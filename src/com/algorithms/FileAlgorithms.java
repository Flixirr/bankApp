package com.algorithms;

import com.gui.ui.AppForm;

import java.io.*;
import java.util.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class FileAlgorithms {

    static File accounts;
    static File dir;
    static FileWriter writerAccounts;
    static BufferedWriter bWriterAcc;

    private static TreeMap<String, String> numPeselPair = new TreeMap<>();
    private static Map<String, String> accNum = new HashMap<>();
    private static Map<String, String> sAccNum = new HashMap<>();

    public static void setNumPeselPair()
    {
        numPeselPair.clear();
        String curLine;
        String holder[];
        try{
            BufferedReader bw = new BufferedReader(new FileReader(new File("accounts/accounts.txt")));
            while((curLine = bw.readLine()) != null)
            {
                holder = curLine.split("!");
                numPeselPair.put(holder[holder.length-3], holder[5]);
                accNum.put(holder[5], holder[holder.length-3]);
                numPeselPair.put(holder[holder.length-1], holder[5]);
                sAccNum.put(holder[5], holder[holder.length-1]);
            }
            bw.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void accountRegFile(Map<String, String> data)
    {
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
                bWriterAcc.write("0");
                bWriterAcc.flush();
                bWriterAcc.close();
            }
            else
            {
                showMessageDialog(null, "This account already exists!");
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> parseFileToStringMap(File data)
    {
        Map<String, String> result = new HashMap<>();
        BufferedReader bw = null;
        String curLine;
        String[] keyAndVal;
        try{
            FileReader fr = new FileReader(data);
            bw = new BufferedReader(fr);
            while((curLine = bw.readLine()) != null)
            {
                keyAndVal = curLine.split("!");
                result.put(keyAndVal[5], keyAndVal[7]);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally
        {
            try{
                if(bw != null)
                    bw.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static int subDirCount(File dir)
    {
        File dirMain = new File("accounts");

        if(!dirMain.exists())
        {
            dir.mkdir();
        }
        String[] dirs = dir.list((file, s) -> new File(file, s).isDirectory());

        assert dirs != null : "NullPointerException occured";
        return dirs.length;
    }

    public static int fileCount(File dir)
    {
        String[] files = dir.list((file, s) -> new File(file, s).isFile());

        assert files != null;
        return files.length;
    }

    public static void addTransactionFile(String transactionID, String amount, String desc, String title,
                                          String target, String from)
    {
        File transactionFrom = new File("accounts/"+numPeselPair.get(from)+'/'+transactionID+".txt");
        try {
            if(AppForm.getThisState() == 2)
            {
                if(readBalance("accounts/"+numPeselPair.get(from)+"/acc.txt") < Double.parseDouble(amount))
                {
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

                    addSubBalance("accounts/"+numPeselPair.get(from)+"/acc.txt", Double.parseDouble(amount), true);
                    if(target.charAt(0) == '1') addSubBalance("accounts/"+numPeselPair.get(target)+"/acc.txt",
                            Double.parseDouble(amount), false);
                    else addSubBalance("accounts/"+numPeselPair.get(target)+"/savacc.txt",
                            Double.parseDouble(amount), false);
                }
            }
            else if(AppForm.getThisState() == 4)
            {
                if(readBalance("accounts/"+numPeselPair.get(from)+"/savacc.txt") < Double.parseDouble(amount))
                {
                    showMessageDialog(null, "Insufficient funds");
                }
                else {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(transactionFrom));
                    bw.write("TITLE!" + title + "!AMOUNT!" + amount + "!DESC!" + desc);
                    bw.flush();
                    bw.close();
                    addSubBalance("accounts/"+numPeselPair.get(from)+"/acc.txt", Double.parseDouble(amount), false);
                    addSubBalance("accounts/"+numPeselPair.get(from)+"/savacc.txt", Double.parseDouble(amount), true);
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static double readBalance(String acc)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(new File(acc)));
            double balance;
            balance = Double.parseDouble(br.readLine());
            br.close();
            return balance;
        }
        catch (IOException e)
        {
            showMessageDialog(null, e.getMessage());
            return 0;
        }
    }

    static void addSubBalance(String acc, double amount, boolean sub) throws IOException
    {
        double nBalance;
        if(sub) nBalance = readBalance(acc)-amount;
        else nBalance = readBalance(acc)+amount;
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(acc)));
        bw.write(Double.toString(nBalance));
        bw.flush();
        bw.close();
    }

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
