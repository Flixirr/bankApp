package com.algorithms;

import com.userfiles.Account;

import java.io.File;

public interface NumGenerator {

    //convert number to string XXXX, e.g 1 to 0001
    static String numXXXXFormat(int num) {
        StringBuilder result = new StringBuilder(Integer.toString(num));

        for (int i = 4 - result.length(); i > 0; i--) {
            result.insert(0, '0');
        }

        return result.toString();
    }

    static String generateAccNum() {
        int fileCount = FileAlgorithms.fileCount(new File("accounts"));
        if (fileCount == 0) {
            return "1 0000 0001";
        } else {
            return "1 0000 " + numXXXXFormat(fileCount + 1);
        }
    }

    static String generateSAccNum() {
        int fileCount = FileAlgorithms.fileCount(new File("accounts"));
        if (fileCount == 0) {
            return "2 0000 0001";
        } else {
            return "2 0000 " + numXXXXFormat(fileCount + 1);
        }
    }

    static String generateTransactionNum(Account acc) {
        return acc.getNumber() + (acc.getTransactions().size());
    }

    //generate random code for 20 pln deposit
    static String generateDepoNum(){
        final String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(5);
        //Get random char from string, then append it to stringbuilder
        for(int i = 0; i < 5; i++){
            int randIndex = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(randIndex));
        }

        return sb.toString();
    }
}
