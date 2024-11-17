package com.example.lms;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Author: Kelly Stinson
 * Course: CEN-3024C-14320 Software Development I
 * Date: 11-03-2024
 * Class: AddBookController
 *
 * AddBookController is the controller class for the Add Book view in the Library Management System.
 * This class manages the behavior of the Add Book form, allowing users to input details about a new book
 * and add it to the library's database. It is linked with the AddBookView.fxml file, which defines the
 * UI layout for adding a new book.
 *
 * Note: The BookDatabase object must be set externally using setBookDatabase(BookService bookDatabase)
 * to ensure the controller has access to the main database.
 */
public class AddBookController {

    @FXML
    private GridPane formContainer;

    @FXML
    private TextField barcodeField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField authorField;

    @FXML
    private TextField genreField;

    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private DatePicker dueDatePicker;

    // Reference to the database service
    private BookService bookService;

    /**
     * Initializes the controller and ensures all required fields are ready for input.
     */
    @FXML
    public void initialize() {
        System.out.println("formContainer: " + formContainer); // Should not be null
        if (formContainer == null) {
            throw new IllegalStateException("Form container (GridPane) is not initialized. Check your FXML file.");
        }

        // Populate the status ComboBox with predefined values
        statusComboBox.getItems().addAll("Checked In", "Checked Out");
        statusComboBox.setValue("Checked In"); // Set default value
        System.out.println("AddBookController initialized successfully.");
    }

    /**
     * Sets the book database service.
     *
     * @param bookService The database service to be used for storing book information.
     */
    public void setBookDatabase(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Handles the action of adding a new book.
     * Validates the input and performs the required operations to save the book data.
     */
    @FXML
    private void handleAddBook() {
        // Retrieve user input from fields
        String barcode = barcodeField.getText().trim();
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();
        String genre = genreField.getText().trim();
        String status = statusComboBox.getValue(); // Get the selected status
        String dueDate = (dueDatePicker.getValue() != null) ? dueDatePicker.getValue().toString() : null;

        // Validate required fields
        if (barcode.isEmpty() || title.isEmpty() || author.isEmpty()) {
            showAlert("Validation Error", "Barcode, Title, and Author fields are required.");
            return;
        }

        // Create a Book object
        Book book = new Book(barcode, title, author, genre, status, dueDate);

        // Save the book using the BookService
        try {
            bookService.addBook(book);
        } catch (Exception e) {
            showAlert("Error", "Failed to add the book: " + e.getMessage());
        }

        // Clear the form after successful operation
        clearForm();

        // Inform the user of success
        showAlert("Success", "Book added successfully!");
    }

    /**
     * Closes the form.
     */
    @FXML
    private void handleClose() {
        // Get the current stage and close it
        Stage stage = (Stage) formContainer.getScene().getWindow();
        stage.close();
    }

    /**
     * Clears all input fields in the form.
     */
    private void clearForm() {
        barcodeField.clear();
        titleField.clear();
        authorField.clear();
        genreField.clear();
        statusComboBox.setValue("Checked In"); // Reset to default value
        dueDatePicker.setValue(null);
    }

    /**
     * Displays an alert dialog to inform the user of messages or errors.
     *
     * @param title   The title of the alert.
     * @param message The message to be displayed.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}