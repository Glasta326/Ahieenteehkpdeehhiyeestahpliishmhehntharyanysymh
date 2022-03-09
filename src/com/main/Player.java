package com.main;

import javafx.scene.control.ChoiceBox;

import java.util.Random;

public class Player
{
    private final int playerNum;
    private int food;
    private Tile currentTile;
    private final String animal;
    public int index;
    public boolean hasRolled;

    public void BuyArea(Tile currentTile){
        currentTile.SetOwner(playerNum);
        //subtract some form of currency idfk
    }

    public void IncreasePopulation(){
        currentTile.population += 1; //replace 1 with actual amount
    }

    public int returnplayerNum(){
        return playerNum;
    }

    public Player(int playerNum, int food, String animal, int index, boolean hasRolled /*Tile currentTile*/) {
        this.playerNum = playerNum;
        this.food = food;
        this.animal = animal;
        this.index = index;
        this.hasRolled = hasRolled;
    }
}
