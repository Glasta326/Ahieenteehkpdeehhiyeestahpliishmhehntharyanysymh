package com.main;

import javafx.application.Application;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

public class GameHandler {
    public static final String delimiter = ",";
    public static ArrayList<Tile> tiles;

    public static void main(String[] args) {
        Application.launch(GraphicsSetup.class, args);
    }


    public static int Rolldice() {
        int randomNumber;
        Random ran = new Random();
        randomNumber = ran.nextInt(6) + 1;
        return randomNumber;
    }

    public static void gameSetup() throws IOException, URISyntaxException {
        File root = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("data")).toURI());
        File file = new File(root, "Areas.csv");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        String[] Arr;
        while((line = br.readLine()) != null) {
            Arr = line.split(delimiter);

            Tile tile = new Tile(Arr[0], Integer.parseInt(Arr[1]),Arr[2],Integer.parseInt(Arr[3]), Stream.of(Arr[4])
                    .mapToInt(Integer::parseInt).toArray(),Stream.of(Arr[5])
                    .mapToInt(Integer::parseInt).toArray(),Stream.of(Arr[6])
                    .mapToInt(Integer::parseInt).toArray());//there is a better way to do this but i am lazy
            tiles.add(tile);
        }
    }
}