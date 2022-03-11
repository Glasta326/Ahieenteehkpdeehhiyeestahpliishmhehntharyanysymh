package com.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

public class GameBoardHandler implements Initializable {

    public Button diceRoller;
    public Button turnEnder;
    public Label infoCardTitle;
    public Label ownerLabel;
    public Label foodStealLabel;
    public Label foodProdLabel;
    public Label playersTurnLabel;
    @FXML private Label cardInfo;
    @FXML private AnchorPane areaTileInfo;
    public TableView<Tile> tierPriceTable;
    public Label cost1;
    public Label cost2;
    public Label cost3;
    public Label cost4;
    public ImageView tileInfoCard;
    public Label currentTierLabel;
    Tile currentTile;
    @FXML private ImageView playerChar1;
    @FXML private ImageView playerChar2;
    @FXML private ImageView playerChar3;
    @FXML private ImageView playerChar4;
    @FXML private Label die1;
    @FXML private Label die2;
    private int playerCount;
    public ArrayList<ImageView> playerCharacters = new ArrayList<>();
    int diceNumber1;
    int diceNumber2;
    int currentPlayer;

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
        Player current = GameHandler.returnCurrentPlayer();
        changeInfoCard();
        playersTurnLabel.setText(current.animal + "'s turn");
    }

    @FXML
    // Activates after the player presses the end turn button, can only be activated after the player has rolled the dice.
    protected void endTurn() throws URISyntaxException {
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
        changeInfoCard();
        playersTurnLabel.setText(current.animal + "'s turn");
    }

    @FXML
    protected void rollDice() throws URISyntaxException {
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
    private void movePlayer() throws URISyntaxException {
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
        changeInfoCard();
    }

    public void changeInfoCard() throws URISyntaxException {
        
        Player currentPlayer = GameHandler.returnCurrentPlayer();
        currentTile = GameHandler.getTileWithIndex(currentPlayer.index + 1);
        assert currentTile != null;
        Image img = new Image(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("images")).toURI() + currentTile.type + "TileInfoCard.png");
        tileInfoCard.setImage(img);
        assert currentTile != null;
        infoCardTitle.setText(currentTile.name);
        if (Objects.equals(currentTile.type, "Special")){
            cardInfo.setOpacity(1);
            areaTileInfo.setOpacity(0);
            switch (currentTile.name){
                case "Start":
                    cardInfo.setText("Land on the Start tile to earn 1000 food, pass the start tile to earn 500 food");
            }
        }
        else {
            cardInfo.setOpacity(0);
            areaTileInfo.setOpacity(1);
            cost1.setText(Integer.toString(currentTile.costs));
            cost2.setText(Integer.toString(currentTile.tierCosts.get(0)));
            cost3.setText(Integer.toString(currentTile.tierCosts.get(1)));
            cost4.setText(Integer.toString(currentTile.tierCosts.get(2)));
        }
    }
}