package com.algorithms;

import com.gui.ui.AppForm;

import javax.swing.*;
import java.io.*;
import java.util.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class FileAlgorithms {

    private static TreeMap<String, String> numPeselPair = new TreeMap<>();
    private static Map<String, String> accNum = new HashMap<>();
    private static Map<String, String> sAccNum = new HashMap<>();


    //Sets map with acc number as key and PESEL as value to use it to login/make transaction
    public static void setNumPeselPair() {
        numPeselPair.clear();
        String curLine;
        String[] holder;
        try{
            BufferedReader bw = new BufferedReader(new FileReader(new File("accounts/accounts.txt")));
            while((curLine = bw.readLine()) != null) {
                holder = curLine.split("!");
                numPeselPair.put(holder[holder.length-3], holder[5]);
                accNum.put(holder[5], holder[holder.length-3]);
                numPeselPair.put(holder[holder.length-1], holder[5]);
                sAccNum.put(holder[5], holder[holder.length-1]);
            }
            bw.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //Writes newly created account to txt files
    public static void accountRegFile(Map<String, String> data) {
        File accounts;
        File dir;
        FileWriter writerAccounts;
        BufferedWriter bWriterAcc;
        
        try {
            dir = new File("accounts/"+data.get("PESEL!"));

            if(!dir.exists()) {
                dir.mkdirs();
                accounts = new File("accounts/accounts.txt");
                writerAccounts = new FileWriter(accounts, accounts.exists());
                bWriterAcc = new BufferedWriter(writerAccounts);
                for (Map.Entry<String, String> entry : data.entrySet()) {
                    bWriterAcc.write(entry.getKey() + entry.getValue() + "!");
                }
                bWriterAcc.write('\n');
                bWriterAcc.flush();
                bWriterAcc.close();
                bWriterAcc = new BufferedWriter(new FileWriter(new File("accounts/"+data.get("PESEL!")+"/acc.txt")));
                bWriterAcc.write("0");
                bWriterAcc.flush();
                bWriterAcc.close();
                bWriterAcc = new BufferedWriter(new FileWriter(new File("accounts/"+data.get("PESEL!")+"/savacc.txt")));
                bWriterAcc.write("100");
                bWriterAcc.flush();
                bWriterAcc.close();
            }
            else {
                showMessageDialog(null, "This account already exists!");
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    //return map with pesels and passwords
    public static Map<String, String> parseFileToStringMap(File data) {
        Map<String, String> result = new HashMap<>();
        BufferedReader bw = null;
        String curLine;
        String[] keyAndVal;
        try{
            FileReader fr = new FileReader(data);
            bw = new BufferedReader(fr);
            while((curLine = bw.readLine()) != null) {
                keyAndVal = curLine.split("!");
                //Sets key to pesel and val to password
                result.put(keyAndVal[5], keyAndVal[7]);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try{
                if(bw != null)
                    bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //Counts subdirectories to chosen directory
    public static int subDirCount(File dir) {
        File dirMain = new File("accounts");

        if(!dirMain.exists()) {
            dir.mkdir();
        }
        String[] dirs = dir.list((file, s) -> new File(file, s).isDirectory());

        assert dirs != null : "NullPointerException occured";
        return dirs.length;
    }

    //Counts files in chosen directory
    public static int fileCount(File dir) {
        String[] files = dir.list((file, s) -> new File(file, s).isFile());

        assert files != null;
        return files.length;
    }

    //Add transaction file to target dir and origin dir
    public static void addTransactionFile(String transactionID, String amount, String desc, String title,
                                          String target, String from) {
        File transactionFrom = new File("accounts/"+numPeselPair.get(from)+'/'+transactionID+".txt");
        try {
            if(AppForm.getThisState() == 2) {
                if(readBalance("accounts/"+numPeselPair.get(from)+"/acc.txt") < Double.parseDouble(amount)) {
                    showMessageDialog(null, "Insufficient funds");
                }
                else {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(transactionFrom));
                    bw.write("TITLE!" + title + "!AMOUNT!" + amount + "!DESC!" + desc);
                    bw.flush();
                    bw.close();

                    bw = new BufferedWriter(new FileWriter(new File("accounts/" + numPeselPair.get(target) + '/' + transactionID + ".txt")));
                    bw.write("TITLE!" + title + "!AMOUNT!" + amount + "!DESC!" + desc);
                    bw.flush();
                    bw.close();

                    if(target.charAt(0) == '1') addSubBalance("accounts/"+numPeselPair.get(target)+"/acc.txt", "accounts/"+numPeselPair.get(from)+"/acc.txt",
                            Double.parseDouble(amount));
                    else addSubBalance("accounts/"+numPeselPair.get(target)+"/savacc.txt", "accounts/"+numPeselPair.get(from)+"/acc.txt",
                            Double.parseDouble(amount));
                }
            }
            else if(AppForm.getThisState() == 4) {
                if(readBalance("accounts/"+numPeselPair.get(from)+"/savacc.txt") < Double.parseDouble(amount)) {
                    showMessageDialog(null, "Insufficient funds");
                }
                else {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(transactionFrom));
                    bw.write("TITLE!" + title + "!AMOUNT!" + amount + "!DESC!" + desc);
                    bw.flush();
                    bw.close();
                    addSubBalance("accounts/"+numPeselPair.get(from)+"/acc.txt", "accounts/"+numPeselPair.get(from)+"/savacc.txt", Double.parseDouble(amount));
                }
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    //Reads balance from .txt
    public static double readBalance(String acc) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(acc)));
            double balance;
            balance = Double.parseDouble(br.readLine());
            br.close();
            return balance;
        }
        catch (IOException e) {
            showMessageDialog(null, e.getMessage());
            return 0;
        }
    }

    //Balance update logic here
    static void addSubBalance(String targetAcc, String originAcc, double amount) throws IOException {
        double nBalance, oBal = readBalance(originAcc);
        nBalance = ((double) ((oBal*100-amount*100)/100));
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(originAcc)));
        bw.write(Double.toString(nBalance));
        bw.close();
        addBalance(targetAcc, amount);
    }
    //add to balance method for later use in savings account thread
    static void addBalance(String targetAcc, double amount) throws IOException {
        double nBalance, oBal;
        oBal = readBalance(targetAcc);
        nBalance = ((double) ((oBal*100+amount*100)/100));
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(targetAcc)));
        bw.write(Double.toString(nBalance));
        bw.close();
    }

    //convert file to JLabel
    static JLabel fileToLabel(File f){
        String[] labelElements = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
            String hold = bufferedReader.readLine();
            labelElements = hold.split("!");
        } catch (Exception e) {
            showMessageDialog(null, e.getMessage());
        }
        if(labelElements == null) return new JLabel("error");

        return new JLabel("Title: " + labelElements[1] + "   Description: " + labelElements[5] + "   Amount: " + labelElements[3]);
    }

    //load transactions and put them into JLabels
    public static List<JLabel> getTransactions(String PESEL) {
        List<JLabel> transactions = new ArrayList<>();
        File dir = new File("accounts/"+PESEL);
        File[] fileList = dir.listFiles();

        assert fileList != null;
        for(File f: fileList) {
            if(!(f.getName().equals("acc.txt") || f.getName().equals("savacc.txt"))) {
                transactions.add(fileToLabel(f));
            }
        }

        return transactions;
    }

    //Getters and setters
    public static Map<String, String> getNumPeselPair() {
        return numPeselPair;
    }
    public static ArrayList<String> getNumPeselPairKeys() {
        return new ArrayList<>(numPeselPair.keySet());
    }

    public static Map<String, String> getAccNum() {
        return accNum;
    }

    public static Map<String, String> getsAccNum() {
        return sAccNum;
    }
}
