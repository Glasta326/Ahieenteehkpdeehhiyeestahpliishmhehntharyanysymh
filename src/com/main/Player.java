package com.main;

import javafx.scene.control.ChoiceBox;

import java.util.Random;

public class Player
{
    private int playerNum;
    private int food;
    private Tile currentTile;
    private String animal;
    private int index;

    public void BuyArea(Tile currentTile){
        //currentTile.owner = player.Id
        //do shit here
    }

    public void IncreasePopulation(){
        currentTile.population += 1; //replace 1 with actual amount
    }

    public Player(int playerNum, int food, String animal, int index /*Tile currentTile*/) {
        this.playerNum = playerNum;
        this.food = food;
        this.animal = ""+animal;
        this.index = index;
    }
}
