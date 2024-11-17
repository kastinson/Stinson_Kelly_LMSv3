# Library Management System (LMS)

## Overview
The **Library Management System (LMS)** is a command-line application designed to manage a library's collection of books. It allows library staff or users to check books in, check books out, and display the list of available books. The system uses a CSV file to store book data, making it easy to load, manage, and update the book inventory.

The LMS is built using the **Model-View-Controller (MVC)** architectural pattern to ensure a clean separation of concerns, making it easier to maintain and extend.

## Features
- **Add a Book**: Allow a user to add a single book to the collection.
- **Remove a Book**: Allows a user to delete a book from the collection by entering its barcode or title.
- **Check Out a Book**: Allows a user to check out a book for 4 weeks by entering its barcode.
- **Check In a Book**: Allows a user to check in a book that was previously checked out.
- **Display Books**: Displays all books in the collection, including details like title, author, genre, barcode, status (Checked In or Checked Out), and due date (if applicable).
- **Load Books from File**: Load books into the collection from csv file.

## Technologies Used
- **Java**: Core programming language used for building the application.
- **CSV File**: Used for persisting the list of books.
- **Model-View-Controller (MVC)**: Design pattern followed for better separation of concerns and maintainability.

## Prerequisites
- **Java 7 or later**: Make sure you have Java Development Kit (JDK) installed on your machine.
- **Text Editor or IDE**: You can use any text editor (e.g., Notepad++, VS Code) or an IDE (e.g., IntelliJ IDEA, Eclipse) to work with the source code.

## Setup Instructions

1. **Clone the Repository**
   ```
   git clone [https://github.com/ykastinson/Stinson_Kelly_LMS]
   ```

2. **Ensure you have a CSV File**
   - The application expects a CSV file containing the book data. Ensure you have a file in the root directory ro you enter the full path to the file.
   - The CSV file should have the following columns:
     - `Title, Author, Barcode, Genre, Status, DueDate`
   - Example content of the `books.csv` file:
     ```
     Harry Potter,J.K. Rowling,123456,Fiction,Checked In,
     The Hobbit,J.R.R. Tolkien,789101,Fantasy,Checked Out,2024-12-12
     ```

3. **Compile the Program**
   Open your terminal or command prompt, navigate to the project folder, and compile the Java files:
   ```bash
   javac -d bin src/*.java
   ```

4. **Run the Program**
   After compilation, you can run the program:
   ```bash
   java -cp bin LibraryManagementSystem
   ```

5. **Interactive Menu**
   Once the program starts, it will display a menu with the following options:
   - Add a book
   - Remove a book
   - Check out a book by barcode and assign a due date.
   - Check in a book by barcode.
   - Display all books and their current status (checked in or out).
   - Load books from a file
   - Exit

## File Structure

```
|-- src/
|   |-- Book.java              # The Book class (Model)
|   |-- BookDatabase.java      # Manages the collection of books (Model)
|   |-- LMSController.java     # The Controller that processes user actions
|   |-- MainView.java          # The View that handles user interaction (CLI)
|   |-- LibraryManagementSystem.java  # Main class to start the application
|-- books.csv                  # CSV file containing the book data
|-- README.md                  # This ReadMe file
```

## Usage

### 1. Add a Book
- Ypu will be propted to add the Title, Author, Barcode, and Genre of the book.
- The system will add the book to the collection with the status "Checked In."

### 2. Remove a Book
- You will be prompted to enter the barcode or title of the book.
- The system will delete the book from the collection.

### 3. Check Out a Book
- You will be prompted to enter the barcode of the book.
- The system will check if the book is available and, if so, mark it as checked out with the specified due date.

### 4. Check In a Book
- You will be prompted to enter the barcode of the book.
- If the book is checked out, the system will mark it as checked in and clear its due date.

### 5. Display All Books
- You can view the entire book collection with details such as title, author, barcode, genre, status (checked in or out), and due date (if applicable).

### 6. Load Books from a File
- You will be prompted for the file path and name of the csv file.
- The system will add the books to the collection.

## Contribution

Contributions are welcome! Feel free to fork this repository, make your changes, and submit a pull request. Please make sure that your code is well-documented and follows the project structure.

## License

This project is licensed under the [MIT License](LICENSE).

## Contact

For any queries, feel free to reach out to:
- **GitHub**: [Your GitHub Profile](https://github.com/kastinson)
