package com.main;

import javafx.application.Application;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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

    public static void gameSetup() throws IOException {
        File file = new File("resources/data/Areas.csv");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        String[] tempArr;
        while((line = br.readLine()) != null) {
            tempArr = line.split(delimiter);

        }
    }
}
