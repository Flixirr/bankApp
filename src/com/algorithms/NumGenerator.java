package com.algorithms;

import java.io.File;
import java.util.Collections;

import static com.algorithms.FileAlgorithms.getNumPeselPairKeys;

public class NumGenerator {
    static String baseForAcc = "1 0000 ";
    static String baseForSavAcc = "2 0000 ";

    public static String numXXXXFormat(int num)
    {
        StringBuilder result = new StringBuilder(Integer.toString(num));

        for(int i = 4-result.length(); i > 0; i--)
        {
            result.insert(0, '0');
        }

        return result.toString();
    }

    public static String generateAccNum()
    {
        int sz = getNumPeselPairKeys().size();
        if(FileAlgorithms.subDirCount(new File("accounts")) == 0)
        {
            return baseForAcc+"0001";
        }
        else
        {
            return baseForAcc+numXXXXFormat(Integer.parseInt(getNumPeselPairKeys().get(sz/2-1).split(" ")[2])+1);
        }
    }

    public static String generateSAccNum()
    {
        int sz = getNumPeselPairKeys().size();
        if(FileAlgorithms.subDirCount(new File("accounts")) == 0)
        {
            return baseForSavAcc+"0001";
        }
        else
        {
            return baseForSavAcc+numXXXXFormat(Integer.parseInt(getNumPeselPairKeys().get(sz-1).split(" ")[2])+1);
        }
    }

    public static String generateTransactionNum(String accNum)
    {
        String newAccNum = accNum.replace(" ", "");
        int tNum = FileAlgorithms.fileCount(new File("accounts/"+FileAlgorithms.getNumPeselPair().get(accNum)));

        return newAccNum+ (tNum - 1);
    }
}
