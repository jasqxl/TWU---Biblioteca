package com.twu.biblioteca;

import java.io.*;
import java.util.*;

public class Menu {

    private static String welcomeMessage = "Welcome to Biblioteca :)\n";
    private static String goodbyeMessage = "Thank you for using Biblioteca..\n";
    private static String menuHeading = "Please choose an action from the list below:";

    private static List<String> necessaryOptions = new ArrayList<String>();
    private static List<String> actionOptions = new ArrayList<String>();
    private static List<String> options = new ArrayList<String>();
    private static String fileName = "options.txt";
    private static String line = null;

    public static void openProgram() {
        necessaryOptions.clear();
        necessaryOptions.add("List Books");
        options = necessaryOptions;
        actionOptions.clear();
        actionOptions.add("Check out item");
        actionOptions.add("Return item");

        System.out.println(welcomeMessage);
    }

    public static void closeProgram() {
        System.out.println(goodbyeMessage);
    }

    public static void showMenu () {
        options.clear();

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            System.out.println(menuHeading);

            while((line = bufferedReader.readLine()) != null) {
                options.add(line);
                System.out.println(options.size() + ") " + options.get(options.size()-1));
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            writeToOptionsFile("List Books");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }

    public static void showActionMenu () {
        printMenu(actionOptions);
        System.out.print("\nEnter choice: ");
    }

    private static void printMenu(List<String> menu) {
        for (int i = 0; i < menu.size(); i++) {
            System.out.println(i + 1 + ") " + menu.get(i));
        }
    }

    public static void writeToOptionsFile(String newOption) {
        try (FileWriter fileWriter = new FileWriter(fileName, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            printWriter.println(newOption);
        }
        catch(IOException ex1) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        showMenu();
    }

    public static void removeOptions(String optionToRemove) {

        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).toLowerCase().indexOf(optionToRemove.toLowerCase()) != -1) {
                options.remove(i);
                i = options.size();
            }
        }

        try (FileWriter fileWriter = new FileWriter(fileName, false);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {

            for (int i = 0; i < options.size(); i++) {
                printWriter.println(options.get(i));
            }
        }
        catch(IOException ex1) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        showMenu();
    }

    public static void removeAllOptions() {
        options.clear();
        options.add("List Books");

        try (FileWriter fileWriter = new FileWriter(fileName, false);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            printWriter.println("List Books");
        }
        catch(IOException ex1) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }

    public static List<String> getMenu() {
        return options;
    }

    public static List<String> getActionMenu() {
        return actionOptions;
    }
}
