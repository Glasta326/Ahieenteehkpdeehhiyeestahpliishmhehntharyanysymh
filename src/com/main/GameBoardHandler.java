package com.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

    public Label foodOutputStat;
    public AnchorPane tileButtonOptions;
    public Button attemptFight;
    public Button surrender;
    public Label victoryChance;
    public Label surrenderCost;
    public AnchorPane editTilePane;
    public AnchorPane ownedTileButtonOptions;
    public AnchorPane unownedTileButtonOptions;
    public Label infoCardTitle1;
    public AnchorPane editableTilePane;
    @FXML private Button diceRoller;
    @FXML private Button turnEnder;
    @FXML private Label infoCardTitle;
    @FXML private Label ownerLabel;
    @FXML private Label foodStealLabel;
    @FXML private Label foodProdLabel;
    @FXML private Label playersTurnLabel;
    @FXML private Button purchaseTile;
    @FXML private ImageView tileInfoCard1;
    @FXML private AnchorPane areaTileInfo1;
    @FXML private Label ownerLabel1;
    @FXML private Label currentTierLabel1;
    @FXML private Label foodStealLabel1;
    @FXML private Label foodProdLabel1;
    @FXML private Label cost1n2;
    @FXML private Label cost2n2;
    @FXML private Label cost3n2;
    @FXML private Label cost4n2;
    @FXML private ChoiceBox<String> areaSelectionBox;
    @FXML private Button upgradeButton;
    @FXML private Button addPopulationButton;
    @FXML private Button removePopulationButton;
    @FXML private TextField addPopulationTextField;
    @FXML private TextField removePopulationTextField;
    @FXML private Label playerFoodStat;
    @FXML private Label playerPopulationStat;
    @FXML private Label playerSparePopulationStat;
    @FXML private Label populationUpkeepStat;
    @FXML private Label foodProductionStat;
    @FXML private Label cardInfo;
    @FXML private AnchorPane areaTileInfo;
    @FXML private Label cost1;
    @FXML private Label cost2;
    @FXML private Label cost3;
    @FXML private Label cost4;
    @FXML private ImageView tileInfoCard;
    @FXML private Label currentTierLabel;
    @FXML private ImageView playerChar1;
    @FXML private ImageView playerChar2;
    @FXML private ImageView playerChar3;
    @FXML private ImageView playerChar4;
    @FXML private Label die1;
    @FXML private Label die2;
    Tile currentTile;
    private int playerCount;
    public ArrayList<ImageView> playerCharacters = new ArrayList<>();
    int diceNumber1;
    int diceNumber2;
    int currentPlayer;
    boolean hasRolled;

    public void retrieveData(int playerCount){
        this.playerCount = playerCount;
    }

    // Gets called when the player presses the "Continue to game button"
    public void initGame(Parent root, ArrayList<String> animals, Stage stage) throws URISyntaxException {
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
        changePlayerStats();
        playersTurnLabel.setText(current.animal + "'s turn");
        hasRolled = false;
    }

    @FXML
    // Activates after the player presses the end turn button, can only be activated after the player has rolled the dice.
    protected void endTurn() throws URISyntaxException {
        if (currentPlayer == playerCount - 1){
            currentPlayer = 0;
        } else {
            currentPlayer += 1;
        }
        GameHandler.setCurrentPlayer(currentPlayer);
        Player current = GameHandler.returnCurrentPlayer();
        diceRoller.setDisable(false);
        turnEnder.setDisable(true);
        hasRolled = false;
        current.food += current.foodOutput;
        changeInfoCard();
        changePlayerStats();
        playersTurnLabel.setText(current.animal + "'s turn");
        purchaseTile.setDisable(true);
        areaSelectionBox.setValue(null);
        editableTilePane.setOpacity(0);
        setOwnedTileList();
    }

    @FXML
    protected void rollDice() throws URISyntaxException {
        // checks to see if player has already rolled dice, then rolls dice
        if (!hasRolled) {
            diceNumber1 = GameHandler.Rolldice();
            die1.setText(Integer.toString(diceNumber1));
            diceNumber2 = GameHandler.Rolldice();
            die2.setText(Integer.toString(diceNumber2));
            movePlayer();
            diceRoller.setDisable(true);
            turnEnder.setDisable(false);
            hasRolled = true;
        }
        diceRoller.setDisable(true);
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
                currentPlayer.index = 0;
            }
            // Checks to see what lane of the board they are on and moves accordingly
            if (1 <= currentPlayer.index && currentPlayer.index < 9){
                playerCharacters.get(currentPlayer.returnplayerNum()-1).setTranslateX(playerCharacters.get(currentPlayer.returnplayerNum()-1).getTranslateX() + 78);
            } else if (9 <= currentPlayer.index && currentPlayer.index < 17){
                playerCharacters.get(currentPlayer.returnplayerNum()-1).setTranslateY(playerCharacters.get(currentPlayer.returnplayerNum()-1).getTranslateY() + 78);
            } else if (17 <= currentPlayer.index && currentPlayer.index < 25) {
                playerCharacters.get(currentPlayer.returnplayerNum()-1).setTranslateX(playerCharacters.get(currentPlayer.returnplayerNum()-1).getTranslateX() - 78);
            } else if (25 <= currentPlayer.index) {
                playerCharacters.get(currentPlayer.returnplayerNum()-1).setTranslateY(playerCharacters.get(currentPlayer.returnplayerNum()-1).getTranslateY() - 78);
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
        infoCardTitle.setText(currentTile.name);
        if (Objects.equals(currentTile.type, "Special")){
            cardInfo.setOpacity(1);
            areaTileInfo.setOpacity(0);
            switch (currentTile.name) {
                case "Start" -> cardInfo.setText("Land on the Start tile to earn 1000 food, pass the start tile to earn 500 food!");
                case "Evolution" -> cardInfo.setText("Land on evolution and receive a special evolution - these can be good or bad and you can have a maximum of 3 in effect at any given point!");
                case "Miss Turn" -> cardInfo.setText("Land on Miss a Turn and you miss your next go - it moves onto the next player skipping over you!");
                case "Zoo" -> cardInfo.setText("Land on Zoo and you stay in the Zoo for 3 turns or until you roll a double!");
                case "Slaughterhouse" -> cardInfo.setText("Land on the Slaughterhouse and you lose one of you lives, lose 3 lives and you are out of the game!");
            }
            ownedTileButtonOptions.setOpacity(0);
            ownedTileButtonOptions.setDisable(true);
            attemptFight.setDisable(true);
            surrender.setDisable(true);
            unownedTileButtonOptions.setOpacity(0);
            unownedTileButtonOptions.setDisable(true);
            purchaseTile.setDisable(true);
        }
        else {
            cardInfo.setOpacity(0);
            areaTileInfo.setOpacity(1);
            cost1.setText(Integer.toString(currentTile.costs));
            cost2.setText(Integer.toString(currentTile.tierCosts.get(0)));
            cost3.setText(Integer.toString(currentTile.tierCosts.get(1)));
            cost4.setText(Integer.toString(currentTile.tierCosts.get(2)));
            currentTierLabel.setText("Current Tier: " + currentTile.tier);
            foodProdLabel.setText("Food Prod: " +  currentTile.foodProduction.get(0) + " | " + currentTile.foodProduction.get(1) + " | "  + currentTile.foodProduction.get(2) + " | "  + currentTile.foodProduction.get(3));
            foodStealLabel.setText("Food Steal: " + currentTile.foodSteal.get(0) + " | " + currentTile.foodSteal.get(1) + " | "  + currentTile.foodSteal.get(2) + " | "  + currentTile.foodSteal.get(3));
            if (currentTile.owner != 0) {
                if (currentTile.owner != currentPlayer.returnplayerNum()) {
                    ownerLabel.setText("Owner: " + Objects.requireNonNull(GameHandler.getPlayerWithIndex(currentTile.getOwner())).animal);
                    purchaseTile.setDisable(true);
                    ownedTileButtonOptions.setOpacity(1);
                    ownedTileButtonOptions.setDisable(false);
                    unownedTileButtonOptions.setOpacity(0);
                    unownedTileButtonOptions.setDisable(true);
                    attemptFight.setDisable(false);
                    surrender.setDisable(false);
                }
                else{
                    ownedTileButtonOptions.setOpacity(0);
                    ownedTileButtonOptions.setDisable(true);
                    unownedTileButtonOptions.setOpacity(0);
                    unownedTileButtonOptions.setDisable(true);
                    attemptFight.setDisable(true);
                    surrender.setDisable(true);
                }
            }
            else{
                ownerLabel.setText("Owner: Unowned");
                unownedTileButtonOptions.setOpacity(1);
                unownedTileButtonOptions.setDisable(false);
                ownedTileButtonOptions.setOpacity(0);
                ownedTileButtonOptions.setDisable(true);
                purchaseTile.setDisable(currentPlayer.food <= currentTile.costs && !hasRolled);
            }

        }
    }
    
    public void changePlayerStats(){
        Player currentPlayer = GameHandler.returnCurrentPlayer();
        playerFoodStat.setText("Food: "+ currentPlayer.food);
        playerPopulationStat.setText("Total Population: "+ currentPlayer.totalPopulation);
        playerSparePopulationStat.setText("Spare Population: "+ currentPlayer.sparePopulation);
        populationUpkeepStat.setText("Population Upkeep: "+ currentPlayer.populationUpkeep);
        foodProductionStat.setText("Food Production: "+ currentPlayer.foodProduction);
        foodOutputStat.setText("Food Output: "+ currentPlayer.foodOutput);
    }

    @FXML
    protected void purchaseTile() throws URISyntaxException {
        Player currentPlayer = GameHandler.returnCurrentPlayer();
        currentTile = GameHandler.getTileWithIndex(currentPlayer.index + 1);
        if (hasRolled) {
            assert currentTile != null;
            if (currentPlayer.food > currentTile.costs) {
                currentPlayer.food -= currentTile.costs;
                currentPlayer.foodProduction += currentTile.foodProduction.get(0);
                currentTile.tier = 1;
                currentPlayer.updateFoodOutput();
                currentTile.owner = currentPlayer.returnplayerNum();
                changeInfoCard();
                changePlayerStats();
            }
        }
    }

    public void setOwnedTileList(){
        Player currentPlayer = GameHandler.returnCurrentPlayer();
        ArrayList<Tile> ownedTiles = GameHandler.getTilesOwnedBy(currentPlayer);
        ObservableList<String> tileNames = FXCollections.observableArrayList();
        for (Tile tile : ownedTiles){
            tileNames.add(tile.name);
        }
        areaSelectionBox.setItems(tileNames);
    }

    @FXML
    protected void onAreaSelect() throws URISyntaxException {
        Tile area = GameHandler.getTileWithName(areaSelectionBox.getValue());
        Player currentPlayer = GameHandler.returnCurrentPlayer();
        if (area != null) {
            Image img = new Image(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("images")).toURI() + area.type + "TileInfoCard.png");
            editableTilePane.setOpacity(1);
            tileInfoCard1.setImage(img);
            infoCardTitle1.setText(area.name);
            areaTileInfo1.setOpacity(1);
            cost1n2.setText(Integer.toString(area.costs));
            cost2n2.setText(Integer.toString(area.tierCosts.get(0)));
            cost3n2.setText(Integer.toString(area.tierCosts.get(1)));
            cost4n2.setText(Integer.toString(area.tierCosts.get(2)));
            currentTierLabel1.setText("Current Tier: " + area.tier);
            foodProdLabel1.setText("Food Prod: " + area.foodProduction.get(0) + " | " + area.foodProduction.get(1) + " | " + area.foodProduction.get(2) + " | " + area.foodProduction.get(3));
            foodStealLabel1.setText("Food Steal: " + area.foodSteal.get(0) + " | " + area.foodSteal.get(1) + " | " + area.foodSteal.get(2) + " | " + area.foodSteal.get(3));
            ownerLabel1.setText("Owner: " + Objects.requireNonNull(GameHandler.getPlayerWithIndex(area.getOwner())).animal);
            editTilePane.setOpacity(1);
            editTilePane.setDisable(false);
            upgradeButton.setDisable(currentPlayer.food < currentTile.tierCosts.get(currentTile.tier - 1) || currentTile.tier >= 4);
            addPopulationButton.setDisable(false);
            removePopulationButton.setDisable(false);
        }
        else{
            editTilePane.setOpacity(0);
            editTilePane.setDisable(true);
        }
    }

    @FXML
    protected void upgradeTile() throws URISyntaxException {
        Player currentPlayer = GameHandler.returnCurrentPlayer();
        currentTile = GameHandler.getTileWithIndex(currentPlayer.index + 1);
        assert currentTile != null;
        if (currentPlayer.food >= currentTile.tierCosts.get(currentTile.tier - 1) && currentTile.tier < 4){
            currentPlayer.food -= currentTile.tierCosts.get(currentTile.tier - 1);
            currentTile.tier += 1;
            changeInfoCard();
            changePlayerStats();
            onAreaSelect();
        }
    }
}