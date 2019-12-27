package com.algorithms;

import static com.algorithms.FileAlgorithms.accNumGet;
import static com.algorithms.FileAlgorithms.getAccNum;
import java.io.File;
import java.util.Collections;

public class accNumGenerator {
    static String baseForAcc = "1 0000 ";
    static String baseForSavAcc = "2 0000 ";
    static String baseForBrokerage = "3 0000 ";

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
        accNumGet();
        Collections.sort(getAccNum());
        if(FileAlgorithms.subDirCount(new File("accounts")) == 0)
        {
            return baseForAcc+"0001";
        }
        else
        {
            if(Integer.parseInt(getAccNum().get(0)) != 1) {
                return baseForAcc+"0001";
            }
            else {
                for (int i = 0; i < getAccNum().size() - 1; i++) {
                    if((Integer.parseInt(getAccNum().get(i+1)) - Integer.parseInt(getAccNum().get(i))) != 1)
                        return baseForAcc+numXXXXFormat(Integer.parseInt(getAccNum().get(i))+1);
                }
            }
            return baseForAcc+numXXXXFormat(Integer.parseInt(getAccNum().get(getAccNum().size()-1))+1);
        }
    }
}
