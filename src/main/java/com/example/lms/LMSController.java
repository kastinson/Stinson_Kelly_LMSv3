package com.example.lms;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.File;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Author: Kelly Stinson
 * Course: CEN-3024C-14320 Software Development I
 * Date: 10-06-2024
 * Class: LMSController
 *
 * This class serves as the controller in the MVC architecture. It mediates between the view and
 * the model, processing user input and updating the model or view accordingly.
 */
public class LMSController {
    private final BookDatabase bookDatabase = new BookDatabase();

    /**
     * Opens the Add Book window, allowing users to input details about a new book.
     * This method loads the AddBookView.fxml file and sets up the AddBookController
     * with a reference to the BookDatabase. The new window allows users to add a new book.
     */
    @FXML
    private void addBook() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddBookView.fxml"));
            Parent root = loader.load();

            AddBookController controller =loader.getController();
            controller.setBookDatabase(bookDatabase);

            Stage stage = new Stage();
            stage.setTitle("Add Book");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action for the "Remove Book" button.
     * Prompts the user to enter the title or barcode of a book to remove,
     * and attempts to remove the specified book from the BookDatabase.
     * A confirmation or error message is displayed based on the result.
     */
    @FXML
    private void removeBook() {
        // Prompt the user to enter the title or barcode of the book to remove
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Remove Book");
        dialog.setHeaderText("Enter Title or Barcode");
        dialog.setContentText("Please enter the title or barcode of the book to remove:");

        dialog.showAndWait().ifPresent(input -> {
            // Attempt to remove the book from the database
            boolean success = bookDatabase.removeBook(input);

            // Show confirmation or error message
            if (success) {
                showAlert("Book removed successfully!");
            } else {
                showAlert("Book not found. Please check the title or barcode and try again.");
            }
        });
    }

    /**
     * Handles the action for the "Check Out Book" button.
     * Prompts the user to enter the title or barcode of a book to check out,
     * and attempts to check out the specified book by updating its status and setting a due date.
     * Displays a success or error message based on the result.
     */
    @FXML
    private void checkOutBook() {
        // Prompt the user to enter the title or barcode of the book to check out
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Check Out Book");
        dialog.setHeaderText("Enter Title or Barcode");
        dialog.setContentText("Please enter the title or barcode of the book to check out:");

        dialog.showAndWait().ifPresent(input -> {
            // Attempt to check out the book in the database
            boolean success = bookDatabase.checkOutBook(input);

            // Show confirmation or error message
            if (success) {
                showAlert("Book checked out successfully!");
            } else {
                showAlert("Book not found or already checked out. Please check the title or barcode and try again.");
            }
        });
    }

    /**
     * Handles the action for the "Check In Book" button.
     * Prompts the user to enter the title or barcode of a book to check in,
     * and attempts to check in the specified book by updating its status and clearing the due date.
     * Displays a success or error message based on the result.
     */
    @FXML
    private void checkInBook() {
        // Prompt the user to enter the title or barcode of the book to check in
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Check In Book");
        dialog.setHeaderText("Enter Title or Barcode");
        dialog.setContentText("Please enter the title or barcode of the book to check in:");

        dialog.showAndWait().ifPresent(input -> {
            // Attempt to check in the book in the database
            boolean success = bookDatabase.checkInBook(input);

            // Show confirmation or error message
            if (success) {
                showAlert("Book checked in successfully!");
            } else {
                showAlert("Book not found or is already checked in. Please check the title or barcode and try again.");
            }
        });
    }

    /**
     * Opens the View All Books window, displaying the list of all books in a table format.
     * This method loads the BookTableView.fxml file and sets up the BookTableController
     * with a reference to the BookDatabase, so it can retrieve and display the list of books.
     */
    @FXML
    private void viewBooks() {
        try {
            // Load the FXML file for viewing books
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewBooks.fxml"));
            Parent root = loader.load();

            // Get the controller instance and set BookDatabase
            ViewBooksController controller = loader.getController();
            controller.setBookDatabase(bookDatabase);

            // Show the books in a new window
            Stage stage = new Stage();
            stage.setTitle("View All Books");
            Scene scene = new Scene(root, 800, 600);  // Set width to 800 and height to 600
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a file chooser dialog to allow the user to load books from a file.
     * When a file is selected, it is passed to BookDatabase for loading.
     * If successful, a success alert is shown; otherwise, an error message is displayed.
     */
    @FXML
    private void loadBooksFromFile() {
        // Create a FileChooser to let the user select a file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Book File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        // Show the file chooser and get the selected file
        Stage stage = new Stage();  // You may want to pass the primary stage if accessible
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            String filePath = file.getPath();
            try {
                // Load books from the selected file
                bookDatabase.loadBooksFromFile(filePath);
                showAlert("Books loaded successfully from " + file.getName());
            } catch (Exception e) {
                // Show error alert if loading fails
                showAlert("Failed to load books from file: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Closes the application when the "Exit" button is clicked.
     * This method gracefully shuts down the JavaFX application by calling Platform.exit().
     * Use this method as the event handler for the "Exit" button in the MainView.
     */
    @FXML
    private void exitApplication() {
        // Exit the application
        Platform.exit();
    }

    /**
     * Displays an informational alert dialog with the specified message.
     * Can be used to notify the user of the result of an operation, such as
     * successful loading, adding, or removal of a book.
     *
     * @param message the message to display in the alert
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }
}

