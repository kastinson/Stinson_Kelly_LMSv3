package com.example.lms;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * Author: Kelly Stinson
 * Course: CEN-3024C-14320 Software Development I
 * Date: 10-06-2024
 * Class: LibraryManagementSystem
 *
 * This is the main class of the Library Management System (LMS)program. It sets up the necessary
 * components (model, view, and controller) and starts the application by invoking the main menu.
 *
 * Program Objective: The Library Management System (LMS) allows users to manage a collection of
 * books, with functionalities for checking books in, checking them out, and displaying available
 * books. The program follows the MVC architecture for clean separation of concerns.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
            Scene scene = new Scene(root, 800, 600);  // Set the initial width and height here
            primaryStage.setTitle("Library Management System");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
