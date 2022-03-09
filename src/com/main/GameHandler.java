package com.main;

import javafx.application.Application;
import javafx.scene.control.ChoiceBox;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

public class GameHandler {
    public static Player currentPlayer;
    public static int turn = 1;
    public static int playerCount;
    public static final String delimiter = ",";
    public static ArrayList<Tile> tiles = new ArrayList<>();
    public static ArrayList<Player> players = new ArrayList<>();

    public static void main(String[] args) {
        turn = 1;
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
    // Is called when the "Continue to Game" button is pressed after players are selected
    public static void gameSetup(int playerNum, ArrayList<String> animals) throws IOException, URISyntaxException {
        playerCount = playerNum;
        // Creates player objects
        for (int i = 0; i < playerCount; i++) {
            players.add(new Player(i, 2000, animals.get(i), 0, false));
        }
        // Reading "Areas.csv" file
        File root = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("data")).toURI());
        File file = new File(root, "Areas.csv");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        String[] Arr;
        String[] ArrCostsS; ArrayList<Integer> ArrCosts = new ArrayList<>();
        String[] ArrProdS; ArrayList<Integer> ArrProd = new ArrayList<>();
        String[] ArrStealS; ArrayList<Integer> ArrSteal = new ArrayList<>();
        int i = 0;
        // Creates tile objects
        while((line = br.readLine()) != null) {
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
}