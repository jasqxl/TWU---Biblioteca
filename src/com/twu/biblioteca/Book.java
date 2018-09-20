package com.twu.biblioteca;

public class Book {

    private String title;
    private String author;
    private int publishYear;
    private boolean isAvailStatus = true;
    private String bookDetails;

    public Book() {}

    public Book(String title, String author, int publishYear, boolean isAvailStatus) {
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
        this.isAvailStatus = isAvailStatus;
        formBookDetails();
    }

    private void formBookDetails() {
        this.bookDetails = String.format("%-20s", title) + "|" + String.format("%-12s", author) + "|" + Integer.toString(publishYear) + " |" + Boolean.toString(isAvailStatus);
    }

    public Book(String bookDetails) {
        this.title = bookDetails.substring(0, 20).trim();
        this.author = bookDetails.substring(21, 33).trim();
        this.publishYear = Integer.parseInt(bookDetails.substring(34, 38));
        this.isAvailStatus = Boolean.parseBoolean(bookDetails.substring(40, bookDetails.length()));
        this.bookDetails = bookDetails;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getPublishYear() {
        return this.publishYear;
    }

    public boolean getCheckOutStatus() {
        return this.isAvailStatus;
    }


    public String listBookDetail() {
        return (this.bookDetails != null) ? bookDetails.substring(0, 38) : null;
    }

    public void checkOutBook() {
        if (this.title != null && this.author != null && this.publishYear == 0) {
            this.isAvailStatus = false;
            formBookDetails();
        }
    }

    public void returnBook() {
        this.isAvailStatus = true;
        formBookDetails();
    }
}
