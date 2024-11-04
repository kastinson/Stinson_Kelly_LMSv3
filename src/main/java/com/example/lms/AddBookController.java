package com.example.lms;

import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.control.Alert;

/**
 * Author: Kelly Stinson
 * Course: CEN-3024C-14320 Software Development I
 * Date: 11-03-2024
 * Class: Book
 *
 * AddBookController is the controller class for the Add Book view in the Library Management System.
 * This class manages the behavior of the Add Book form, allowing users to input details about a new book
 * and add it to the library's database. It is linked with the AddBookView.fxml file, which defines the
 * UI layout for adding a new book.
 *
 * Note: The BookDatabase object must be set externally using setBookDatabase(BookDatabase bookDatabase)
 * to ensure the controller has access to the main database.
 */
public class AddBookController {

    @FXML
    private Pane formContainer;

    private BookDatabase bookDatabase;
    private Form form;
    private Book book;

    public AddBookController() {
        // Default constructor required by FXMLLoader
    }

    /**
     * Initializes the AddBookController and sets up the form within the formContainer.
     * This method is automatically called after the FXML file has been loaded.
     * It binds the book's properties to form fields, allowing user input.
     */
    @FXML
    public void initialize() {
        showAddBookForm();
    }

    /**
     * Displays the form for adding a new book using the FormFX library.
     * Initializes the form fields with an empty Book object and binds
     * the fields to the book's properties. Adds the form to the formContainer Pane.
     */
    @FXML
    private void showAddBookForm() {
        book = new Book("", "", "", "", "Checked In", null);
        form = Form.of(
                Group.of(
                        Field.ofStringType(book.titleProperty()).label("Title"),
                        Field.ofStringType(book.authorProperty()).label("Author"),
                        Field.ofStringType(book.barcodeProperty()).label("Barcode"),
                        Field.ofStringType(book.genreProperty()).label("Genre"),
                        Field.ofStringType(book.statusProperty()).label("Status"),
                        Field.ofStringType(book.dueDateProperty()).label("Due Date")
                )
        );

        formContainer.getChildren().add(new FormRenderer(form));
    }

    /**
     * Handles the "Add Book" button action.
     * Validates the form, then creates a new Book object based on user input
     * and adds it to the BookDatabase. Displays an alert with a success or error message.
     */
    @FXML
    public void handleAddBook() {
        if (bookDatabase == null) {
            showAlert("Book database is not initialized.");
            return;
        }

        if (form.isValid()) {
            bookDatabase.addBook(new Book(
                    book.getTitle(),
                    book.getAuthor(),
                    book.getBarcode(),
                    book.getGenre(),
                    book.getStatus(),
                    book.getDueDate() != null ? book.getDueDate() : null
            ));

            showAlert("Book added successfully!");
            form.reset();
        } else {
            showAlert("Please fill out all required fields.");
        }
    }

    /**
     * Sets the BookDatabase instance to allow this controller to add books.
     * This method should be called externally before attempting to add books
     * to ensure the database is available.
     *
     * @param bookDatabase the BookDatabase instance used to store book records
     */
    public void setBookDatabase(BookDatabase bookDatabase) {
        this.bookDatabase = bookDatabase;
    }

    /**
     * Displays an informational alert dialog with the given message.
     *
     * @param message the message to display in the alert
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}