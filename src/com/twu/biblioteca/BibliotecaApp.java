package com.twu.biblioteca;

import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    private static String invalidMenuOptionMessage = "Select a valid option!\n";
    private static String actionMessage = "What would you like to do?\n";
    private static String checkOutMessage = "Please enter serial number to check out: ";
    private static String returnMessage = "Please enter serial number to return: ";

    private static String input;

    public static void main(String[] args) {

        Menu.openProgram();

        do {
            Menu.showMenu();
            input = Integer.toString(checkUserChoice(getUserChoice(), Menu.getMenu().size()));

            if (Integer.parseInt(input) > 0) {
                doAction(input, Menu.getMenu());
            }
        } while (Integer.parseInt(input) != 0);

        Menu.closeProgram();
    }

    private static String getUserChoice() {
        Scanner reader = new Scanner(System.in);
        return reader.nextLine();
    }

    public static int checkUserChoice(String userChoice, int sizeOfList) {
        if (parseOption(userChoice, sizeOfList) == 0) return 0;
        if (parseOption(userChoice, sizeOfList) != -1) return parseOption(userChoice, sizeOfList);
        else invalidOptionSelected();
        return -1;
    }

    private static void invalidOptionSelected() {
        System.out.println(invalidMenuOptionMessage);
    }

    public static void doAction(String userChoice, List<String> options) {
        if (options.get(Integer.parseInt(userChoice) - 1).equals("List Books")) {
            System.out.println("\n" + BookList.listBooks(BookList.getBookList()));

            if (BookList.getBookList().size() == 0) {
                checkUserChoice(userChoice, options.size());
            }
            else {
                BookList.listBooks(BookList.getBookList());
                doUserAction(Menu.getActionMenu());
            }
        }
    }

    public static void doUserAction(List<String> options) {
        System.out.println(actionMessage);
        Menu.showActionMenu();

        do {
            input = Integer.toString(checkUserChoice(getUserChoice(), Menu.getActionMenu().size()));
        } while (Integer.parseInt(input) < 0);

        if (Integer.parseInt(input) > 0) {
            if (options.get(Integer.parseInt(input) - 1).equals("Check out item")) {
                do {
                    if (BookList.getAvailableList().size() != 0) {
                        System.out.println(checkOutMessage);
                        input = Integer.toString(checkUserChoice(getUserChoice(), BookList.getAvailableList().size()));

                        if (Integer.parseInt(input) > 0) BookList.checkOutABook(Integer.parseInt(input));

                    }
                    else System.out.println(BookList.listBooks(BookList.getBookList()));
                } while (Integer.parseInt(input) < 0);
            }
            else if (options.get(Integer.parseInt(input) - 1).equals("Return item")) {
                do {
                    System.out.println(BookList.printList(BookList.getUnavailableList()));
                    if (BookList.getUnavailableList().size() != 0) {
                        System.out.println(returnMessage);

                        input = Integer.toString(checkUserChoice(getUserChoice(), BookList.getUnavailableList().size()));

                        if (Integer.parseInt(input) > 0) BookList.returnABook(Integer.parseInt(input));
                    }
                } while (Integer.parseInt(input) < 0);

            }
        }
    }

    private static int parseOption(String userChoice, int sizeOfList) {
        if (isQuit(userChoice)) return 0;
        if (!isNumeric(userChoice)) return -1;
        if (isOptionValid(userChoice, sizeOfList)) return Integer.parseInt(userChoice);
        return -1;
    }

    private static boolean isQuit(String userChoice) {
        if (userChoice.length() >= 4 && userChoice.toLowerCase().indexOf("quit") >= 0) return true;
        return false;
    }

    private static boolean isNumeric(String userChoice) {
        for (int i = 0; i < userChoice.length(); i++) {
            if (userChoice.charAt(i) < '0' || userChoice.charAt(i) > '9') return false;
        }
        return true;
    }

    private static boolean isOptionValid(String userChoice, int sizeOfList) {
        if (userChoice.trim().length() == 0) return false;
        if (Integer.parseInt(userChoice) < 1 || Integer.parseInt(userChoice) > sizeOfList) return false;
        return true;
    }
}
