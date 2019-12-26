package com.algorithms;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static javax.swing.JOptionPane.showMessageDialog;

public class FileAlgorithms {

    static File accounts;
    static File dir;
    static FileWriter writerAccounts;
    static BufferedWriter bWriterAcc;

    private static ArrayList<String> accNum = new ArrayList<>();

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
                accNum.add(keyAndVal[keyAndVal.length-1].split(" ")[2]);
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

    public static Map<String, String> peselAccountPair()
    {
        Map<String, String> result = new HashMap<>();
        BufferedReader bw = null;
        String curLine;
        String[] keyAndVal;
        try{
            bw = new BufferedReader(new FileReader(new File("accounts/accounts.txt")));
            while((curLine = bw.readLine()) != null)
            {
                keyAndVal = curLine.split("!");
                result.put(keyAndVal[5], keyAndVal[9]);
                accNum.add(keyAndVal[keyAndVal.length-1].split(" ")[2]);
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

    public static void accNumGet()
    {
        String curLine;
        accNum.clear();
        try{
            BufferedReader bw = new BufferedReader(new FileReader(new File("accounts/accounts.txt")));
            while((curLine = bw.readLine()) != null)
            {
                accNum.add(curLine.split("!")[curLine.split("!").length-1].split(" ")[2]);
            }

            bw.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static int subDirCount(File dir)
    {
        String[] dirs = dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File file, String s) {
                return new File(file, s).isDirectory();
            }
        });

        assert dirs != null : "NullPointerException occured";
        return dirs.length;
    }

    public static ArrayList<String> getAccNum() {
        return accNum;
    }
}
