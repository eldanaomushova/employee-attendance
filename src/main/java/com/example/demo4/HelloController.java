package com.example.demo4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    private List<String> recommendations = new ArrayList<>();
    Randomblock randomblock = new Randomblock();

    @FXML
    void onAddTolibrary(ActionEvent event) {

    }
    public void setDataOfRecommandations(){
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(randomblock.getBlockimage())));
        randomblock.setBlockimage(image);

    }


    @FXML
    void onDeleteFromRecommendation(ActionEvent event) {
        if (!recommendations.isEmpty()) {
            String deletedItem = recommendations.remove(0); // Assuming you want to remove the first item
            System.out.println("Deleted from recommendations: " + deletedItem);
            // You can update your UI or perform other actions based on the deletion
        } else {
            System.out.println("No items in recommendations to delete.");
        }

    }
}