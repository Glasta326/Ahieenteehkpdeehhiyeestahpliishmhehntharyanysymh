package com.main;

import javafx.application.Application;

import java.io.IOException;
import java.util.Scanner;
import java.lang.Integer;

public class GameHandler {
    int playerCount = 0;

    public static void main(String[] args) throws IOException {
        Application.launch(GraphicsSetup.class, args);
    }

    public void initialise(){
        Scanner scanner = new Scanner(System.in);
        playerCount = Integer.parseInt(scanner.nextLine());
        System.out.println(playerCount);
        for (int i = 0; i < playerCount; i++) {
        }
    }

    public void updatePlayers(int numPlayers){

    }
}
