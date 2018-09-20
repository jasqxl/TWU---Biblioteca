package com.twu.biblioteca;

import org.junit.*;
import java.io.*;
import static org.junit.Assert.*;

public class BookListTest {

    private Book emptyBook = new Book();
    private Book testBookAttribute = new Book("Lord of the Rings", "ME", 1994, false);
    private Book testBookDetail = new Book("Lord of the Rings 2           |Uncle               |2018    |true");

    @Test
    public void testRetrieveBookList() {
    }

    @Test
    public void testListBooks() {
    }

    @Test
    public void testCheckOutABook() {
    }

    @Test
    public void testReturnABook() {
    }

    @Test
    public void testGetBookList() {
    }

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testCheckOutBook() {
        assertTrue(emptyBook.getCheckOutStatus());
        emptyBook.checkOutBook();
        assertTrue(emptyBook.getCheckOutStatus());
        //assertEquals(unsuccessfulCheckOutMessage, outContent);

        assertFalse(testBookAttribute.getCheckOutStatus());
        testBookAttribute.checkOutBook();
        assertFalse(testBookAttribute.getCheckOutStatus());
        //assertEquals(unsuccessfulCheckOutMessage, outContent);

        assertTrue(testBookDetail.getCheckOutStatus());
        testBookDetail.checkOutBook();
        assertFalse(testBookDetail.getCheckOutStatus());
        //assertEquals(successfulCheckOutMessage, outContent);
    }

    @Test
    public void testReturnBook() {
        assertTrue(emptyBook.getCheckOutStatus());
        emptyBook.returnBook();
        assertTrue(emptyBook.getCheckOutStatus());
        //assertEquals(unsuccessfulReturnMessage, outContent);

        assertFalse(testBookAttribute.getCheckOutStatus());
        testBookAttribute.returnBook();
        assertTrue(testBookAttribute.getCheckOutStatus());
        //assertEquals(successfulReturnMessage, outContent);

        assertTrue(testBookDetail.getCheckOutStatus());
        testBookDetail.returnBook();
        assertTrue(testBookDetail.getCheckOutStatus());
        //assertEquals(unsuccessfulReturnMessage, outContent);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}