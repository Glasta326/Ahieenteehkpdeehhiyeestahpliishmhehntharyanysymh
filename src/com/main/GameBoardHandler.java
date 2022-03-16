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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class GameBoardHandler implements Initializable  {

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
    public Label currentPopulationLabel;
    public Label currentPopulationLabel1;
    public Button attemptAnnex;
    public Label victoryChanceAnnex;
    public Label messageLabel;
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
    HashMap<String,String> infoMessages = new HashMap<>();

    public void retrieveData(int playerCount){
        this.playerCount = playerCount;
    }

    // Gets called when the player presses the "Continue to game button"
    public void initGame(Parent root, ArrayList<String> animals, Stage stage) throws URISyntaxException {
        infoMessages.put("negativePop","Cannot enter a negative value!");
        infoMessages.put("emptyVal","Must enter a value!");
        infoMessages.put("cannotAfford","Cannot afford!");
        infoMessages.put("victory", "Successfully defeated!");
        infoMessages.put("annexed", "Successfully annexed!");
        infoMessages.put("defeated", "You were defeated!");
        infoMessages.put("missedTurn", "Previous player missed their turn!");
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

    // Activates after the player presses the end turn button, can only be activated after the player has rolled the dice, or if player misses their turn.
    private void turnOver() throws URISyntaxException {
        if (currentPlayer == playerCount - 1){
            currentPlayer = 0;
        } else {
            currentPlayer += 1;
        }
        GameHandler.setCurrentPlayer(currentPlayer);
        Player current = GameHandler.returnCurrentPlayer();
        if (current.turnsToMiss > 0){
            current.turnsToMiss -= 1;
            // Recursion !!!!!!!!!!!!!!!!!!!!!
            turnOver();
        }
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
        messageLabel.setText("");
    }

    @FXML

    protected void endTurn() throws URISyntaxException {
        turnOver();
    }

    @FXML
    protected void rollDice() throws URISyntaxException {
        // checks to see if player has already rolled dice, then rolls dice
        Player currentPlayer = GameHandler.returnCurrentPlayer();
        if (!hasRolled) {
            diceNumber1 = GameHandler.Rolldice();
            die1.setText(Integer.toString(diceNumber1));
            diceNumber2 = GameHandler.Rolldice();
            die2.setText(Integer.toString(diceNumber2));
            movePlayer();
            diceRoller.setDisable(true);
            currentTile = GameHandler.getTileWithIndex(currentPlayer.index + 1);
            assert currentTile != null;
            if (currentTile.getOwner() != 0 && currentTile.getOwner() != currentPlayer.returnplayerNum()){
                turnEnder.setDisable(true);
            }else{
                turnEnder.setDisable(false);
            }
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
            // Checks to see what lane of the board they are on and moves accordingly
            if (currentPlayer.index > 32 || currentPlayer.index == 32){
                currentPlayer.index = 0;
            }
            if (0 <= currentPlayer.index && currentPlayer.index < 8){
                playerCharacters.get(currentPlayer.returnplayerNum()-1).setTranslateX(playerCharacters.get(currentPlayer.returnplayerNum()-1).getTranslateX() + 78);
            } else if (8 <= currentPlayer.index && currentPlayer.index < 16){
                playerCharacters.get(currentPlayer.returnplayerNum()-1).setTranslateY(playerCharacters.get(currentPlayer.returnplayerNum()-1).getTranslateY() + 78);
            } else if (16 <= currentPlayer.index && currentPlayer.index < 24) {
                playerCharacters.get(currentPlayer.returnplayerNum()-1).setTranslateX(playerCharacters.get(currentPlayer.returnplayerNum()-1).getTranslateX() - 78);
            } else if (24 <= currentPlayer.index) {
                playerCharacters.get(currentPlayer.returnplayerNum()-1).setTranslateY(playerCharacters.get(currentPlayer.returnplayerNum()-1).getTranslateY() - 78);
            }
            currentPlayer.index += 1;
            if (currentPlayer.index > 32 || currentPlayer.index == 32){
                currentPlayer.index = 0;
                currentPlayer.food += 500;
            }
            if (currentPlayer.index == 8){
                currentPlayer.turnsToMiss += 1;
            }
        }
        if ( currentPlayer.index == 0){
            currentPlayer.food += 500;
        }
        changePlayerStats();
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
            attemptAnnex.setDisable(true);
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
            currentPopulationLabel.setText("Current Population: " + currentTile.population);
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
                    attemptAnnex.setDisable(false);
                    surrenderCost.setText("Food Lost: "+currentTile.foodSteal.get(currentTile.tier - 1) * (1+(currentTile.population / currentPlayer.sparePopulation)));
                    long chance = Math.round((100 / (currentPlayer.sparePopulation + currentTile.population * 1.1)) * currentPlayer.sparePopulation);
                    victoryChance.setText("Victory Chance: " + chance + "%");
                    chance = Math.round((100 / (currentPlayer.sparePopulation + currentTile.population * 2.5)) * currentPlayer.sparePopulation);
                    victoryChanceAnnex.setText("Victory Chance: " + chance + "%");
                }
                else{
                    ownedTileButtonOptions.setOpacity(0);
                    ownedTileButtonOptions.setDisable(true);
                    unownedTileButtonOptions.setOpacity(0);
                    unownedTileButtonOptions.setDisable(true);
                    attemptFight.setDisable(true);
                    surrender.setDisable(true);
                    attemptAnnex.setDisable(true);
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
            currentPopulationLabel1.setText("Current Population: " + area.population);
            editTilePane.setOpacity(1);
            editTilePane.setDisable(false);
            upgradeButton.setDisable(currentPlayer.food < area.tierCosts.get(area.tier - 1) || area.tier >= 4);
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

    @FXML
    protected void addPopulation() throws URISyntaxException {
        Player currentPlayer = GameHandler.returnCurrentPlayer();
        Tile area = GameHandler.getTileWithName(areaSelectionBox.getValue());
        assert area != null;
        if (currentPlayer.sparePopulation >= Integer.parseInt(addPopulationTextField.getText()) && Integer.parseInt(addPopulationTextField.getText()) > 0) {
            area.population += Integer.parseInt(addPopulationTextField.getText());
            currentPlayer.sparePopulation -= Integer.parseInt(addPopulationTextField.getText());
        }
        changeInfoCard();
        changePlayerStats();
        onAreaSelect();
    }

    @FXML
    protected void removePopulation() throws URISyntaxException {
        Player currentPlayer = GameHandler.returnCurrentPlayer();
        Tile area = GameHandler.getTileWithName(areaSelectionBox.getValue());
        assert area != null;
        if (area.population >= Integer.parseInt(removePopulationTextField.getText()) && Integer.parseInt(removePopulationTextField.getText()) > 0) {
            area.population -= Integer.parseInt(removePopulationTextField.getText());
            currentPlayer.sparePopulation += Integer.parseInt(removePopulationTextField.getText());
        }
        changeInfoCard();
        changePlayerStats();
        onAreaSelect();
    }

    private void giveFood(int multiplier) throws URISyntaxException {
        Player currentPlayer = GameHandler.returnCurrentPlayer();
        currentTile = GameHandler.getTileWithIndex(currentPlayer.index + 1);
        assert currentTile != null;
        Player tileOwner = GameHandler.getPlayerWithIndex(currentTile.getOwner());
        int foodToGive = (currentTile.foodSteal.get(currentTile.tier - 1) * (1+(currentTile.population / currentPlayer.sparePopulation))) * multiplier;
        assert tileOwner != null;
        // Checks if player has enough food in reserve to give
        if (currentPlayer.food > foodToGive){
            System.out.println("Payed player: " + foodToGive);
            currentPlayer.food -= foodToGive;
            tileOwner.food += foodToGive;
        }else{
            tileOwner.food += foodToGive;
            foodToGive -= currentPlayer.food;
            currentPlayer.food = 0;
            // If player lacks food, checks if they have enough population to cover the food debt, if not they are eliminated
            if (currentPlayer.totalPopulation > foodToGive){
                // Checks if player has enough pop in spare to cover debt, if not takes from areas.
                if (currentPlayer.sparePopulation > foodToGive){
                    currentPlayer.sparePopulation -= foodToGive;
                }else {
                    ArrayList<Tile> playersTiles = GameHandler.getTilesOwnedBy(currentPlayer);
                    foodToGive -= currentPlayer.sparePopulation;
                    currentPlayer.sparePopulation = 0;
                    // Checks areas owned by the player from closest to start to end until and removes population until debt is paid off.
                    for (Tile tile : playersTiles){
                        if (tile.population > foodToGive){
                            tile.population -= foodToGive;
                            break;
                        }else{
                            foodToGive -= tile.population;
                            tile.population = 0;
                        }
                    }
                }
            }else{
                System.out.println(currentPlayer.animal + " has been eliminated!");
                // Players gets eliminated (tile owner gains food equal to currentPlayers spare food and total population)
            }
        }
        turnEnder.setDisable(false);
        surrender.setDisable(true);
        attemptAnnex.setDisable(true);
        attemptFight.setDisable(true);
        changeInfoCard();
        changePlayerStats();
        onAreaSelect();
    }

    @FXML
    protected void surrender() throws URISyntaxException {
        giveFood(1);
    }

    @FXML
    protected void fight() throws URISyntaxException {
        Player currentPlayer = GameHandler.returnCurrentPlayer();
        currentTile = GameHandler.getTileWithIndex(currentPlayer.index + 1);
        assert currentTile != null;
        Player tileOwner = GameHandler.getPlayerWithIndex(currentTile.getOwner());
        int foodToGive = currentTile.foodSteal.get(currentTile.tier - 1) * (1+(currentTile.population / currentPlayer.sparePopulation));
        assert tileOwner != null;
        long chance = Math.round((100 / (currentPlayer.sparePopulation + currentTile.population * 1.1)) * currentPlayer.sparePopulation);
        Random ran = new Random();
        int randomNumber = ran.nextInt(100) + 1;
        if (randomNumber <= chance){
            long populationLost = Math.round((1 - (chance / 100.0)) * 0.05 * currentPlayer.sparePopulation);
            currentPlayer.sparePopulation -= populationLost;
            populationLost = Math.round(((chance / 100.0)) * 0.3 * currentTile.population);
            currentTile.population -= populationLost;
            changeMessageLabel("victory", "GREEN");
        }else{
            giveFood(2);
            long populationLost = Math.round((1 - (chance / 100.0)) * 0.3 * currentPlayer.sparePopulation);
            currentPlayer.sparePopulation -= populationLost;
            populationLost = Math.round(((chance / 100.0)) * 0.05 * currentTile.population);
            currentTile.population -= populationLost;
            changeMessageLabel("defeated", "RED");
        }
        changeInfoCard();
        changePlayerStats();
        onAreaSelect();
    }

    @FXML
    protected void annex() throws URISyntaxException {
        Player currentPlayer = GameHandler.returnCurrentPlayer();
        currentTile = GameHandler.getTileWithIndex(currentPlayer.index + 1);
        assert currentTile != null;
        Player tileOwner = GameHandler.getPlayerWithIndex(currentTile.getOwner());
        assert tileOwner != null;
        long chance = Math.round((100 / (currentPlayer.sparePopulation + currentTile.population * 2.5)) * currentPlayer.sparePopulation);
        Random ran = new Random();
        int randomNumber = ran.nextInt(100) + 1;
        if (randomNumber <= chance){
            long populationLost = Math.round((1 - (chance / 100.0)) * 0.1 * currentPlayer.sparePopulation);
            currentPlayer.sparePopulation -= populationLost;
            populationLost = Math.round(((chance / 100.0)) * 0.8 * currentTile.population);
            currentTile.population -= populationLost;
            currentTile.owner = currentPlayer.returnplayerNum();
            changeMessageLabel("annexed", "GREEN");
        }else {
            long populationLost = Math.round((1 - (chance / 100.0)) * 0.8 * currentPlayer.sparePopulation);
            currentPlayer.sparePopulation -= populationLost;
            populationLost = Math.round(((chance / 100.0)) * 0.1 * currentTile.population);
            currentTile.population -= populationLost;
            changeMessageLabel("defeated", "RED");
        }
        turnEnder.setDisable(false);
        surrender.setDisable(true);
        attemptAnnex.setDisable(true);
        attemptFight.setDisable(true);
        changeInfoCard();
        changePlayerStats();
        onAreaSelect();
    }

    private void changeMessageLabel(String message, String clr){
        Color colour;
        switch (clr.toUpperCase()) {
            case "RED" -> colour = Color.RED;
            case "GREEN" -> colour = Color.GREEN;
            default -> colour = Color.BLACK;
        }
        messageLabel.setTextFill(colour);
        messageLabel.setText(infoMessages.get(message));
    }
}