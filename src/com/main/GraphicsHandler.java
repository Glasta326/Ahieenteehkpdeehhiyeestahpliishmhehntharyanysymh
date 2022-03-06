package com.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import javafx.stage.Stage;

public class GraphicsHandler {
    public Label Title;
    public Label player1;
    public Label player2;
    public Label player3;
    public Label player4;
    public Button removePlayer;
    public Button cont;

    int playerCount;
    public GraphicsHandler() {
        playerCount = 0;
    }

    @FXML
    protected void onStartGameButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(GraphicsHandler.class.getResource("resources/PlayerSelect.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1120.0D, 630.0D);
        stage.setScene(scene);
    }

    @FXML
    protected void addPlayerButton() {
        playerCount += 1;
        switch (playerCount) {
            case 1 -> {
                player1.setOpacity(1);
                removePlayer.setDisable(false);
            }
            case 2 -> {
                player2.setOpacity(1);
                cont.setDisable(false);
            }
            case 3 -> player3.setOpacity(1);
            case 4 -> player4.setOpacity(1);
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
            case 4 -> player4.setOpacity(0);
        }
        playerCount -= 1;
    }

    @FXML
    protected void continueToGame() {

    }

}