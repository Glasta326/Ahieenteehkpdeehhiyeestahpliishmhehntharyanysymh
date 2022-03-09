package com.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class GameBoardHandler implements Initializable {

    public Button diceRoller;
    public Button turnEnder;
    int diceNumber1;
    int diceNumber2;
    int currentPlayer;
    @FXML private ImageView playerChar1;
    @FXML private ImageView playerChar2;
    @FXML private ImageView playerChar3;
    @FXML private ImageView playerChar4;
    @FXML private Label die1;
    @FXML private Label die2;
    private int playerCount;
    public ArrayList<ImageView> playerCharacters = new ArrayList<>();

    public void retrieveData(int playerCount){
        this.playerCount = playerCount;
    }

    // Gets called when the player presses the "Continue to game button"
    public void initGame(Parent root, ArrayList<String> animals, Stage stage) throws URISyntaxException, IOException {
        Scene GameBoard = new Scene(root, 1280.0D, 720.0D);
        stage.setScene(GameBoard);
        Image img;
        playerCharacters.add(playerChar1);
        playerCharacters.add(playerChar2);
        playerCharacters.add(playerChar3);
        playerCharacters.add(playerChar4);
        // Activating necessary player characters
        for (int i = 0; i < playerCount; i++) {
            img = new Image(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("images")).toURI() + animals.get(i) + ".jpg");
            playerCharacters.get(i).setOpacity(1);
            playerCharacters.get(i).setImage(img);
        }
        currentPlayer = 0;
        GameHandler.setCurrentPlayer(currentPlayer);
    }

    @FXML
    // Activates after the player presses the end turn button, can only be activated after the player has rolled the dice.
    protected void endTurn(){
        Player current = GameHandler.returnCurrentPlayer();
        current.hasRolled = false;
        if (currentPlayer == playerCount - 1){
            currentPlayer = 0;
        } else {
            currentPlayer += 1;
        }
        GameHandler.setCurrentPlayer(currentPlayer);
        current = GameHandler.returnCurrentPlayer();
        diceRoller.setDisable(false);
        turnEnder.setDisable(true);
        current.hasRolled = false;
    }

    @FXML
    protected void rollDice() {
        Player currentPlayer = GameHandler.returnCurrentPlayer();
        // checks to see if player has already rolled dice, then rolls dice
            if (!currentPlayer.hasRolled) {
                diceNumber1 = GameHandler.Rolldice();
                die1.setText(Integer.toString(diceNumber1));
                diceNumber2 = GameHandler.Rolldice();
                die2.setText(Integer.toString(diceNumber2));
                movePlayer();
                diceRoller.setDisable(true);
                turnEnder.setDisable(false);
                currentPlayer.hasRolled = true;
            }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    // When a player rolls dice, moves their character the appropriate number of spaces.
    private void movePlayer(){
        Player currentPlayer = GameHandler.returnCurrentPlayer();
        for (int i = 0; i < diceNumber1 + diceNumber2; i++) {
            currentPlayer.index += 1;
            if (currentPlayer.index > 32){
                currentPlayer.index = 1;
            }
            // Checks to see what lane of the board they are on and moves accordingly
            if (1 <= currentPlayer.index && currentPlayer.index < 9){
                playerCharacters.get(currentPlayer.returnplayerNum()).setTranslateX(playerCharacters.get(currentPlayer.returnplayerNum()).getTranslateX() + 78);
            } else if (9 <= currentPlayer.index && currentPlayer.index < 17){
                playerCharacters.get(currentPlayer.returnplayerNum()).setTranslateY(playerCharacters.get(currentPlayer.returnplayerNum()).getTranslateY() + 78);
            } else if (17 <= currentPlayer.index && currentPlayer.index < 25) {
                playerCharacters.get(currentPlayer.returnplayerNum()).setTranslateX(playerCharacters.get(currentPlayer.returnplayerNum()).getTranslateX() - 78);
            } else if (25 <= currentPlayer.index) {
                playerCharacters.get(currentPlayer.returnplayerNum()).setTranslateY(playerCharacters.get(currentPlayer.returnplayerNum()).getTranslateY() - 78);
            }
        }

    }
}