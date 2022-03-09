package com.main;

public class Tile {

    String name;
    String type;
    int index;
    int costs;
    int[] tierCosts;
    int[] foodProduction;
    int[] foodSteal;
    int owner;
    int population;

    public Tile(String name, int index, String type, int costs, int[] tierCosts, int[] foodProduction, int[] foodSteal) {
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

