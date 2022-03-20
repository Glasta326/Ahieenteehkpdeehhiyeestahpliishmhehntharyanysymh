package com.main;

import java.util.ArrayList;

public class Player
{
    private final int playerNum;
    public int food;
    public int totalPopulation;
    public int sparePopulation;
    public long populationUpkeep;
    public int foodProduction;
    public int foodOutput;
    private Tile currentTile;
    public final String animal;
    public int index;
    public int turnsToMiss;
    public int zooTurnsLeft;
    public double cardStrengthMulti;
    public double popGrowthRate;
    public int keys;
    public ArrayList<Integer> evolutionIndexes;
    public String synergy;
    public double evolutionStrengthMulti;
    public double evolutionFoodConsumptionMulti;
    public double evolutionFoodProductionMulti;
    public boolean hasAgility;
    public boolean hasSly;
    public boolean hasSleepy;
    public boolean hasSurvivor;
    public boolean hasEscapist;
    public boolean hasSwiftness;
    public boolean hasSlowness;
    public boolean isEliminated;


    public void IncreasePopulation(){
        currentTile.population += 1; //replace 1 with actual amount
    }

    public void calculateTotalPopulation(){
        int tempPop = 0;
        ArrayList<Tile> tiles = GameHandler.getTilesOwnedBy(this);
        for (Tile tile : tiles){
            tempPop += tile.population;
        }
        tempPop += sparePopulation;
        totalPopulation = tempPop;
    }

    public int returnplayerNum(){
        return playerNum;
    }

    public void updateFoodOutput(){
        populationUpkeep = Math.round(totalPopulation * evolutionFoodConsumptionMulti * 0.5);
        foodOutput = foodProduction - (int) populationUpkeep;
    }

    public Player(int playerNum, int food, String animal, int index, boolean hasRolled, String synergy) {
        this.playerNum = playerNum;
        this.food = food;
        this.animal = animal;
        this.index = index;
        this.totalPopulation = 100;
        this.sparePopulation = 100;
        this.populationUpkeep = (int)Math.floor(totalPopulation * 0.5);
        this.foodProduction = 50;
        this.foodOutput = foodProduction - (int) populationUpkeep;
        this.turnsToMiss = 0;
        this.zooTurnsLeft = 0;
        this.popGrowthRate = 0.0;
        this.synergy = synergy;
        this.cardStrengthMulti = 1.0;
        this.evolutionStrengthMulti = 1.0;
        this.evolutionFoodConsumptionMulti = 1.0;
        this.evolutionFoodProductionMulti = 1.0;
        this.evolutionIndexes = new ArrayList<>();
        this.isEliminated = false;
    }
}
