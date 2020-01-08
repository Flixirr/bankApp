package com.algorithms;

import java.io.File;
import java.util.Map;
import static javax.swing.JOptionPane.showMessageDialog;

public class CheckDataCorrectness {

    public static boolean checkForDataType(String PESEL, String password, String name, String surname) {
        String numReg = ".*[0-9]+.*";
        String letRet = ".*[a-zA-Z]+.*";
        String fullLetReg = "[a-zA-Z]+";
        boolean pCheck = PESEL.matches("\\d{11}");
        boolean passCheck = password.matches(numReg) && password.matches(letRet);
        boolean nameCheck = name.matches(fullLetReg);
        boolean surnCheck = surname.matches(fullLetReg);

        if(!passCheck) showMessageDialog(null, "Password must contain at least one letter and one number!");
        if(!pCheck) showMessageDialog(null, "PESEL must be 11 numbers long");
        if(!nameCheck) showMessageDialog(null, "Name must contain only letters!");
        if(!surnCheck) showMessageDialog(null, "Surname must contain only letters!");

        return passCheck && pCheck && nameCheck && surnCheck;
    }

    public static boolean checkAccNum(String accNum) {
        boolean r =  accNum.matches("[12]\\s*0000\\s*\\d{4}");
        if(r) {
            String test = Algs.accNumCorrectFormat(accNum);
            return FileAlgorithms.getNumPeselPair().containsKey(test);
        }

        return false;
    }

    public static boolean passwordPeselMatch(String user, String password)
    {
        Map<String, String> accounts = FileAlgorithms.parseFileToStringMap(new File("accounts/accounts.txt"));
        boolean match = true;

        if(accounts.containsKey(user))
        {
            if(!accounts.get(user).equals(password))
            {
                match = false;
            }
        }
        else
        {
            match = false;
        }

        return match;
    }
}
