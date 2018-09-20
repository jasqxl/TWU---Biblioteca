package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    private static String invalidMenuOptionMessage = "Select a valid option!\n";
    private static String actionMessage = "What would you like to do?\n";

    private static List<String> options = new ArrayList<String>();
    private static String userChoice = "-1";

    private static Book testBook1 = new Book("Lord of the Rings", "ME", 1994, false);
    private static Book testBook2 = new Book("Hang of the Rings 2           |Uncle               |2018    |available");
    private static Book testBook3 = new Book("Book to handling bruh", "bot", 1967, true);
    private static Book testBook4 = new Book("Lord of the Rings 2           |handphone           |2004    |Unavailable");
    private static Book testBook5 = new Book("King an lah Rings", "kenny", 1994, false);
    private static Book testBook6 = new Book("osfd of the Rings 2           |Uncle               |2018    |available");
    private static Book testBook7 = new Book("Book sfv hanling bruh", "quah xue", 2012, true);
    private static Book testBook8 = new Book("Lord sfgwef Rings 2           |dude                |2045    |Unavailable");


    public static void main(String[] args) {
        Menu.openProgram();
        options = Menu.getOptions();
        checkUserChoice();
        Menu.closeProgram();
    }

    public static void getUserChoice() {
        Scanner reader = new Scanner(System.in);
        userChoice = reader.nextLine();
    }

    public static void checkUserChoice() {

        getUserChoice();

        if (parseOption() == 0) return;
        if (parseOption() != -1) doAction();
        else invalidOptionSelected();
    }

    private static void invalidOptionSelected() {
        System.out.println(invalidMenuOptionMessage);
        Menu.showMenu();
        checkUserChoice();
    }

    public static void doAction() {
        if (options.get(Integer.parseInt(userChoice)) == "List Books") {
            if (BookList.getBookList().size() == 0) {
                checkUserChoice();
            }
            else {
                BookList.listBooks();
                //System.out.println(actionMessage);
            }
        }
    }

    public static int parseOption() {
        if (userChoice.length() >= 4 && userChoice.toLowerCase().indexOf("quit") >= 0) return 0;
        return isNumericAndValid();
    }

    private static int isNumericAndValid() {
        for (int i = 0; i < userChoice.length(); i++) {
            if (userChoice.charAt(i) < '0' || userChoice.charAt(i) > '9') return -1;
        }
        return (options.get(Integer.parseInt(userChoice)) != null) ? Integer.parseInt(userChoice) : -1;
    }
}
