package com.example.lms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Kelly Stinson
 * Course: CEN-3024C-14320 Software Development I
 * Date: 10-20-2024
 * Class: BookDatabaseTest
 *
 * This class contains a series of unit tests to verify the functionality of the BookDatabase class
 * in the Library Management System (LMS). Each test ensures that the core features of the system,
 * such as adding, removing, checking out, and checking in books, behave as expected.
 *
 * The tests include:
 * - Loading books from a CSV file into the database.
 * - Adding a book to the database programmatically (using CLI simulation).
 * - Removing a book by its barcode or title.
 * - Checking out a book and verifying the status change and due date assignment.
 * - Checking in a book and verifying the status change and clearing the due date.
 *
 * These tests help ensure the integrity of the LMS functionality as it interacts with the BookDatabase.
 *
 * JUnit is used for writing and running the tests. The setUp() method is used to initialize a fresh
 * BookDatabase instance before each test to ensure isolation between tests.
 */
class BookDatabaseTest {

    private BookDatabase bookDatabase;

    @BeforeEach
    void setUp() {
        bookDatabase = new BookDatabase();
    }

    @Test
    @DisplayName("Load Books from File Test")
    void loadBooksFromFile() {
        // Create a sample CSV file for testing
        File file = new File("test_books.csv");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("The Great Gatsby,F. Scott Fitzgerald,123456789,Fiction,Checked In,\n");
            writer.write("1984,George Orwell,987654321,Dystopian,Checked Out,2024-01-10\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Load books from the CSV file
        try {
            bookDatabase.loadBooksFromFile(file.getPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Verify that the books are loaded correctly
        assertEquals(2, bookDatabase.getBooks().size(), "Error: All books not loaded");
        assertEquals("The Great Gatsby", bookDatabase.getBooks().get(0).getTitle());
        assertEquals("1984", bookDatabase.getBooks().get(1).getTitle());

        // Clean up test file
        file.delete();
    }

    @Test
    @DisplayName("Add Book using CLI Test")
    void addBook() {
        // Create a book using CLI
        Book newBook = new Book("To Kill a Mockingbird", "Harper Lee", "1234567890", "Fiction", "Checked In", null);
        bookDatabase.addBook(newBook);

        // Verify that the book is added to the database
        assertEquals(1, bookDatabase.getBooks().size());
        assertEquals("To Kill a Mockingbird", bookDatabase.getBooks().get(0).getTitle(),"Error: Book not added");
    }

    @Test
    @DisplayName("Remove Book by Barcode or Title Test")
    void removeBook() {
        // Add a sample book to the database
        Book book = new Book("The Catcher in the Rye", "J.D. Salinger", "1234567891", "Fiction", "Checked In", null);
        bookDatabase.addBook(book);

        // Remove the book using its barcode
        bookDatabase.removeBook("1234567891");

        // Verify that the book is removed
        assertEquals(0, bookDatabase.getBooks().size(), "Error: Book not removed");

        // Add a sample book to the database
        bookDatabase.addBook(book);

        // Remove the book using its title
        bookDatabase.removeBook("The Catcher in the Rye");

        // Verify that the book is removed
        assertEquals(0, bookDatabase.getBooks().size(), "Error: Book not removed");
    }

    @Test
    @DisplayName("Check Out Book Test")
    void checkOutBook() {
        // Add a sample book to the database
        Book book = new Book("Brave New World", "Aldous Huxley", "1234567892", "Fiction", "Checked In", null);
        bookDatabase.addBook(book);

        // Check out the book
        bookDatabase.checkOutBook("1234567892");

        // Verify that the book is checked out and due date is assigned
        assertEquals("Checked Out", book.getStatus(), "Error: Book not checked Out - Status is not Checked Out");
        assertNotNull(book.getDueDate(), "Error: Book not checked Out - Due Date is null");
    }

    @Test
    @DisplayName("Check In Book Test")
    void checkInBook() {
        // Add a sample book to the database and check it out
        Book book = new Book("Brave New World", "Aldous Huxley", "1234567892", "Fiction", "Checked Out", "2024-01-10");
        bookDatabase.addBook(book);

        // Check in the book
        bookDatabase.checkInBook("1234567892");

        // Verify that the book is checked in and due date is null
        assertEquals("Checked In", book.getStatus(), "Error: Book not checked In - Status is not Checked In");
        assertNull(book.getDueDate(), "Error: Book not checked In - Due Date is not null");
    }
}