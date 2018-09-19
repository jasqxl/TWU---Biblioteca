package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    private static String welcomeMessage = "Welcome to Biblioteca :)\n";
    private static String goodbyeMessage = "Thank you for using Biblioteca..\n";
    private static String invalidMenuOptionMessage = "Select a valid option!\n";
    private static String actionMessage = "What would you like to do?\n";
    private static String emptyBookListMessage = "There are no available books right now, please try again later..\n";

    private static List<String> options = new ArrayList<String>();
    private static String userChoice = "-1";

    public static void main(String[] args) {
        System.out.println(welcomeMessage);
        Menu.showMenu();
        options = Menu.getOption();

        checkUserChoice();
        System.out.println(goodbyeMessage);
    }

    public static void getUserChoice() {
        Scanner reader = new Scanner(System.in);
        userChoice = reader.nextLine();
    }

    public static void checkUserChoice() {

        getUserChoice();

        if (userChoice.length() >= 4 && userChoice.toLowerCase().indexOf("quit") >= 0) return;

        if (!isNumeric()) invalidOptionSelected();
        else if (isValidOption()) {
            doAction();
        }
        else invalidOptionSelected();
    }

    private static boolean isNumeric() {
        for (int i = 0; i < userChoice.length(); i++) {
            if (userChoice.charAt(i) < '0' || userChoice.charAt(i) > '9') return false;
        }
        return true;
    }

    private static void invalidOptionSelected() {
        System.out.println(invalidMenuOptionMessage);
        Menu.showMenu();
        checkUserChoice();
    }

    public static boolean isValidOption() {
        return (options.get(Integer.parseInt(userChoice)) != null) ? true : false;
    }

    public static void doAction() {
        if (options.get(Integer.parseInt(userChoice)) == "List Books") {
            if (BookList.getBookList().size() == 0) {
                System.out.println(emptyBookListMessage);
                checkUserChoice();
            }
            else {
                BookList.listBooks();
                //System.out.println(actionMessage);
            }
        }
    }
}
