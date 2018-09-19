package com.twu.biblioteca;

import java.io.*;
import java.util.*;

public class Menu {

    private static String menuHeading = "Please choose an action from the list below:";

    private static List<String> Options = new ArrayList<String>();
    private static String fileName = "Options.txt";
    private static String line = null;

    //private static String workingOptionFilePath = System.getProperty("user.dir") + "/Options.txt";

    public static void showMenu() {
        System.out.println(menuHeading);
        showOptions();
    }

    public static void showOptions () {
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                Options.add(line);
                System.out.println(Options.size() + ") " + Options.get(Options.size()-1));
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            writeToOptionsFile("List Books");
            showOptions();
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }

    private static void writeToOptionsFile(String newOption) {
        try (FileWriter fileWriter = new FileWriter(fileName, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            printWriter.println(newOption);
        }
        catch(IOException ex1) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        Options.clear();
    }

    public static void addOption(String newOptionToAdd) {
        writeToOptionsFile(newOptionToAdd);
    }

    public static List<String> getOption() {
        return Options;
    }
}
