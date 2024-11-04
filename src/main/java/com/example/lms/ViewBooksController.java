package com.example.lms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Author: Kelly Stinson
 * Course: CEN-3024C-14320 Software Development I
 * Date: 11-03-2024
 * Class: LibraryManagementSystem
 *
 * ViewBooksController is the controller class for managing the display of all books in the library's collection
 * within the Library Management System. This class is linked to the ViewBooksView.fxml file, which defines the
 * layout for a table view that displays a list of books, allowing users to view, sort, and select individual
 * book records.
 */
public class ViewBooksController {

    @FXML
    private TableView<Book> booksTable;

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

    private ObservableList<Book> booksList = FXCollections.observableArrayList();

    /**
     * Initializes the ViewBooksController and sets up the table view with data from the BookDatabase.
     * This method is automatically called by the JavaFX framework after the FXML file has been loaded.
     *
     * It configures the columns of the table, binds them to the book properties, and loads the data
     * to be displayed in the table. This setup allows for sorting and filtering within the table view.
     */
    @FXML
    public void initialize() {
        // Set up columns to use Book properties
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        barcodeColumn.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        // Populate the TableView with the list of books
        booksTable.setItems(booksList);
    }

    public ViewBooksController() {
        // Default constructor required by FXMLLoader
    }

    public ViewBooksController(BookDatabase bookDatabase) {
        // Initialize the ObservableList with books from the database
        this.booksList = FXCollections.observableArrayList(bookDatabase.getBooks());
    }

    /**
     * Sets the BookDatabase instance for this controller, allowing it to retrieve and display
     * the list of books. This method should be called externally to provide the necessary
     * database reference before displaying the table view.
     *
     * @param bookDatabase the BookDatabase instance containing the library's book records
     */
    public void setBookDatabase(BookDatabase bookDatabase) {
        // Refresh the ObservableList from the updated BookDatabase
        this.booksList.setAll(bookDatabase.getBooks());
    }

    /**
     * Refreshes the list of books displayed in the view by reloading data from the given BookDatabase.
     * This method updates the booksList with the latest records from the database, ensuring that
     * any additions, removals, or updates to the library's collection are reflected in the list view.
     *
     * @param bookDatabase the BookDatabase instance containing the current list of books
     */
    public void refreshBooksList(BookDatabase bookDatabase) {
        booksList.setAll(bookDatabase.getBooks());
    }
}

