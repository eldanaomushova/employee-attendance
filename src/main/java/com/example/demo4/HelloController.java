package com.example.demo4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    private Label author;
    @FXML
    private ImageView image;
    @FXML
    private Label name;

    @FXML
    private TextField inputEmail;
    @FXML
    private TextField inputPassword;
    private List<String> recommendations = new ArrayList<>();
    Randomblock randomblock = new Randomblock();
    private Stage stage;
    private Scene scene;
    private Parent root;
    MusicDAO musicDAO = new MusicDAO();
    ObservableList<LogInPage> logInPages = FXCollections.observableArrayList();

//    public void onButtonLogIn(ActionEvent event) throws IOException  {
//        LogInPage logInPage = new LogInPage();
//        logInPage.setEmail(inputEmail.getText());
//        logInPage.setPassword(inputPassword.getText());
//
//        try {
//            if (musicDAO.isEmailInDatabase(logInPage.getEmail()) && musicDAO.isPasswordInDatabase(logInPage.getPassword())) {
//
//
//            }
//            else{
//                alert("error");
//            }
//        } catch (Exception e) {
//            // Catching any exceptions that might occur during database operations
//            e.printStackTrace();
//        }
//    }



    private void alert(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(error);
        alert.showAndWait();
    }

    public void switchToScene1(ActionEvent event) throws IOException {
        Object root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene2(ActionEvent event) throws IOException {
        LogInPage logInPage = new LogInPage(inputEmail.getText(), inputPassword.getText());
        logInPage.setEmail(inputEmail.getText());
        logInPage.setPassword();

        try {
            if (musicDAO.isPasswordInDatabase(logInPage)) {
                Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                alert("Error: Password not found in the database");
            }
        } catch (Exception e) {
            // Catching any exceptions that might occur during database operations
            e.printStackTrace();
        }
    }

}