package com.main;

import java.util.ArrayList;

public class Tile {

    String name;
    String type;
    int index;
    int costs;
    ArrayList<Integer> tierCosts = new ArrayList<>();
    ArrayList<Integer> foodProduction = new ArrayList<>();
    ArrayList<Integer> foodSteal = new ArrayList<>();
    int owner;
    int population;

    public Tile(String name, String type, int costs, ArrayList<Integer> tierCosts, ArrayList<Integer> foodProduction, ArrayList<Integer> foodSteal, int index) {
        this.name = name;
        this.index = index;
        this.type = type;
        this.costs = costs;
        this.tierCosts = tierCosts;
        this.foodProduction = foodProduction;
        this.foodSteal = foodSteal;
    }
    public void SetOwner(int playernum){
        this.owner = playernum;
    }
    public int GetOwner(){
        return this.owner;
    }
    public void RemOwner(){
        this.owner = 0;
    }
}

