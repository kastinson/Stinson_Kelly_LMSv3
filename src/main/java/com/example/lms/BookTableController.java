package com.example.lms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Author: Kelly Stinson
 * Course: CEN-3024C-14320 Software Development I
 * Date: 11-03-2024
 * Class: Book
 *
 * BookTableController is the controller class responsible for managing the Book Table view in the Library Management
 * System. This controller interacts with the BookTableView.fxml file, which defines the table layout used to display
 * a list of books.*
 */
 public class BookTableController {
    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> barcodeColumn;
    @FXML
    private TableColumn<Book, String> genreColumn;
    @FXML
    private TableColumn<Book, String> statusColumn;
    @FXML
    private TableColumn<Book, String> dueDateColumn;

    private BookDatabase bookDatabase = new BookDatabase();

    @FXML
    public void initialize() {
        ObservableList<Book> books = FXCollections.observableArrayList(bookDatabase.getBooks());
        bookTable.setItems(books);

        // Set cell value factories
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        // Repeat for other columns
    }
}
