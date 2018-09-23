package com.twu.biblioteca;

import java.io.*;
import java.util.*;
public class BookList {

    private static String bookListHeader = "S/N  |" + String.format("%-30s", "Book Title") + "|" + String.format("%-20s", "Author") + "|Publish Year";
    private static String successfulCheckOutMessage = "Thank you! Enjoy the book.\n";
    private static String unsuccessfulCheckOutMessage = "That book is not available.\n";
    private static String successfulReturnMessage = "Thank you for returning the book.\n";
    private static String unsuccessfulReturnMessage = "That is not a valid book to return.\n";
    private static String emptyBookListMessage = "There are no available books right now, please try again later..\n";

    private static List<Book> bookList = new ArrayList<Book>();
    private static List<Book> availableBookList = new ArrayList<Book>();
    private static List<Book> unavailableBookList = new ArrayList<Book>();

    private static String fileName = "Book List.txt";
    private static String line = null;
    private static int[] availableBooksArray;
    private static int[] unavailableBooksArray;
    private static int numberOfAvailableBooks;
    private static int numberOfUnavailableBooks;
    private static String listOfBooks;

    private static void retrieveBookList () {
        bookList.clear();

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null && line.trim().length() > 0) {
                Book newBook = new Book(line);
                bookList.add(newBook);
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        if (bookList.size() != 0) listOfBooks = listBooks(bookList);
    }

    public static void addBookToList(Book newBook) {
        try (FileWriter fileWriter = new FileWriter(fileName, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            printWriter.println(newBook.listAllDetail());
        }
        catch(IOException ex1) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        retrieveBookList();
    }

    private static void writeToFile (List <Book> bookList) {
        try (FileWriter fileWriter = new FileWriter(fileName, false);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            for (int i = 0; i < bookList.size(); i++) {
                printWriter.println(bookList.get(i).listAllDetail());
            }
        }
        catch(IOException ex1) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        retrieveBookList();
    }

    public static void removeBook(String title, String creator, int publishYear) {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getTitle().toLowerCase().indexOf(title.toLowerCase()) != -1 &&
                    bookList.get(i).getCreator().toLowerCase().indexOf(creator.toLowerCase()) != -1 &&
                    bookList.get(i).getReleaseYear() == publishYear) {
                bookList.remove(i);
                i = bookList.size();
            }
        }

        try (FileWriter fileWriter = new FileWriter(fileName, false);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {

            for (int i = 0; i < bookList.size(); i++) {
                printWriter.println(bookList.get(i).listAllDetail());
            }
        }
        catch(IOException ex1) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        retrieveBookList();
    }

    public static void removeAllBooks() {
        bookList.clear();
        availableBooksArray = null;
        unavailableBooksArray = null;
        listOfBooks = null;
        numberOfAvailableBooks = 0;
        numberOfUnavailableBooks = 0;

        try (FileWriter fileWriter = new FileWriter(fileName, false);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            printWriter.print("");
        }
        catch(IOException ex1) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }

    public static String listBooks(List <Book> bookList) {
        listOfBooks = "";
        availableBooksArray = null;
        unavailableBooksArray = null;
        numberOfAvailableBooks = 0;
        numberOfUnavailableBooks = 0;
        availableBookList.clear();
        unavailableBookList.clear();

        if (bookList.size() == 0) {
            retrieveBookList();
            if (bookList.size() == 0) listOfBooks = emptyBookListMessage;
        }
        else {
            listOfBooks = listOfBooks + bookListHeader + "\n";
            availableBooksArray = new int[bookList.size()];
            unavailableBooksArray = new int[bookList.size()];
            numberOfAvailableBooks = 0;

            for (int i = 0; i < bookList.size(); i++) {
                if (bookList.get(i).getCheckOutStatus()) {
                    numberOfAvailableBooks++;
                    availableBookList.add(bookList.get(i));
                    availableBooksArray[numberOfAvailableBooks - 1] = i;
                    listOfBooks = listOfBooks + String.format("%-5d", numberOfAvailableBooks) + "|" + bookList.get(i).listDetail() + "\n";
                }
                else {
                    numberOfUnavailableBooks++;
                    unavailableBookList.add(bookList.get(i));
                    unavailableBooksArray[numberOfUnavailableBooks - 1] = i;
                }
            }
        }
        return listOfBooks;
    }

    public static String printList(List<Book> listToPrint) {
        String out = "";
        out = out + bookListHeader + "\n";
        for (int i = 0; i < listToPrint.size(); i++) {
            out = out + String.format("%-5d", i + 1) + "|" + listToPrint.get(i).listDetail() + "\n";
        }
        return out;
    }

    public static void checkOutABook(int serial) {
        if (serial < 1 || serial > numberOfAvailableBooks) {
            System.out.println(unsuccessfulCheckOutMessage);
        }
        else if (bookList.get(availableBooksArray[serial - 1]) != null &&
                bookList.get(availableBooksArray[serial - 1]).getCheckOutStatus()) {
            bookList.get(availableBooksArray[serial - 1]).checkOutItem();
            System.out.println(successfulCheckOutMessage);
        }
        else {
            System.out.println(unsuccessfulCheckOutMessage);
        }
        writeToFile(bookList);
    }

    public static void returnABook(int serial) {
        if (serial < 1 || serial > numberOfUnavailableBooks) {
            System.out.println(unsuccessfulReturnMessage);
        }
        else if (bookList.get(unavailableBooksArray[serial - 1]) != null &&
                !bookList.get(unavailableBooksArray[serial - 1]).getCheckOutStatus()) {
            bookList.get(unavailableBooksArray[serial - 1]).returnItem();
            System.out.println(successfulReturnMessage);
        }
        else {
            System.out.println(unsuccessfulReturnMessage);
        }
        writeToFile(bookList);
    }

    public static List<Book> getBookList() {
        return bookList;
    }

    public static List<Book> getAvailableList() {
        return availableBookList;
    }

    public static List<Book> getUnavailableList() {
        return unavailableBookList;
    }
}
