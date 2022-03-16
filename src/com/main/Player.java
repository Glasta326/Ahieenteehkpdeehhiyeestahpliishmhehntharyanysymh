package com.main;

public class Player
{
    private final int playerNum;
    public int food;
    public int totalPopulation;
    public int sparePopulation;
    public int populationUpkeep;
    public int foodProduction;
    public int foodOutput;
    private Tile currentTile;
    public final String animal;
    public int index;
    public int lives;

    public void IncreasePopulation(){
        currentTile.population += 1; //replace 1 with actual amount
    }

    public int returnplayerNum(){
        return playerNum;
    }

    public void updateFoodOutput(){
        foodOutput = foodProduction - populationUpkeep;
    }

    public Player(int playerNum, int food, String animal, int index, boolean hasRolled /*Tile currentTile*/) {
        this.playerNum = playerNum;
        this.food = food;
        this.animal = animal;
        this.index = index;
        this.totalPopulation = 100;
        this.sparePopulation = 100;
        this.populationUpkeep = (int)Math.floor(totalPopulation * 0.5);
        this.foodProduction = 50;
        this.foodOutput = foodProduction - populationUpkeep;
        this.lives = 3;
    }
}
