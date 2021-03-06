package com.algorithms;

import com.userfiles.Transaction;
import com.userfiles.User;

import javax.swing.*;
import java.io.*;
import java.util.*;

import static javax.swing.JOptionPane.showMessageDialog;

public interface FileAlgorithms {



    //Sets map with acc number as key and PESEL as value to use it to login/make transaction
    static void setNumUserPair(Map<String, User> numUserPair) {
        File dir = new File("accounts");
        String[] files = dir.list((file, s) -> new File(file, s).isFile());
        assert files != null : "files is null";
        for(String f : files)
        {
            User u = readObject(f.split("\\.")[0]);
            assert u != null : "u is null";
            numUserPair.put(u.getbAcc().getNumber(), u);
            numUserPair.put(u.getsAcc().getNumber(), u);
        }
    }

    //Writes newly created account to txt files
    static void accountRegFile(Map<String, String> data) {
        File dir = new File("accounts");
        boolean dirExist = dir.exists();
        //Check if user already exists
        if(!dirExist) dirExist = dir.mkdir();
        //Create new user, then write object to file
        if(dirExist) {
            User user = new User(data.get("Name!"), data.get("Surname!"), data.get("PESEL!"), data.get("Password!"));
            saveChanges(user, false);
            showMessageDialog(null, "Account has been created");
        }
    }

    static void saveChanges(User user, boolean overwrite) {
        File userFile = new File("accounts/" + user.getPESEL() + ".bin");
        if(overwrite || !userFile.exists()) {
            try {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(userFile));

                objectOutputStream.writeObject(user);
                objectOutputStream.flush();
                objectOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            showMessageDialog(null, "This account already exists!");
        }
    }

    //Reads requested by pesel object from file
    static User readObject(String pesel) {
        User user = null;
        try {
            File f = new File("accounts/"+pesel+".bin");
            if(f.exists()) {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(f));
                user = (User) objectInputStream.readObject();
            } else {
                return null;
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        return user;
    }

    //return transaction labels as arraylist
    static List<JLabel> transToLabel(User user) {
        List<JLabel> labels = new ArrayList<>();

        for(Transaction t : user.getbAcc().getTransactions()) {
            labels.add(t.getTransactionLabel());
        }
        for(Transaction t : user.getsAcc().getTransactions()) {
            labels.add(t.getTransactionLabel());
        }

        return labels;
    }

    //Counts files in chosen directory
    static int fileCount(File dir) {
        if(!dir.exists()) return 0;
        String[] files = dir.list((file, s) -> new File(file, s).isFile());

        assert files != null;
        return files.length;
    }

}
