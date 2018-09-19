package com.twu.biblioteca;

import java.io.*;
import java.util.*;
public class BookList {

    private static String bookListHeader = "S/N  |" + String.format("%-20s", "Book Title") + "|" + String.format("%-12s", "Author") + "|Publish Year";
    private static String successfulCheckOutMessage = "Thank you! Enjoy the book.\n";
    private static String unsuccessfulCheckOutMessage = "That book is not available.\n";
    private static String successfulReturnMessage = "Thank you for returning the book.\n";
    private static String unsuccessfulReturnMessage = "That is not a valid book to return.\n";

    private static List<Book> bookList = new ArrayList<Book>();

    private static String fileName = "Book List.txt";
    private static String line = null;
    private static int[] bookSerialNumberArray;

    public static void retrieveBookList () {
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                Book newBook = new Book(line);
                bookList.add(newBook);
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Error finding file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }

    private static void addBookListToFile(Book newBook) {
        try (FileWriter fileWriter = new FileWriter(fileName, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            printWriter.println(newBook.listBookDetail());
            retrieveBookList();
        }
        catch(IOException ex1) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        bookList.clear();
    }

    public static void listBooks() {

        System.out.println(bookListHeader);
        int bookSerialNumber = 1;
        bookSerialNumberArray = new int[bookList.size()];

        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getCheckOutStatus()) {
                bookSerialNumberArray[bookSerialNumber - 1] = i;
                System.out.println(String.format("%-6d", bookSerialNumber) + bookList.get(i).listBookDetail());
                bookSerialNumber++;
            }
        }
    }

    public static void checkOutABook(int serial) {
        if (bookList.get(bookSerialNumberArray[serial - 1]) != null && bookList.get(bookSerialNumberArray[serial - 1]).getCheckOutStatus()) {
            bookList.get(bookSerialNumberArray[serial - 1]).checkOutBook();
            System.out.println(successfulCheckOutMessage);
        }
        else {
            System.out.println(unsuccessfulCheckOutMessage);
        }
    }

    public static void returnABook(int serial) {
        if (bookList.get(bookSerialNumberArray[serial - 1]) == null && !(bookList.get(bookSerialNumberArray[serial - 1]).getCheckOutStatus())) {
            bookList.get(bookSerialNumberArray[serial - 1]).returnBook();
            System.out.println(successfulReturnMessage);
        }else {
            System.out.println(unsuccessfulReturnMessage);
        }
    }

    public static List<Book> getBookList() {
        return bookList;
    }
}
