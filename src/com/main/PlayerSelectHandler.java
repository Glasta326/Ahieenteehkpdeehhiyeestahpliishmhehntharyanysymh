package com.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class PlayerSelectHandler implements Initializable {
    @FXML private final ObservableList<String> animals = FXCollections.observableArrayList("Cow", "Pig", "Penguin", "Sloth", "Hallucigenia", "Blue Dragon", "Basilisk", "Probiscus Monkey");
    @FXML private AnchorPane player1;
    @FXML private AnchorPane player2;
    @FXML private AnchorPane player3;
    @FXML private AnchorPane player4;
    @FXML private Button removePlayer;
    @FXML private Button cont;
    @FXML private Button addPlayer;
    @FXML private ChoiceBox<String> animalChoiceBox1;
    @FXML private ChoiceBox<String> animalChoiceBox2;
    @FXML private ChoiceBox<String> animalChoiceBox3;
    @FXML private ChoiceBox<String> animalChoiceBox4;
    @FXML private ImageView animalImage1;
    @FXML private ImageView animalImage2;
    @FXML private ImageView animalImage3;
    @FXML private ImageView animalImage4;
    public ArrayList<String> animalChoices = new ArrayList<>();
    int readycount = 0;
    private int playerCount;

    public void retrieveData(int playerCount){

    }

    @FXML
    // Fires after the players have selected their animals and pressed the continue to game button
    protected void continueToGame(ActionEvent event) throws IOException, URISyntaxException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        animalChoices.add(animalChoiceBox1.getValue());
        animalChoices.add(animalChoiceBox2.getValue());
        animalChoices.add(animalChoiceBox3.getValue());
        animalChoices.add(animalChoiceBox4.getValue());
        GameHandler.gameSetup(playerCount, animalChoices);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("resources/GameBoard.fxml")));
        Parent root = loader.load();

        GameBoardHandler controller = loader.getController();
        controller.retrieveData(playerCount);
        controller.initGame(root, animalChoices, stage);
    }

    @FXML
    protected void addPlayerButton() {
        cont.setDisable(true);
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
        dochecks();
    }

    @FXML
    protected void removePlayerButton() {
        switch (playerCount) {
            case 1 -> {
                animalChoiceBox1.setValue(null);
                player1.setOpacity(0);
                removePlayer.setDisable(true);

            }
            case 2 -> {
                animalChoiceBox2.setValue(null);
                player2.setOpacity(0);
                cont.setDisable(true);
            }
            case 3 -> {
                animalChoiceBox3.setValue(null);
                player3.setOpacity(0);
            }
            case 4 -> {
                animalChoiceBox4.setValue(null);
                player4.setOpacity(0);
                addPlayer.setDisable(false);
            }
        }
        dochecks();
        if (playerCount ==  readycount)
            readycount -= 1;
        playerCount -= 1;
        if (readycount == playerCount){
            cont.setDisable(false);
        }
    }
    public void dochecks(){
        if (Objects.equals(animalChoiceBox1.getValue(), animalChoiceBox2.getValue()) || Objects.equals(animalChoiceBox1.getValue(), animalChoiceBox3.getValue()) || Objects.equals(animalChoiceBox1.getValue(), animalChoiceBox4.getValue()) && animalChoiceBox1.getValue() != null){
            cont.setDisable(true);
        }
        else if (Objects.equals(animalChoiceBox2.getValue(), animalChoiceBox3.getValue()) || Objects.equals(animalChoiceBox2.getValue(), animalChoiceBox4.getValue()) && animalChoiceBox2.getValue() != null){
            cont.setDisable(true);
        }
        else if (Objects.equals(animalChoiceBox3.getValue(), animalChoiceBox4.getValue()) && animalChoiceBox3.getValue() != null){
            cont.setDisable(true);
        }
        else{
            cont.setDisable(false);
        }
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
                if (animalChoiceBox1 != null) {
                    readycount += 1;
                }
                if (readycount == playerCount){
                    cont.setDisable(false);
                }
                dochecks();
                break;
            case "animalChoiceBox2":
                img = new Image(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("images")).toURI() + animalChoiceBox2.getValue() + ".jpg");
                animalImage2.setImage(img);
                if (animalChoiceBox2 != null) {
                    readycount += 1;
                }
                if (readycount == playerCount){
                    cont.setDisable(false);
                }
                dochecks();
                break;
            case "animalChoiceBox3":
                img = new Image(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("images")).toURI() + animalChoiceBox3.getValue() + ".jpg");
                animalImage3.setImage(img);
                if (animalChoiceBox3 != null) {
                    readycount += 1;
                }
                if (readycount == playerCount){
                    cont.setDisable(false);
                }
                dochecks();
                break;
            case "animalChoiceBox4":
                img = new Image(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("images")).toURI() + animalChoiceBox4.getValue() + ".jpg");
                animalImage4.setImage(img);
                if (animalChoiceBox4 != null) {
                    readycount += 1;
                }
                if (readycount == playerCount){
                    cont.setDisable(false);
                }
                dochecks();
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playerCount = 0;
    }
}
