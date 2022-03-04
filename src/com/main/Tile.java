package com.main;

public class Tile {
    private int x;
    private int y;
    private int index;
    int owner = 0;
    int population;
    Vector2 position;


    public Tile(int x, int y, int index,int owner, int population) {
        this.position = new Vector2(x,y);
        this.index = index;
        this.owner = owner;
        this.population = population;
    }




}
