package com.main;

import javafx.application.Application;
import javafx.scene.control.ChoiceBox;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class GameHandler {
    public static int playerCount;
    public static final String delimiter = ",";
    public static ArrayList<Tile> tiles = new ArrayList<>();
    public static ArrayList<Player> players = new ArrayList<>();

    public static void main(String[] args) {
        Application.launch(GraphicsSetup.class, args);
    }


    public static int Rolldice() {
        int randomNumber;
        Random ran = new Random();
        randomNumber = ran.nextInt(6) + 1;
        return randomNumber;
    }

    public static void gameSetup(int playerNum, ArrayList<ChoiceBox<String>> animals) throws IOException, URISyntaxException {
        playerCount = playerNum;
        for (int i = 0; i < playerCount; i++) {
            players.add(new Player(i+1, 2000, ""+animals.get(i), 0));
        }
        File root = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("data")).toURI());
        File file = new File(root, "Areas.csv");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        String[] Arr;
        while((line = br.readLine()) != null) {
            Arr = line.split(delimiter);
        }
    }
}