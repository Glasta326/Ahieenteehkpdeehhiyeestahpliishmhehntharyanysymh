package com.main;

import java.util.ArrayList;

public class Tile {

    String name;
    String type;
    int index;
    int costs;
    ArrayList<Integer> tierCosts;
    ArrayList<Integer> foodProduction;
    ArrayList<Integer> foodSteal;
    int owner;
    int population;
    int tier;

    public Tile(String name, String type, int costs, ArrayList<Integer> tierCosts, ArrayList<Integer> foodProduction, ArrayList<Integer> foodSteal, int index) {
        this.name = name;
        this.index = index;
        this.type = type;
        this.costs = costs;
        this.tierCosts = tierCosts;
        this.foodProduction = foodProduction;
        this.foodSteal = foodSteal;
        this.tier = 0;
        this.owner = 0;
        this.population = 0;
    }
    public void setOwner(int playernum){
        this.owner = playernum;
    }
    public int getOwner(){
        return this.owner;
    }
    public void remOwner(){
        this.owner = 0;
    }
}

