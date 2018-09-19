package com.twu.biblioteca;

import java.io.*;
import java.util.*;

public class BookList {

    private static String bookListHeader = "S/N  |" + String.format("%-20d", "Book Title") + "|" + String.format("%-12d", "Author") + "|Publish YearYear";

    private static List<Book> bookList = new ArrayList<Book>();
    private static String fileName = "Book List.txt";
    private static String line = null;

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
        int[] bookSerialNumberArray = new int[bookList.size()];

        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getCheckOutStatus()) {
                bookSerialNumberArray[i] = bookSerialNumber;
                System.out.println(String.format("%-6d", bookSerialNumber) + bookList.get(i).listBookDetail());
                bookSerialNumber++;
            }
        }
    }

    public static List<Book> getBookList() {
        return bookList;
    }
}
