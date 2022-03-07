package com.main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GraphicsHandler {
    public ObservableList<String> animals = FXCollections.observableArrayList("Cow", "Pig", "Penguin", "Sloth", "Hallucigenia", "Blue Dragon", "Basilisk", "Probiscus Monkey");
    public Label Title;
    public AnchorPane player1;
    public AnchorPane player2;
    public AnchorPane player3;
    public AnchorPane player4;
    public Label die1;
    public Label die2;
    public Button removePlayer;
    public Button cont;
    public Button addPlayer;
    public ChoiceBox<String> animalChoiceBox1;
    public ChoiceBox<String> animalChoiceBox2;
    public ChoiceBox<String> animalChoiceBox3;
    public ChoiceBox<String> animalChoiceBox4;
    public ImageView animalImage1;
    public ImageView animalImage2;
    public ImageView animalImage3;
    public ImageView animalImage4;

    int playerCount;
    public GraphicsHandler() {
        playerCount = 0;
    }

    @FXML
    protected void onStartGameButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(GraphicsHandler.class.getResource("resources/PlayerSelect.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280.0D, 720.0D);
        stage.setScene(scene);

    }

    @FXML
    protected void addPlayerButton() {
        playerCount += 1;
        switch (playerCount) {
            case 1 -> {
                player1.setOpacity(1);
                animalChoiceBox1.setItems(animals);
                removePlayer.setDisable(false);
            }
            case 2 -> {
                player2.setOpacity(1);
                animalChoiceBox2.setItems(animals);
                cont.setDisable(false);
            }
            case 3 -> {
                player3.setOpacity(1);
                animalChoiceBox3.setItems(animals);
            }
            case 4 -> {
                player4.setOpacity(1);
                animalChoiceBox4.setItems(animals);
                addPlayer.setDisable(true);
            }
        }
    }

    @FXML
    protected void removePlayerButton() {
        switch (playerCount) {
            case 1 -> {
                player1.setOpacity(0);
                removePlayer.setDisable(true);
            }
            case 2 -> {
                player2.setOpacity(0);
                cont.setDisable(true);
            }
            case 3 -> player3.setOpacity(0);
            case 4 -> {
                player4.setOpacity(0);
                addPlayer.setDisable(false);
            }
        }
        playerCount -= 1;
    }

    @FXML
    protected void continueToGame(ActionEvent event) throws IOException, URISyntaxException {
        GameHandler.gameSetup();
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(GraphicsHandler.class.getResource("resources/GameBoard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280.0D, 720.0D);
        stage.setScene(scene);
    }

    @FXML
    protected void rollDice() throws InterruptedException {
        int diceNumber;
        diceNumber = GameHandler.Rolldice();
        die1.setText(Integer.toString(diceNumber));
        diceNumber = GameHandler.Rolldice();
        die2.setText(Integer.toString(diceNumber));
    }

    @FXML
    protected void changeImage(ActionEvent event) throws URISyntaxException {
        String boxName = ((Node) event.getSource()).getId();
        Image img;
        switch (boxName){
            case "animalChoiceBox1":
                img = new Image(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("images")).toURI() + animalChoiceBox1.getValue() + ".jpg");
                animalImage1.setImage(img);
                break;
            case "animalChoiceBox2":
                img = new Image(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("images")).toURI() + animalChoiceBox2.getValue() + ".jpg");
                animalImage2.setImage(img);
                break;
            case "animalChoiceBox3":
                img = new Image(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("images")).toURI() + animalChoiceBox3.getValue() + ".jpg");
                animalImage3.setImage(img);
                break;
            case "animalChoiceBox4":
                img = new Image(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("images")).toURI() + animalChoiceBox4.getValue() + ".jpg");
                animalImage4.setImage(img);
                break;
        }
    }

}