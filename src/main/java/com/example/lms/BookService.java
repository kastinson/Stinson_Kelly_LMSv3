package com.example.lms;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Author: Kelly Stinson
 * Course: CEN-3024C-14320 Software Development I
 * Date: 10-06-2024
 * Class: BookService
 *
 * This class manages the collection of books in the library system. A CSV file is used to initial load the list
 * and there are methods to add, remove, check out, and check in books. This class acts as the model in the MVC
 * architecture.
 */
public class BookService {
    private List<Book> books;

    // Constructor
    public BookService() throws SQLException {
        this.books = new ArrayList<>();
        BookDAO.loadBooksFromDatabase(books);
    }

    /**
     * Method: getBooks
     * Purpose: Returns the list of books currently in the database.
     *
     * @return A list of Book objects.
     */
    public List<Book> getBooks() {
        return books;
    }

    /**
     * Method: loadBooksFromFile
     * Purpose: Loads books from a CSV file into the book list.
     *
     * @param filePath The file path and name of the CSV file to load books from.
     * @throws IOException If the file cannot be read.
     */
    public void loadBooksFromFile(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String barcode = data[0];
                String title = data[1];
                String author = data[2];
                String genre = data[3];
                String status = data[4];
                String dueDate = data.length > 5 ? data[5] : null;
                Book book = new Book(title, author, barcode, genre, status, dueDate);
                // Add book to list
                books.add(book);
                //Add book to database
                Boolean result = BookDAO.insertBook(book);
            }
            System.out.println("Books loaded: " + books.size()); // Debug statement
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Method: addBook
     * Purpose: Adds a new book to the library's collection.
     *
     * @param book The book object to be added.
     */
    public void addBook(Book book) {
        System.out.println("In addBook method");
        // Add book to list
        books.add(book);
        // Add book to database
        Boolean result = BookDAO.insertBook(book);
    }

    /**
     * Removes a book from the database by either its barcode or title.
     *
     * @param identifier The book's barcode or title as a string.
     */
    public boolean removeBook(String identifier) {
        boolean result = false;;

        // First attempt to remove the book by barcode
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);

            // If identifier matches the barcode, remove the book
            if (book.getBarcode().equals(identifier)) {
                // Remove book from list
                books.remove(i);
                // Remove book from database
                result = BookDAO.deleteBook(identifier);
                return result;
            }
        }

        // If no book was found by barcode, try to remove it by title
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);

            // If identifier matches the title, remove the book
            if (book.getTitle().equalsIgnoreCase(identifier)) {
                // Remove book from list
                books.remove(i);
                // Remove book from database
                BookDAO.deleteBook(identifier);
                return result;
            }
        }

        // No match is found by either barcode or title
        return false;
    }

    /**
     * Method: checkOutBook
     * Purpose: Checks out a book from the collection using its barcode number.
     *
     * @param identifier The barcode or title of the book to be checked out.
     */
    public boolean checkOutBook(String identifier) {
        for (Book book : books) {
            // Check if the identifier matches either title or barcode
            if ((book.getTitle().equalsIgnoreCase(identifier) || book.getBarcode().equals(identifier))) {
                if ("Checked Out".equalsIgnoreCase(book.getStatus())) {
                    // Book is already checked out
                    return false;
                } else {
                    // Check out the book
                    book.setStatus("Checked Out");

                    // Get today's date
                    LocalDate today = LocalDate.now();

                    // Add 4 weeks to today's date
                    LocalDate newDate = today.plusWeeks(4);

                    // Define the date format
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    // Format the new date back to string
                    book.setDueDate(newDate.format(formatter));

                    // Update atabase
                    return BookDAO.updateBook(book);
                }
            }
        }
        return false; // Book not found
    }

    /**
     * Method: checkInBook
     * Purpose: Checks in a book to the collection using its barcode number.
     *
     * @param identifier The barcode or title of the book to be removed.
     */
    public boolean checkInBook(String identifier) {
        for (Book book : books) {
            // Check if the identifier matches either title or barcode
            if ((book.getTitle().equalsIgnoreCase(identifier) || book.getBarcode().equals(identifier))) {
                if ("Checked In".equalsIgnoreCase(book.getStatus())) {
                    // Book is already checked in
                    return false;
                } else {
                    // Check in the book
                    book.setStatus("Checked In");
                    book.setDueDate(null); // Clear the due date
                    // Update database
                    return BookDAO.updateBook(book);
                }
            }
        }
        return false; // Book not found
    }
}
