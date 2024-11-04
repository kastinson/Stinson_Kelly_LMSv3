
package com.example.lms;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Author: Kelly Stinson
 * Course: CEN-3024C-14320 Software Development I
 * Date: 10-06-2024
 * Class: Book
 *
 * This class represents a book in the library system. Each book object will have a title,
 * author, barcode number, genre, status (checked in or out), and due date (if applicable).
 * The class also provides necessary methods to manage the book's attributes.
 */
public class Book {
    private final StringProperty title;
    private final StringProperty author;
    private final StringProperty barcode;
    private final StringProperty genre;
    private final StringProperty status;
    private final StringProperty dueDate;

    // Constructor
    public Book(String title, String author,  String barcode, String genre, String status, String dueDate) {
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.barcode = new SimpleStringProperty(barcode);
        this.genre = new SimpleStringProperty(genre);
        this.status = new SimpleStringProperty(status);
        this.dueDate = new SimpleStringProperty(dueDate);
    }

    // Getter for title property
    public StringProperty titleProperty() {
        return title;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    // Getter for author property
    public StringProperty authorProperty() {
        return author;
    }

    public String getAuthor() {
        return author.get();
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    // Getter for barcode property
    public StringProperty barcodeProperty() {
        return barcode;
    }

    public String getBarcode() {
        return barcode.get();
    }

    public void setBarcode(String barcode) {
        this.barcode.set(barcode);
    }

    // Getter for genre property
    public StringProperty genreProperty() {
        return genre;
    }

    public String getGenre() {
        return genre.get();
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    // Getter for status property
    public StringProperty statusProperty() {
        return status;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    // Getter for dueDate property
    public StringProperty dueDateProperty() {
        return dueDate;
    }

    public String getDueDate() {
        return dueDate.get();
    }

    public void setDueDate(String dueDate) {
        this.dueDate.set(dueDate);
    }

    /**
     * Method: checkOut
     * Purpose: Changes the status of the book to "Checked Out" and assigns a due date.
     */
//    public void checkOut() {
//        if ("Checked In".equals(status.get())) {
//            this.status.set("Checked Out");
//            // Get today's date
//            LocalDate today = LocalDate.now();
//
//            // Add 4 weeks to today's date
//            LocalDate newDate = today.plusWeeks(4);
//
//            // Define the date format
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//            // Format the new date back to string
//            this.dueDate.set(newDate.format(formatter));
//            System.out.println("Book checked out. Due: " + dueDate);
//        } else {
//            System.out.println("Book is already checked out.");
//        }
//    }

    /**
     * Method: checkInBook
     * Purpose: Changes the status of the book to "Checked In" and clears the due date.
     */
//    public void checkIn() {
//        if ("Checked Out".equals(status.get())) {
//            this.status.set("Checked In");
//            this.dueDate.set(null);
//            System.out.println("Book checked in.");
//        } else {
//            System.out.println("Book is already checked in.");
//        }
//    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", barcodeNumber='" + barcode + '\'' +
                ", genre='" + genre + '\'' +
                ", status='" + status + '\'' +
                ", dueDate=" + (dueDate != null ? dueDate : "N/A") +
                '}';
    }
}
