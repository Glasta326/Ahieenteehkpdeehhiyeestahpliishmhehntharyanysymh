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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
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
    public ImageView evolutionSlot1;
    public ImageView evolutionSlot2;
    public ImageView evolutionSlot3;
    public Label evolutionLabel1;
    public Label evolutionLabel2;
    public Label evolutionLabel3;
    public ChoiceBox<String> popGrowthRateChoiceBox;
    public Label rollDoubleInfoLabel;
    public AnchorPane tempEvoPane;
    public ImageView tempEvolutionSlot;
    public Label tempEvoTitleLabel;
    public Label tempEvoInfoLabel;
    public Label clickEvoReplaceLabel;
    public Button evoSelectButton1;
    public Button evoSelectButton2;
    public Button evoSelectButton3;
    public Label Title;
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
    int spacesToMove;
    boolean hasRolled;
    int ranEvoValue;
    HashMap<String,String> infoMessages = new HashMap<>();
    @FXML private final ObservableList<String> growthRates = FXCollections.observableArrayList("No Breeding 0 %", "Light Breeding 5 %", "Average Breeding 10 %", "Competitive Breeding 15 %");
    public double breedAmount;
    public double multiplier;
    public boolean rolledDouble;
    public boolean choosingEvo;
    Stage currentStage;

    public String[][] cards = {
            {"A small but welcome gift from the humans - 20 extra food", "20", "food"},
            {"You have a particularly good hunting/gathering session and gain 100 extra food", "100", "food"},
            {"A bountiful find, you stole food from a human camp and gain 200 extra food", "200", "food"},
            {"Some of your food has gone rotten and is inedible, you lose 100 food", "-100", "food"},
            {"Rival animals steal some of your food, you lose 200 food", "-200", "food"},
            {"Humans burn a large portion of your food, you lose 10% of your food", "0.9", "xFood"},
            {"You're population is feeling particularly frisky, your population increases by 50", "50", "population"},
            {"You fell into a trap and lost 20 population", "-20", "population"},
            {"The humans have hunted some of you down, population cut by 50", "-50", "population"},
            {"A plague spreads through your population, spare population is cut by 10%", "0.9", "xPopulation"},
            {"Your population starts to work out more, next time you fight, you will get a 10% bonus", "0.1", "strength"},
            {"Your population has become weak from lack of exercise, you will lose 10% strength in your next fight", "-0.1", "strength"},
            {"You found a zoo guards key, you will immediately escape the zoo next time you get caught", "1", "key"}
    };

    public String[][] evolutions = {
            {"Agility", "When you roll a double you get to roll again", "Gold", "2", "agility", "true"},
            {"Super Strong", "Increase population strength by 20%", "Gold", "3", "strength", "0.2"},
            {"Survivor", "Reduce population killed from the slaughterhouse by 15% (50% --> 35%)", "Gold", "3", "survivor", "true"},
            {"Minuscule Stomach", "Decrease food required to feed population by 10%", "Gold", "4", "food", "-0.1"},
            {"Strong", "Increase population strength by 10%", "Silver", "6", "strength", "0.1"},
            {"Small Stomach", "Decrease food required to feed population by 5%", "Silver", "7", "food", "-0.05"},
            {"Escapist", "Reduces time spent in zoo by 1 turn", "Silver", "7", "escapist", "true"},
            {"Lucky", "Increase chance to get positive cards from rolling doubles by 20%", "Bronze", "10", "lucky", "true"},
            {"Sly", "25% chance to not miss a turn when landing on the 'Miss a turn' tile", "Bronze", "10", "sly", "true"},
            {"Swiftness", "When you roll a double, you move 1 extra tile", "Bronze", "10", "swiftness", "true"},
            {"Longer Neck", "Makes it easier to reach food, increasing production by 5%", "Bronze", "10", "foodProd", "0.05"},
            {"Sleepy", "15% chance that you have to miss 2 turns when landing on the 'Miss a turn' tile", "Red", "10", "sleepy", "true"},
            {"Slowness", "When you roll a double, you move 1 less tile", "Red", "10", "slowness", "true"},
            {"Greedy", "Increase food required to feed population by 5%", "DarkRed", "4", "food", "0.05"},
            {"Weak", "Decrease population strength by 10%", "DarkRed", "4", "strength", "-0.1"},

    };
    
    public void retrieveData(int playerCount){
        this.playerCount = playerCount;
    }

    // Gets called when the player presses the "Continue to game button"
    public void initGame(Parent root, ArrayList<String> animals, Stage stage) throws URISyntaxException {
        popGrowthRateChoiceBox.setItems(growthRates);
        popGrowthRateChoiceBox.setValue("No Breeding 0 %");
        // Info messages that are called when an event happens in the game
        infoMessages.put("negativePop","Cannot enter a negative value!");
        infoMessages.put("emptyVal","Must enter a value!");
        infoMessages.put("cannotAfford","Cannot afford to make that purchase!");
        infoMessages.put("victory", "Successfully defeated!");
        infoMessages.put("annexed", "Successfully annexed!");
        infoMessages.put("defeated", "You were defeated!");
        infoMessages.put("missedTurn", "Previous player missed their turn!");
        infoMessages.put("escaped", "You escaped from the Zoo");
        infoMessages.put("trapped", "Player remains trapped in zoo");
        infoMessages.put("maxTier", "That area is already the maximum tier");
        infoMessages.put("rolledDouble", "You rolled a double, and drew a card");
        infoMessages.put("evolved", "You landed on an evolution tile and evolved!");
        Scene GameBoard = new Scene(root, 1280.0D, 720.0D);
        currentStage = stage;
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
    private void turnOver() throws URISyntaxException, InterruptedException, IOException {
        Player current = GameHandler.returnCurrentPlayer();
        if (!current.isEliminated) {
            // Checks if it is the last player, and if so, goes to player 1's turn
            if (!rolledDouble && !current.hasAgility || rolledDouble && !current.hasAgility || !rolledDouble) {
                if (currentPlayer == playerCount - 1) {
                    currentPlayer = 0;
                } else {
                    currentPlayer += 1;
                }
            }
            GameHandler.setCurrentPlayer(currentPlayer);
            current = GameHandler.returnCurrentPlayer();
            // Checks if the player has any turns to miss (Landed on a miss a turn tile)
            if (current.turnsToMiss > 0) {
                current.turnsToMiss -= 1;
                changeMessageLabel("missedTurn", "RED");
                // Recursion !!!!!!!!!!!!!!!!!!!!!
                turnOver();
            } else {
                playersTurnLabel.setText(current.animal + "'s turn");
            }
            rollDoubleInfoLabel.setText("");
            diceRoller.setDisable(false);
            turnEnder.setDisable(true);
            hasRolled = false;
            rolledDouble = false;
            if (current.food >= current.foodOutput) {
                current.food += current.foodOutput;
            } else {
                int populationToKill = (int) Math.round((current.foodOutput - current.food) * 0.2);
                current.food = 0;
                if (current.totalPopulation > populationToKill) {
                    // Checks if player has enough pop in spare to cover debt, if not takes from areas.
                    if (current.sparePopulation > populationToKill) {
                        current.sparePopulation -= populationToKill;
                    } else {
                        ArrayList<Tile> playersTiles = GameHandler.getTilesOwnedBy(current);
                        populationToKill -= current.sparePopulation;
                        current.sparePopulation = 0;
                        // Checks areas owned by the player from closest to start to end until and removes population until debt is paid off.
                        for (Tile tile : playersTiles) {
                            if (tile.population > populationToKill) {
                                tile.population -= populationToKill;
                                break;
                            } else {
                                populationToKill -= tile.population;
                                tile.population = 0;
                                checkIfEliminated(current);
                            }
                        }
                    }
                } else {
                    checkIfEliminated(current);
                }
            }
            current.calculateTotalPopulation();
            current.updateFoodOutput();
            current.totalPopulation -= 85;
            checkIfEliminated(current);
            changeInfoCard();
            changePlayerStats();
            popGrowthRateChoiceBox.setValue("No Breeding 0 %");
            purchaseTile.setDisable(true);
            areaSelectionBox.setValue(null);
            editableTilePane.setOpacity(0);
            tempEvoPane.setOpacity(0);
            evolutionSlot1.setOpacity(0);
            evolutionSlot2.setOpacity(0);
            evolutionSlot3.setOpacity(0);
            evolutionLabel1.setText("");
            evolutionLabel2.setText("");
            evolutionLabel3.setText("");
            updateEvolutionCards(current);
            setOwnedTileList();
        }else{
            turnOver();
        }
    }

    @FXML
    // Is called when the player clicks the "End turn button"
    protected void endTurn() throws URISyntaxException, InterruptedException, IOException {
        Player currentPlayer = GameHandler.returnCurrentPlayer();
        // Checks if the player is not in zoo, and has a growth rate set, and increases the population accordingly
        if (currentPlayer.popGrowthRate > 0 && currentPlayer.zooTurnsLeft == 0) {
            currentPlayer.food = (int) Math.round(currentPlayer.food*(1 - currentPlayer.popGrowthRate));
            currentPlayer.sparePopulation += Math.round(currentPlayer.food * (currentPlayer.popGrowthRate) * 0.33);
            currentPlayer.updateFoodOutput();
        }
        turnOver();
    }

    @FXML
    protected int rollDice() throws URISyntaxException, InterruptedException, IOException {
        // checks to see if player has already rolled dice, then rolls dice
        Player currentPlayer = GameHandler.returnCurrentPlayer();
        if (!hasRolled) {
            diceNumber1 = GameHandler.Rolldice();
            die1.setText(Integer.toString(diceNumber1));
            diceNumber2 = GameHandler.Rolldice();
            die2.setText(Integer.toString(diceNumber2));
            hasRolled = true;
            // Checks for a double roll
            if (diceNumber1 == diceNumber2){
                // If player is in zoo, causes them to escape the zoo
                spacesToMove = diceNumber1 + diceNumber2;
                rolledDouble = true;
                if (currentPlayer.hasSwiftness) {
                    spacesToMove += 1;
                }
                if (currentPlayer.hasSlowness) {
                    spacesToMove -= 1;
                }
                changeMessageLabel("rolledDouble", "GREEN");
                if (currentPlayer.zooTurnsLeft > 0) {
                    currentPlayer.zooTurnsLeft = 0;
                    changeMessageLabel("escaped", "GREEN");
                }
                drawCard(currentPlayer);
            }else{
                // If player is in the zoo, checks to see if it is their last turn in their, if not, ends turn
                spacesToMove = diceNumber1 + diceNumber2;
                if (currentPlayer.zooTurnsLeft > 0){
                    currentPlayer.zooTurnsLeft -= 1;
                    if (currentPlayer.zooTurnsLeft == 0){
                        changeMessageLabel("escaped", "GREEN");
                    }else if (currentPlayer.keys > 0) {
                        changeMessageLabel("escaped", "GREEN");
                        currentPlayer.keys -= 1;
                        currentPlayer.zooTurnsLeft = 0;
                    }else{
                        changeMessageLabel("trapped", "RED");
                        turnOver();
                        return 0;
                        }
                    }
                }
            }
            // Moves the player to the correct tile.
            movePlayer();
            diceRoller.setDisable(true);
            return 0;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    // When a player rolls dice, moves their character the appropriate number of spaces.
    private void movePlayer() throws URISyntaxException, IOException {
        Player currentPlayer = GameHandler.returnCurrentPlayer();
        for (int i = 0; i < spacesToMove; i++) {
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
                currentPlayer.food += 250;
            }
        }
        currentTile = GameHandler.getTileWithIndex(currentPlayer.index + 1);
        assert currentTile != null;
        // checks if the tile the player landed on matches their synergy
        if (Objects.equals(currentTile.type, currentPlayer.synergy)){
            multiplier = 0.9;
        }else{
            multiplier = 1;
        }
        // Checking for specific tiles (e.g. if they landed on start or zoo tile)
        currentTile = GameHandler.getTileWithIndex(currentPlayer.index + 1);
        assert currentTile != null;
        if (currentTile.getOwner() != 0 && currentTile.getOwner() != currentPlayer.returnplayerNum()){
            turnEnder.setDisable(true);
        }else{
            turnEnder.setDisable(false);
        }
        // Landed on start
        if ( currentPlayer.index == 0){
            currentPlayer.food += 250;
        }
        // Landed on miss a turn
        if (currentPlayer.index == 8){
            if (currentPlayer.hasSly) {
                Random random = new Random();
                int ranNum = random.nextInt(100) + 1;
                if (ranNum < 75){
                    currentPlayer.turnsToMiss += 1;
                    ranNum = random.nextInt(100) + 1;
                    if (ranNum < 85){
                        currentPlayer.turnsToMiss += 1;
                    }
                }
            }else{
                currentPlayer.turnsToMiss += 1;
            }
        }
        // Landed on zoo
        if (currentPlayer.index == 16){
            if (currentPlayer.hasEscapist) {
                currentPlayer.zooTurnsLeft += 3;
            }else{
                currentPlayer.zooTurnsLeft += 4;
            }
        }
        // Landed on slaughterhouse
        if (currentPlayer.index == 24){
            long populationToKill;
            if (currentPlayer.hasSurvivor) {
                populationToKill = Math.round(currentPlayer.totalPopulation * 0.35);
            }else{
                populationToKill = Math.round(currentPlayer.totalPopulation * 0.5);
            }
            if (currentPlayer.totalPopulation > populationToKill) {
                // Checks if player has enough pop in spare to cover debt, if not takes from areas.
                if (currentPlayer.sparePopulation > populationToKill) {
                    currentPlayer.sparePopulation -= populationToKill;
                } else {
                    ArrayList<Tile> playersTiles = GameHandler.getTilesOwnedBy(currentPlayer);
                    populationToKill -= currentPlayer.sparePopulation;
                    currentPlayer.sparePopulation = 0;
                    // Checks areas owned by the player from closest to start to end until and removes population until debt is paid off.
                    for (Tile tile : playersTiles) {
                        if (tile.population > populationToKill) {
                            tile.population -= populationToKill;
                            break;
                        } else {
                            populationToKill -= tile.population;
                            tile.population = 0;
                        }
                    }
                }
            }
        }
        // Landed on an evolution tile
        if (currentPlayer.index == 4 || currentPlayer.index == 12 || currentPlayer.index == 20 || currentPlayer.index == 28){
            turnEnder.setDisable(true);
            drawEvolution(currentPlayer);
        }
        changePlayerStats();
        changeInfoCard();
    }

    // Whenever the info of the tile the player on changes, this function is called
    public void changeInfoCard() throws URISyntaxException {
        Player currentPlayer = GameHandler.returnCurrentPlayer();
        currentTile = GameHandler.getTileWithIndex(currentPlayer.index + 1);
        assert currentTile != null;
        Image img = new Image(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("images")).toURI() + currentTile.type + "TileInfoCard.png");
        tileInfoCard.setImage(img);
        infoCardTitle.setText(currentTile.name);
        // If the tile is a special type, only shows one descrption of tile
        if (Objects.equals(currentTile.type, "Special")){
            cardInfo.setOpacity(1);
            areaTileInfo.setOpacity(0);
            switch (currentTile.name) {
                case "Start" -> cardInfo.setText("Land on the Start tile to earn 500 food, pass the start tile to earn 250!");
                case "Evolution" -> cardInfo.setText("Land on evolution and receive a special evolution - these can be good or bad and you can have a maximum of 3 in effect at any given point!");
                case "Miss Turn" -> cardInfo.setText("Land on Miss a Turn and you miss your next go - it moves onto the next player skipping over you!");
                case "Zoo" -> cardInfo.setText("Land on Zoo and you stay in the Zoo for 3 turns or until you roll a double!");
                case "Slaughterhouse" -> cardInfo.setText("Landing on the slaughterhouse removes 50% of your entire population :(");
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
        // if tile is not of special type
        else {
            cardInfo.setOpacity(0);
            areaTileInfo.setOpacity(1);
            cost1.setText(Integer.toString((int) (currentTile.costs * multiplier)));
            cost2.setText(Integer.toString((int) (currentTile.tierCosts.get(0) * multiplier)));
            cost3.setText(Integer.toString((int) (currentTile.tierCosts.get(1) * multiplier)));
            cost4.setText(Integer.toString((int) (currentTile.tierCosts.get(2) * multiplier)));
            currentTierLabel.setText("Current Tier: " + currentTile.tier);
            foodProdLabel.setText("Food Prod: " +  (int) (currentTile.foodProduction.get(0) * (2 - multiplier)) + " | " + (int) (currentTile.foodProduction.get(1) * (2 - multiplier)) + " | "  + (int) (currentTile.foodProduction.get(2) * (2 - multiplier)) + " | "  + (int) (currentTile.foodProduction.get(3) * (2 - multiplier)));
            foodStealLabel.setText("Food Steal: " + currentTile.foodSteal.get(0) + " | " + currentTile.foodSteal.get(1) + " | "  + currentTile.foodSteal.get(2) + " | "  + currentTile.foodSteal.get(3));
            currentPopulationLabel.setText("Current Population: " + currentTile.population);
            // checks if tile is owned (and checks if the owner is the current player) and displays info accordingly
            if (currentTile.owner != 0) {
                if (currentTile.owner != currentPlayer.returnplayerNum()) {
                    ownerLabel.setText("Owner: " + Objects.requireNonNull(GameHandler.getPlayerWithIndex(currentTile.getOwner())).animal);
                    purchaseTile.setDisable(true);
                    ownedTileButtonOptions.setOpacity(1);
                    ownedTileButtonOptions.setDisable(false);
                    unownedTileButtonOptions.setOpacity(0);
                    unownedTileButtonOptions.setDisable(true);
                    System.out.println(hasRolled);
                    if (hasRolled) {
                        attemptFight.setDisable(false);
                        surrender.setDisable(false);
                        attemptAnnex.setDisable(false);
                    }
                    double currentPlayerStrengthMulti = currentPlayer.cardStrengthMulti + currentPlayer.evolutionStrengthMulti;
                    Player tileOwner = GameHandler.getPlayerWithIndex(currentTile.getOwner());
                    assert tileOwner != null;
                    double tileOwnerStrengthMulti = tileOwner.cardStrengthMulti + tileOwner.evolutionStrengthMulti;
                    surrenderCost.setText("Food Lost: "+currentTile.foodSteal.get(currentTile.tier - 1) * (1+(currentTile.population / (currentPlayer.sparePopulation + 1))));
                    System.out.println((100 / (currentPlayer.sparePopulation * currentPlayerStrengthMulti + ((currentTile.population * tileOwnerStrengthMulti) * 2.0) ) * currentPlayer.sparePopulation * currentPlayerStrengthMulti));
                    long chance = Math.round((100 / (currentPlayer.sparePopulation * currentPlayerStrengthMulti + ((currentTile.population * tileOwnerStrengthMulti) * 2.0) ) * currentPlayer.sparePopulation * currentPlayerStrengthMulti));
                    victoryChance.setText("Victory Chance: " + chance + "%");
                    chance = Math.round((100 / (currentPlayer.sparePopulation * currentPlayerStrengthMulti + ((currentTile.population * tileOwnerStrengthMulti) * 10.0) ) * currentPlayer.sparePopulation * currentPlayerStrengthMulti));
                    victoryChanceAnnex.setText("Victory Chance: " + chance + "%");
                }
                else{
                    ownerLabel.setText("Owner: " + Objects.requireNonNull(GameHandler.getPlayerWithIndex(currentTile.getOwner())).animal);
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
                purchaseTile.setDisable(currentPlayer.food < currentTile.costs || !hasRolled);
            }

        }
    }

    // changes the stats in the box to the bottom left of the board
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
        // Checks that the player has rolled the dice already, and has enough food to purchase area
        if (hasRolled) {
            assert currentTile != null;
            if (currentPlayer.food >= currentTile.costs * multiplier) {
                currentPlayer.food -= currentTile.costs * multiplier;
                currentPlayer.foodProduction += currentTile.foodProduction.get(0) * currentPlayer.evolutionFoodProductionMulti;
                currentTile.tier = 1;
                currentPlayer.updateFoodOutput();
                currentTile.owner = currentPlayer.returnplayerNum();
                changeInfoCard();
                changePlayerStats();
            }else{
                changeMessageLabel("cannotAfford", "RED");
            }
        }
    }

    // Adds all the players tiles to the list that they can then select from to edit
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
            System.out.println(area.tier);
            currentTierLabel1.setText("Current Tier: " + area.tier);
            if (Objects.equals(currentPlayer.synergy, currentTile.type)){
                cost1n2.setText(Integer.toString((int) (area.costs * multiplier)));
                cost2n2.setText(Integer.toString((int) (area.tierCosts.get(0) * multiplier)));
                cost3n2.setText(Integer.toString((int) (area.tierCosts.get(1) * multiplier)));
                cost4n2.setText(Integer.toString((int) (area.tierCosts.get(2) * multiplier)));
                foodProdLabel.setText("Food Prod: " +  (int) (currentTile.foodProduction.get(0) * (2 - multiplier)) + " | " + (int) (currentTile.foodProduction.get(1) * (2 - multiplier)) + " | "  + (int) (currentTile.foodProduction.get(2) * (2 - multiplier)) + " | "  + (int) (currentTile.foodProduction.get(3) * (2 - multiplier)));
            }else{
                System.out.println(area.tierCosts);
                cost1n2.setText(Integer.toString(area.costs));
                cost2n2.setText(Integer.toString(area.tierCosts.get(0)));
                cost3n2.setText(Integer.toString(area.tierCosts.get(1)));
                cost4n2.setText(Integer.toString(area.tierCosts.get(2)));
                foodProdLabel1.setText("Food Prod: " + area.foodProduction.get(0) + " | " + area.foodProduction.get(1) + " | " + area.foodProduction.get(2) + " | " + area.foodProduction.get(3));
            }
            foodStealLabel1.setText("Food Steal: " + area.foodSteal.get(0) + " | " + area.foodSteal.get(1) + " | " + area.foodSteal.get(2) + " | " + area.foodSteal.get(3));
            ownerLabel1.setText("Owner: " + Objects.requireNonNull(GameHandler.getPlayerWithIndex(area.getOwner())).animal);
            currentPopulationLabel1.setText("Current Population: " + area.population);
            editTilePane.setOpacity(1);
            editTilePane.setDisable(false);
            if (area.tier > 1) {
                upgradeButton.setDisable(currentPlayer.food < area.tierCosts.get(area.tier - 2) || area.tier >= 4);
            }else{
                upgradeButton.setDisable(currentPlayer.food < area.tierCosts.get(area.tier - 1));
            }
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
        Tile area = GameHandler.getTileWithName(areaSelectionBox.getValue());
        assert area != null;
        // checks that area is not already max level, and player can afford purchase, then upgrades tile accordingly
        if (area.tier < 4) {
            if (currentPlayer.food >= area.tierCosts.get(area.tier - 1) * multiplier){
                currentPlayer.food -= area.tierCosts.get(area.tier - 1) * multiplier;
                currentPlayer.foodProduction += (area.foodProduction.get(area.tier)) * currentPlayer.evolutionFoodProductionMulti;
                area.tier += 1;
                currentPlayer.updateFoodOutput();
                changeInfoCard();
                changePlayerStats();
                onAreaSelect();
            }else{
                changeMessageLabel("cannotAfford", "RED");
            }
        }else{
            changeMessageLabel("maxTier", "RED");
        }
    }

    @FXML
    // adds population to a tile
    protected void addPopulation() throws URISyntaxException {
        Player currentPlayer = GameHandler.returnCurrentPlayer();
        Tile area = GameHandler.getTileWithName(areaSelectionBox.getValue());
        assert area != null;
        // checks the player has enough spare population to add to tile pop
        if (currentPlayer.sparePopulation >= Integer.parseInt(addPopulationTextField.getText()) && Integer.parseInt(addPopulationTextField.getText()) > 0) {
            area.population += Integer.parseInt(addPopulationTextField.getText());
            currentPlayer.sparePopulation -= Integer.parseInt(addPopulationTextField.getText());
        }
        changeInfoCard();
        changePlayerStats();
        onAreaSelect();
    }

    @FXML
    // removed population from a tile
    protected void removePopulation() throws URISyntaxException {
        Player currentPlayer = GameHandler.returnCurrentPlayer();
        Tile area = GameHandler.getTileWithName(areaSelectionBox.getValue());
        assert area != null;
        // checks tile pop is high enough for the specified amount to be removed
        if (area.population >= Integer.parseInt(removePopulationTextField.getText()) && Integer.parseInt(removePopulationTextField.getText()) > 0) {
            area.population -= Integer.parseInt(removePopulationTextField.getText());
            currentPlayer.sparePopulation += Integer.parseInt(removePopulationTextField.getText());
        }
        changeInfoCard();
        changePlayerStats();
        onAreaSelect();
    }

    // called when the player surrenders, fights or attempts to annex an area
    private void giveFood(int multi) throws URISyntaxException, IOException {
        Player currentPlayer = GameHandler.returnCurrentPlayer();
        currentTile = GameHandler.getTileWithIndex(currentPlayer.index + 1);
        assert currentTile != null;
        Player tileOwner = GameHandler.getPlayerWithIndex(currentTile.getOwner());
        int foodToGive = (currentTile.foodSteal.get(currentTile.tier - 1) * (1+(currentTile.population / (currentPlayer.sparePopulation + 1)))) * multi;
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
            }
        }
        changeInfoCard();
        changePlayerStats();
        onAreaSelect();
        currentPlayer.calculateTotalPopulation();
        tileOwner.calculateTotalPopulation();
        checkIfEliminated(currentPlayer);
        checkIfEliminated(tileOwner);
        turnEnder.setDisable(false);
        surrender.setDisable(true);
        attemptAnnex.setDisable(true);
        attemptFight.setDisable(true);
    }

    @FXML
    protected void surrender() throws URISyntaxException, IOException {
        giveFood(1);
    }

    @FXML
    protected void fight() throws URISyntaxException, IOException {
        // Preparing stats for fight (Fight is in a ratio between players strength equal to population * any multipliers) - defender gets a 100% (x2) bonus
        Player currentPlayer = GameHandler.returnCurrentPlayer();
        double currentPlayerStrengthMulti = currentPlayer.cardStrengthMulti + currentPlayer.evolutionStrengthMulti;
        currentTile = GameHandler.getTileWithIndex(currentPlayer.index + 1);
        assert currentTile != null;
        Player tileOwner = GameHandler.getPlayerWithIndex(currentTile.getOwner());
        assert tileOwner != null;
        double tileOwnerStrengthMulti = tileOwner.cardStrengthMulti + tileOwner.evolutionStrengthMulti;
        long chance = Math.round((100 / (currentPlayer.sparePopulation * currentPlayerStrengthMulti + (currentTile.population * tileOwnerStrengthMulti) * 2)) * currentPlayer.sparePopulation * currentPlayerStrengthMulti);
        Random ran = new Random();
        int randomNumber = ran.nextInt(100) + 1;
        // checks if player won or not
        if (randomNumber <= chance){
            long populationLost = Math.round((1 - (chance / 100.0)) * 0.05 * currentPlayer.sparePopulation);
            currentPlayer.sparePopulation -= populationLost;
            populationLost = Math.round(((chance / 100.0)) * 0.3 * currentTile.population);
            currentTile.population -= populationLost;
            changeMessageLabel("victory", "GREEN");
        }else{
            long populationLost = Math.round((1 - (chance / 100.0)) * 0.3 * currentPlayer.sparePopulation);
            currentPlayer.sparePopulation -= populationLost;
            populationLost = Math.round(((chance / 100.0)) * 0.05 * currentTile.population);
            currentTile.population -= populationLost;
            giveFood(2);
            changeMessageLabel("defeated", "RED");
        }
        currentPlayer.calculateTotalPopulation();
        tileOwner.calculateTotalPopulation();
        checkIfEliminated(currentPlayer);
        checkIfEliminated(tileOwner);
        currentPlayer.cardStrengthMulti = 1.0;
        tileOwner.cardStrengthMulti = 1.0;
        changeInfoCard();
        changePlayerStats();
        onAreaSelect();
        turnEnder.setDisable(false);
        surrender.setDisable(true);
        attemptAnnex.setDisable(true);
        attemptFight.setDisable(true);
    }

    @FXML
    protected void annex() throws URISyntaxException, IOException {
        // Preparing stats for fight (Fight is in a ratio between players strength equal to population * any multipliers) - defender gets a 900% (x10) bonus
        Player currentPlayer = GameHandler.returnCurrentPlayer();
        double currentPlayerStrengthMulti = currentPlayer.cardStrengthMulti + currentPlayer.evolutionStrengthMulti;
        currentTile = GameHandler.getTileWithIndex(currentPlayer.index + 1);
        assert currentTile != null;
        Player tileOwner = GameHandler.getPlayerWithIndex(currentTile.getOwner());
        assert tileOwner != null;
        double tileOwnerStrengthMulti = tileOwner.cardStrengthMulti + tileOwner.evolutionStrengthMulti;
        long chance = Math.round( (100 / (currentPlayer.sparePopulation * currentPlayerStrengthMulti + ((currentTile.population * tileOwnerStrengthMulti) * 10.0))) * currentPlayer.sparePopulation * currentPlayerStrengthMulti);
        Random ran = new Random();
        int randomNumber = ran.nextInt(100) + 1;
        // checks if player won or not
        if (randomNumber <= chance){
            long populationLost = Math.round((1 - (chance / 100.0)) * 0.1 * currentPlayer.sparePopulation);
            currentPlayer.sparePopulation -= populationLost;
            populationLost = Math.round(((chance / 100.0)) * 0.8 * currentTile.population);
            currentTile.population = 0;
            currentTile.owner = currentPlayer.returnplayerNum();
            if (Objects.equals(tileOwner.synergy, currentTile.type)){
                tileOwner.foodOutput -= (int) (currentTile.foodProduction.get(currentTile.tier) * 1.1 * tileOwner.evolutionFoodProductionMulti);
                if (Objects.equals(currentPlayer.synergy, currentTile.type)){
                    currentPlayer.foodOutput += (int) (currentTile.foodProduction.get(currentTile.tier) * 1.1 * tileOwner.evolutionFoodProductionMulti);
                }else{
                    currentPlayer.foodOutput += (currentTile.foodProduction.get(currentTile.tier) * tileOwner.evolutionFoodProductionMulti);
                }
            }else{
                tileOwner.foodOutput -=  (currentTile.foodProduction.get(currentTile.tier) * tileOwner.evolutionFoodProductionMulti);
                if (Objects.equals(currentPlayer.synergy, currentTile.type)){
                    currentPlayer.foodOutput += (int) (currentTile.foodProduction.get(currentTile.tier) * 1.1 * tileOwner.evolutionFoodProductionMulti);
                }else{
                    currentPlayer.foodOutput += (currentTile.foodProduction.get(currentTile.tier) * tileOwner.evolutionFoodProductionMulti);
                }
            }
            changeMessageLabel("annexed", "GREEN");
        }else {
            long populationLost = Math.round((1 - (chance / 100.0)) * 0.8 * currentPlayer.sparePopulation);
            currentPlayer.sparePopulation -= populationLost;
            populationLost = Math.round(((chance / 100.0)) * 0.1 * currentTile.population);
            currentTile.population -= populationLost;
            giveFood(2);
            changeMessageLabel("defeated", "RED");
        }
        currentPlayer.calculateTotalPopulation();
        tileOwner.calculateTotalPopulation();
        checkIfEliminated(currentPlayer);
        checkIfEliminated(tileOwner);
        currentPlayer.updateFoodOutput();
        currentPlayer.cardStrengthMulti = 1.0;
        tileOwner.cardStrengthMulti = 1.0;
        changeInfoCard();
        changePlayerStats();
        onAreaSelect();
        turnEnder.setDisable(false);
        surrender.setDisable(true);
        attemptAnnex.setDisable(true);
        attemptFight.setDisable(true);
    }

    // Function that changes the info message
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

    // Checks if the players population has dropped below 20 whenever population is removed
    private void checkIfEliminated(Player player) throws IOException {
        if (player.totalPopulation <= 20){
            System.out.println(player.animal + " has been eliminated");
            playerCharacters.get(player.index).setOpacity(0);
            player.isEliminated = true;
        }
        // Checks if all but one player has been eliminated
        int eliminatedPlayers = 0;
        String winnersAnimal = Objects.requireNonNull(GameHandler.getPlayerWithIndex(1)).animal;
        for (int i = 0; i < playerCount; i++) {
            if (Objects.requireNonNull(GameHandler.getPlayerWithIndex(i+1)).isEliminated){
                eliminatedPlayers += 1;
            }else{
                winnersAnimal = Objects.requireNonNull(GameHandler.getPlayerWithIndex(i+1)).animal;
            }
        }
        if (eliminatedPlayers == playerCount - 1){
            FXMLLoader loader = new FXMLLoader(getClass().getResource(("resources/WinnerScreen.fxml")));
            Parent root = loader.load();
            Scene GameBoard = new Scene(root, 1280.0D, 720.0D);
            currentStage.setScene(GameBoard);
            Title.setText(winnersAnimal + "is the winner");
        }
    }

    // Picks a random card from array
    public void drawCard(Player player) throws IOException {
        Random random = new Random();
        int ranNum = random.nextInt(cards.length);
        String[] card = cards[ranNum];
        switch (card[2]) {
            case "food" -> player.food += Integer.parseInt(card[1]);
            case "xFood" -> player.food *= Double.parseDouble(card[1]);
            case "population" -> player.sparePopulation += Integer.parseInt(card[1]);
            case "xPopulation" -> player.sparePopulation *= Double.parseDouble(card[1]);
            case "strength" -> player.cardStrengthMulti += Double.parseDouble(card[1]);
            case "key" -> player.keys += Integer.parseInt(card[1]);
        }
        rollDoubleInfoLabel.setText(card[0]);
        player.calculateTotalPopulation();
        checkIfEliminated(player);
    }

    // Picks random (weighter) evolution
    private int getRandomEvolution(Player player) throws URISyntaxException {
        Random random = new Random();
        int ind = 0;
        int ranNum = random.nextInt(100) + 1;
        int totalChance = 100;
        String[] evolution = evolutions[0];
        for (int i = 0; i < evolutions.length; i++) {
            if (ranNum > totalChance - Integer.parseInt(evolutions[i][3])){
                evolution = evolutions[i];
                ind = i;
                break;
            }
            else{
                totalChance -= Integer.parseInt(evolutions[i][3]);
            }
        }
        switch (evolution[4]) {
            case "food" -> player.evolutionFoodConsumptionMulti += Double.parseDouble(evolution[5]);
            case "foodProd" -> player.evolutionFoodProductionMulti += Double.parseDouble(evolution[5]);
            case "strength" -> player.evolutionStrengthMulti += Double.parseDouble(evolution[5]);
            case "agility" -> player.hasAgility = true;
            case "survivor" -> player.hasSurvivor = true;
            case "escapist" -> player.hasEscapist = true;
            case "sly" -> player.hasSly= true;
            case "swiftness" -> player.hasSwiftness = true;
            case "sleepy" -> player.hasSleepy = true;
            case "slowness" -> player.hasSlowness = true;
        }
        tempEvoPane.setOpacity(1);
        tempEvoTitleLabel.setText(evolution[0]);
        tempEvoInfoLabel.setText(evolution[1]);
        Image img = new Image(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("images")).toURI() + evolution[2] + "Evolution.png");
        tempEvolutionSlot.setImage(img);
        return ind;
    }

    private void drawEvolution(Player player) throws URISyntaxException, IOException {
        choosingEvo = true;
        if (player.evolutionIndexes.size() != 3){
            turnEnder.setDisable(false);
            int ranNum = getRandomEvolution(player);
            clickEvoReplaceLabel.setOpacity(0);
            player.evolutionIndexes.add(ranNum);
            updateEvolutionCards(player);
            choosingEvo = false;
        }else{
            ranEvoValue = getRandomEvolution(player);
            clickEvoReplaceLabel.setOpacity(1);
        }
        player.calculateTotalPopulation();
        checkIfEliminated(player);
    }

    @FXML
    // Either displays or replaces an evolution
    protected void evoButtonPress(ActionEvent event) throws URISyntaxException {
        Player currentPlayer = GameHandler.returnCurrentPlayer();
        String evoName = ((Node) event.getSource()).getId();
        int evoIndex = Integer.parseInt(evoName.substring(Math.max(0, evoName.length() - 1)));
        if ((evoIndex == 1 && evolutionSlot1.getOpacity() == 1) || (evoIndex == 2 && evolutionSlot2.getOpacity() == 1) || (evoIndex == 3 && evolutionSlot3.getOpacity() == 1)){
            String[] evolution = evolutions[currentPlayer.evolutionIndexes.get(evoIndex-1)];
            if (choosingEvo) {
                // Replaces evolution
                currentPlayer.evolutionIndexes.remove(evoIndex-1);
                currentPlayer.evolutionIndexes.add(ranEvoValue);
                turnEnder.setDisable(false);
                updateEvolutionCards(currentPlayer);
                choosingEvo = false;
            } else {
                // Displays evolution info
                tempEvoPane.setOpacity(1);
                tempEvoTitleLabel.setText(evolution[0]);
                tempEvoInfoLabel.setText(evolution[1]);
                clickEvoReplaceLabel.setOpacity(0);
                Image img = new Image(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("images")).toURI() + evolution[2] + "Evolution.png");
                tempEvolutionSlot.setImage(img);
            }
        }
    }

    private void updateEvolutionCards(Player player) throws URISyntaxException {
        for (int i = 0; i < player.evolutionIndexes.size(); i++) {
            String[] evolution = evolutions[player.evolutionIndexes.get(i)];
            Image img = new Image(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("images")).toURI() + evolution[2] + "Evolution.png");
            switch (i) {
                case 0 ->{
                    evolutionSlot1.setImage(img);
                    evolutionSlot1.setOpacity(1);
                    evolutionLabel1.setText(evolution[0]);
                }
                case 1 ->{
                    evolutionSlot2.setImage(img);
                    evolutionSlot2.setOpacity(1);
                    evolutionLabel2.setText(evolution[0]);
                }
                case 2 ->{
                    evolutionSlot3.setImage(img);
                    evolutionSlot3.setOpacity(1);
                    evolutionLabel3.setText(evolution[0]);
                }
            }
        }
    }
    
    @FXML
    protected void changeBreedRate(){
        Player currentPlayer = GameHandler.returnCurrentPlayer();
        String string = popGrowthRateChoiceBox.getValue();
        String[] output = string.split(" ");
        breedAmount = Integer.parseInt(output[2]) / 100.0;
        if (currentPlayer != null) {
            currentPlayer.popGrowthRate = breedAmount;
        }
    }
}