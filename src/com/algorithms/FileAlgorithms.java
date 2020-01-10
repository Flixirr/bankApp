package com.algorithms;

import com.userfiles.Transaction;
import com.userfiles.User;

import java.io.*;
import java.util.*;

import static javax.swing.JOptionPane.showMessageDialog;
public class FileAlgorithms {

    private static HashMap<String, User> numUserPair = new HashMap<>();

    //Sets map with acc number as key and PESEL as value to use it to login/make transaction
    public static void setNumUserPair() {
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
    public static void accountRegFile(Map<String, String> data) {
        File account;
        try {
            File dir = new File("accounts");
            account = new File("accounts/"+data.get("PESEL!")+".bin");
            boolean dirExist = dir.exists();
            //Check if user already exists
            if(!dirExist) dirExist = dir.mkdir();
            if(!account.exists() && dirExist) {
                //Create new user, then write object to file
                User user = new User(data.get("Name!"), data.get("Surname!"), data.get("PESEL!"), data.get("Password!"));
                FileOutputStream fileOutputStream = new FileOutputStream(account);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

                objectOutputStream.writeObject(user);
            }
            else {
                showMessageDialog(null, "This account already exists!");
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    //Reads requested by pesel object from file
    public static User readObject(String pesel) {
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

    //Counts files in chosen directory
    public static int fileCount(File dir) {
        if(!dir.exists()) return 0;
        String[] files = dir.list((file, s) -> new File(file, s).isFile());
        System.out.println(Arrays.toString(files));

        assert files != null;
        return files.length;
    }

    //Getters and setters

    public static HashMap<String, User> getNumUserPair() {
        return numUserPair;
    }
}
