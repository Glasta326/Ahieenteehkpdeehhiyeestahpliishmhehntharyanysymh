package com.main;

import javafx.application.Application;

import java.io.IOException;
import java.util.Scanner;
import java.lang.Integer;

public class GameHandler {
    static int playerCount = 0;

    public static void main(String[] args) throws IOException {
        Application.launch(GraphicsSetup.class, args);
    }

    public static void initialise(){
        Scanner scanner = new Scanner(System.in);
        playerCount = Integer.parseInt(scanner.nextLine());
        System.out.println(playerCount);
        for (int i = 0; i < playerCount; i++) {
        }
    }
}
