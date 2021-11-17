package com.application;

import java.util.HashMap;
import java.util.Scanner;

public class MultiValueDicitionaryAppRunner {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String command = "";
        MultiValueDictionary multiValueDict = new MultiValueDictionary(new HashMap<>());
        while (!command.equals("EXIT")) {
            System.out.println("Enter command:");
            command = scan.next();
            String key = "";
            String value = "";


            switch(command) {

                case "KEYS" : System.out.println(multiValueDict.getKeys());
                    break;
                case "MEMBERS" : System.out.println("Enter key:");
                    key = scan.next();
                    try {
                        System.out.println(multiValueDict.getValues(key));
                    }
                    catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case "ADD" : System.out.println("Enter key:");
                    key = scan.next();
                    System.out.println("Enter value:");
                    value = scan.next();
                    try {
                        multiValueDict.add(key, value);
                        System.out.println("Added");
                    }
                    catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case "REMOVE" : System.out.println("Enter key:");
                    key = scan.next();
                    System.out.println("Enter value:");
                    value = scan.next();
                    try {
                        multiValueDict.remove(key, value);
                        System.out.println("Removed");
                    }
                    catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case "REMOVEALL" : System.out.println("Enter key:");
                    key = scan.next();
                    try {
                        multiValueDict.removeAll(key);
                        System.out.println("Removed");
                    }
                    catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case "CLEAR" : multiValueDict.clear();
                    System.out.println("Cleared");
                    break;
                case "KEYEXISTS" :
                    System.out.println("Enter key:");
                    key = scan.next();
                    try {
                        System.out.println(multiValueDict.isKeyExists(key));
                    }
                    catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case "MEMBEREXISTS" :
                    System.out.println("Enter key:");
                    key = scan.next();
                    System.out.println("Enter value:");
                    value = scan.next();
                    try {
                        System.out.println(multiValueDict.isValueExists(key, value));
                    }
                    catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case "ALLMEMBERS" :
                    System.out.println(multiValueDict.getAllValues());
                    break;
                case "ITEMS" :
                    System.out.println(multiValueDict.getItems());
                    break;
            }
        }

    }

}
