package com.main;

public class Tile {
    private int x;
    private int y;
    private int index;
    Vector2 position;

    public Tile(int x, int y, int index) {
        this.position = new Vector2(x,y);
        this.index = index;
    }


}
