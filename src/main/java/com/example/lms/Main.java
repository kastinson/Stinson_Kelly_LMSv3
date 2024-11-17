package com.example.lms;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

 /**
 * ===================================================================================
 *                          Library Management System (LMS)
 * ===================================================================================
 *
 * Author: Kelly Stinson
 * Course: CEN-3024C-14320 Software Development I
 * Date: 10-06-2024
 * Class: Main
  *
 * Project Description:
 * The Library Management System (LMS) is a software application designed to
 * manage library operations efficiently. It provides functionalities for managing books
 * and inventory, including tracking statuses (e.g., Checked In/Out).
 *
 * This Java application uses JavaFX to deliver an intuitive and user-friendly
 * graphical user interface (GUI). The GUI offers a seamless experience for managing
 * all library operations, from book search to user management, with interactive
 * elements and responsive design.
 *
 * The application integrates with a MySQL database to store and manage data,
 * ensuring reliability and scalability for library operations of varying sizes.
 *
 * ===================================================================================
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

