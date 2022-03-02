package com.company;

import java.util.Scanner;
import java.lang.Integer;

public class GameHandler {
    static int playerCount = 0;
    public static void main(String[] args) {
        initialise();
    }

    public static void initialise(){
        Scanner scanner = new Scanner(System.in);
        playerCount = Integer.parseInt(scanner.nextLine());
        System.out.println(playerCount);
        for (int i = 0; i < playerCount; i++) {

        }
    }
}
