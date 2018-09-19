package com.twu.biblioteca;

import java.util.*;

public class BibliotecaApp {

    private static String welcomeMessage = "Welcome to Biblioteca :)\n";
    private static String goodbyeMessage = "Thank you for using Biblioteca..\n";
    private static String invalidMenuOptionMessage = "Select a valid option!\n";
    private static String actionMessage = "What would you like to do?\n";
    private static String emptyBookListMessage = "There are no available books right now, please try again later..\n";
    private static String successfulCheckOutMessage = "Thank you! Enjoy the book.\n";
    private static String unsuccessfulCheckOutMessage = "That book is not available.\n";
    private static String successfulReturnMessage = "Thank you for returning the book.\n";
    private static String unsuccessfulReturnMessage = "That is not a valid book to return.\n";

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
        reader.close();
    }

    public static void checkUserChoice() {
        getUserChoice();

        try {
            int i = Integer.parseInt(userChoice);
        }
        catch (NumberFormatException e) {
            if (userChoice.toLowerCase() != "quit") invalidOptionSelected();
        }

        if (isValidOption()) {
            doAction();
        }
        else invalidOptionSelected();
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
                System.out.
            }
        }
    }
}
