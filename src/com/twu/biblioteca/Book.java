package com.twu.biblioteca;

public class Book {

    private String title;
    private String author;
    private int publishYear;
    private boolean isAvailStatus = true;
    private String bookDetails;

    public Book(String title, String author, int publishYear) {
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
        formBookDetails();
    }

    private void formBookDetails() {
        this.bookDetails = String.format("%-20d", this.title) + "|" + String.format("%-12d", this.author) + "|" + Integer.toString(this.publishYear) + "|" + Boolean.toString(isAvailStatus);
    }

    public Book(String bookDetails) {
        this.title = bookDetails.substring(0, 20);
        this.author = bookDetails.substring(21, 33);
        this.publishYear = Integer.parseInt(bookDetails.substring(34, 38));
        this.isAvailStatus = Boolean.parseBoolean(bookDetails.substring(39, bookDetails.length()));
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public int publishYear() {
        return this.publishYear;
    }

    public boolean getCheckOutStatus() {
        return this.isAvailStatus;
    }


    public String listBookDetail() {
        return bookDetails.substring(0, 38);
    }

    public void checkOutBook() {
        this.isAvailStatus = false;
        formBookDetails();
    }

    public void returnBook() {
        this.isAvailStatus = true;
        formBookDetails();
    }
}
