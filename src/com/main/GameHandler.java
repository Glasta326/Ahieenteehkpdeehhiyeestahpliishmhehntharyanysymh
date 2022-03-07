package com.main;

import javafx.application.Application;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class GameHandler {
    public static final String delimiter = ",";
    public static ArrayList<Tile> tiles;
    //fdfdf

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
        }


    }
}
