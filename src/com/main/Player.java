package com.main;

import java.util.Random;

public class Player
{
    private int food;
    private Tile currentTile;
    private String animal;
    private int coolrandomfx;

    public int Rolldice() {
        Random ran = new Random();
        for (int i = 0; i < 20; i++) {
            ran = new Random();
            coolrandomfx = ran.nextInt(1) + 6;
        }
        return coolrandomfx;
    }

    public void BuyArea(Tile currentTile){
        //currentTile.owner = player.Id
        //do shit here
    }

    public void IncreasePopulation(){
        currentTile.population += 1; //replace 1 with actual amount
    }

    public Player() {

    }
}
