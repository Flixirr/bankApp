package com.algorithms;

import com.gui.actions.ButtonActions;

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
            if(test.equals(ButtonActions.getLoggedUser().getbAcc().getNumber())) return false;
            return FileAlgorithms.getNumUserPair().containsKey(test);
        }

        return false;
    }

    public static boolean checkAmountProv(String amount) {
        boolean r = amount.matches("[0-9]+\\.\\d{2}") || amount.matches("[0-9]+\\.\\d") || amount.matches("[0-9]+");
        if(!r) showMessageDialog(null, "Amount type incorrect - correct type is XX.XX");
        return r;
    }
}
