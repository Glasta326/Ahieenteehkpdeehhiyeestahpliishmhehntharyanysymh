package com.main;

import javafx.application.Application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

// Just holds a bunch of general use functions
public class GameHandler {
    static HashMap<String,String> synergies = new HashMap<>();


    public static Player currentPlayer;
    public static int turn = 1;
    public static int playerCount;
    public static final String delimiter = ",";
    public static ArrayList<Tile> tiles = new ArrayList<>();
    public static ArrayList<Player> players = new ArrayList<>();


    public static void main(String[] args) {
        turn = 1;
        synergies.put("Cow","Savanna");
        synergies.put("Pig","Grassland");
        synergies.put("Penguin","Tundra");
        synergies.put("Sloth","Jungle");
        synergies.put("Hallucigenia","Marine");
        synergies.put("Blue Dragon","Marine");
        synergies.put("Basilisk","Jungle");
        synergies.put("Probiscus Monkey","Jungle");
        Application.launch(GraphicsSetup.class, args);
    }

    // Returns the player whose turn it currently is
    public static Player returnCurrentPlayer() {
        return currentPlayer;
    }

    // Sets the player whose turn it currently is
    public static void setCurrentPlayer(int player){
        currentPlayer = players.get(player);
    }

    // Simple random number generator for a die
    public static int Rolldice() {
        int randomNumber;
        Random ran = new Random();
        randomNumber = ran.nextInt(6) + 1;
        return randomNumber;
    }

    public static String getSynergyFor(String animal){
        return synergies.get(animal);
    }

    // Is called when the "Continue to Game" button is pressed after players are selected
    public static void gameSetup(int playerNum, ArrayList<String> animals) throws IOException, URISyntaxException {
        playerCount = playerNum;
        // Creates player objects
        for (int i = 0; i < playerCount; i++) {
            players.add(new Player(i+1, 2000, animals.get(i), 0, false, synergies.get(animals.get(i))));
        }
        // Reading "Areas.csv" file.
        File root = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("data")).toURI());
        File file = new File(root, "Areas.csv");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;

        int i = 0;
        // Creates tile objects
        while((line = br.readLine()) != null) {
            String[] Arr;
            String[] ArrCostsS; ArrayList<Integer> ArrCosts = new ArrayList<>();
            String[] ArrProdS; ArrayList<Integer> ArrProd = new ArrayList<>();
            String[] ArrStealS; ArrayList<Integer> ArrSteal = new ArrayList<>();
            i += 1;
            Arr = line.split(delimiter);
            // Converting string of characters to array of strings
            ArrCostsS = Arr[3].split("-");
            ArrProdS = Arr[4].split("-");
            ArrStealS = Arr[5].split("-");
            // Converting string array to int array

            for (String string : ArrCostsS) {
                ArrCosts.add(Integer.parseInt(string));
            }
            for (String string : ArrProdS) {
                ArrProd.add(Integer.parseInt(string));
            }
            for (String string : ArrStealS) {
                ArrSteal.add(Integer.parseInt(string));
            }
            Tile tile = new Tile(Arr[0], Arr[1], Integer.parseInt(Arr[2]), ArrCosts, ArrProd, ArrSteal, i);
            tiles.add(tile);
        }
    }

    public static Player getPlayerWithIndex(int i){
        // Loops through all players and checks to see if their player number matches the passed parameter - then returns that player.
        for (Player player : players){
            if (player.returnplayerNum() == i){
                return player;
            }
        }
        return null;
    }

    public static ArrayList<Tile> getTilesOwnedBy(Player player){
        // Checks if the tiles "Owner" number is equal to the player number
        ArrayList<Tile> ownedTiles = new ArrayList<>();
        for (Tile tile : tiles){
            if (tile.owner == player.returnplayerNum()){
                ownedTiles.add(tile);
            }
        }
        return ownedTiles;
    }

    public static Tile getTileWithIndex(int i){
        // Loops through all tiles and checks to see if their index matches the passed parameter - then returns that tile.
        for (Tile tile : tiles){
            if (tile.index == i) {
                return tile;
            }
        }
        return null;
    }

    public static Tile getTileWithName(String name){
        for (Tile tile : tiles){
            if (Objects.equals(tile.name, name)) {
                return tile;
            }
        }
        return null;
    }
}