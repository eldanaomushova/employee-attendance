package com.example.demo4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HelloController {
    @FXML
    private Label author;
    @FXML
    private ImageView image;
    @FXML
    private Label name;

    @FXML
    private Label inputEmail;
    @FXML
    private Label inputPassword;
    private List<String> recommendations = new ArrayList<>();
    Randomblock randomblock = new Randomblock();
    private Stage stage;
    private Scene scene;
    private Parent root;
    MusicDAO musicDAO = new MusicDAO();

    public void onButtonLogIn(ActionEvent event) throws IOException  {
        LogInPage logInPage = new LogInPage();
        logInPage.setEmail(inputEmail.getText());
        logInPage.setPassword(inputPassword.getText());

        try {

            if (musicDAO.isEmailInDatabase(logInPage.getEmail()) && musicDAO.isPasswordInDatabase(logInPage.getPassword())) {
                Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
        } catch (Exception e) {
            // Catching any exceptions that might occur during database operations
            e.printStackTrace();
        }
    }



    public void switchToScene1(ActionEvent event) throws IOException {
        Object root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}