package com.algorithms;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.Map;

public class FileAlgorithms {

    static File accounts;
    static FileWriter writerAccounts;
    static BufferedWriter bWriterAcc;

    public static void accountRegFile(Map<String, String> data)
    {
        try {
            accounts = new File("accounts.txt");
            writerAccounts = new FileWriter(accounts, accounts.exists());
            bWriterAcc = new BufferedWriter(writerAccounts);
            for(Map.Entry<String, String> entry : data.entrySet())
            {
                bWriterAcc.write(entry.getKey()+entry.getValue()+"!");
            }
            bWriterAcc.write('\n');
            bWriterAcc.flush();
            bWriterAcc.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }



}
