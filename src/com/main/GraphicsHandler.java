package com.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GraphicsHandler implements Initializable {
    Scene PlayerSelect;
    @FXML private final ObservableList<String> animals = FXCollections.observableArrayList("Cow", "Pig", "Penguin", "Sloth", "Hallucigenia", "Blue Dragon", "Basilisk", "Probiscus Monkey");
    @FXML private AnchorPane player1;
    @FXML private AnchorPane player2;
    @FXML private AnchorPane player3;
    @FXML private AnchorPane player4;
    @FXML private Label die1;
    @FXML private Label die2;
    @FXML private Button removePlayer;
    @FXML private Button cont;
    @FXML private Button addPlayer;
    @FXML private ChoiceBox<String> animalChoiceBox1;
    @FXML private ChoiceBox<String> animalChoiceBox2;
    @FXML private ChoiceBox<String> animalChoiceBox3;
    @FXML private ChoiceBox<String> animalChoiceBox4;
    @FXML private ImageView playerChar1;
    @FXML private ImageView playerChar2;
    @FXML private ImageView playerChar3;
    @FXML private ImageView playerChar4;
    @FXML private ImageView animalImage1;
    @FXML private ImageView animalImage2;
    @FXML private ImageView animalImage3;
    @FXML private ImageView animalImage4;
    public ArrayList<ImageView> playerCharacters = new ArrayList<>();
    public ArrayList<ChoiceBox<String>> animalChoices = new ArrayList<>();

    int playerCount;
    public GraphicsHandler() {
        playerCount = 0;
    }

    @FXML
    protected void onStartGameButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GraphicsHandler.class.getResource("resources/PlayerSelect.fxml"));
        PlayerSelect = new Scene(fxmlLoader.load(), 1280.0D, 720.0D);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(PlayerSelect);
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
                animalChoiceBox1.setItems(null);
            }
            case 2 -> {
                player2.setOpacity(0);
                cont.setDisable(true);
                animalChoiceBox2.setItems(null);
            }
            case 3 -> {
                player3.setOpacity(0);
                animalChoiceBox3.setItems(null);
            }
            case 4 -> {
                player4.setOpacity(0);
                addPlayer.setDisable(false);
                animalChoiceBox4.setItems(null);
            }
        }
        playerCount -= 1;
    }

    @FXML
    // Fires after the players have selected their animals and pressed the continue to game button
    protected void continueToGame(ActionEvent event) throws IOException, URISyntaxException {
        FXMLLoader fxmlLoader = new FXMLLoader(GraphicsHandler.class.getResource("resources/GameBoard.fxml"));
        Scene GameBoard = new Scene(fxmlLoader.load(), 1280.0D, 720.0D);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(GameBoard);
        //Image img;
        System.out.println(playerChar1);
        animalChoices.add(animalChoiceBox1);
        animalChoices.add(animalChoiceBox2);
        animalChoices.add(animalChoiceBox3);
        animalChoices.add(animalChoiceBox4);
        GameHandler.gameSetup(playerCount, animalChoices);
        playerCharacters.add(playerChar1);
        playerCharacters.add(playerChar2);
        playerCharacters.add(playerChar3);
        playerCharacters.add(playerChar4);
        // Loading the GameBoard fxml file onto the stage
        // Setting player characters to correct animal image as well as making them visible
        //for (int i = 0; i < playerCount; i++) {
            //System.out.println(animalChoices.get(i).getValue());
            //img = new Image(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("images")).toURI() + animalChoices.get(i).getValue() + ".jpg");
            //playerChar1.setOpacity(1);
            //playerChar1.setImage(img);
        //}
    }

    @FXML
    protected void rollDice() {
        int diceNumber1;
        int diceNumber2;
        diceNumber1 = GameHandler.Rolldice();
        die1.setText(Integer.toString(diceNumber1));
        diceNumber2 = GameHandler.Rolldice();
        die2.setText(Integer.toString(diceNumber2));
    }

    @FXML
    protected void changeImage(ActionEvent event) throws URISyntaxException {
        // When a player selects an animal from drop down list, changes image below list to selected animal image
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}