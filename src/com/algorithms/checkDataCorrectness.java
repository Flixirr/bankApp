package com.algorithms;

import java.io.File;
import java.util.Map;

public class checkDataCorrectness {

    //IMPORTANT [1 - surname, 3 - password, 5 - pesel, 7 - name]

    String PESEL, name, surname, password;

    public static boolean checkPESELAndIfAdult()
    {
        boolean checkResult = true;
        //TODO make this shit work
        return checkResult;
    }

    public static boolean checkAccNum(String accNum)
    {
        boolean r =  accNum.matches("[12]\\s*0000\\s*\\d{4}");
        if(r)
        {
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
