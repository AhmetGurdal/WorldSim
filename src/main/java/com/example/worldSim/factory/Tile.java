package com.example.worldSim.factory;

import com.example.worldSim.type.TileType;

public abstract class Tile {
    public boolean isHuman;
    public boolean isFood;
    public boolean isWater;
    public boolean isGround;

    public int posX;
    public int posY;


    public Tile(int x, int y){
        this.isGround = true;
        this.isFood = false;
        this.isWater = false;
        this.isHuman = false;
        this.posX = x;
        this.posY = y;
    }
    public Tile(int x, int y, TileType type){
        this.posX = x;
        this.posY = y;
        switch (type){
            case WATER:
                this.isWater = true;
                this.isHuman = false;
                this.isGround = false;
                this.isFood = false;
                break;

            case FOOD:
                this.isFood = true;
                this.isHuman = false;
                this.isGround = false;
                this.isWater = false;
                break;

            case HUMAN:
                this.isHuman = true;
                this.isGround = false;
                this.isFood = false;
                this.isWater = false;
                break;

            default:
                this.isGround = true;
                this.isFood = false;
                this.isWater = false;
                this.isHuman = false;
                break;
        }
    }

    public String toString(){
        if(isHuman){
            return "H";
        }
        if(isFood){
            return "F";
        }
        if(isWater){
            return "W";
        }
        else{
            return "G";
        }

    }

    public TileType getType(){
        if(this.isGround){
            return TileType.GROUND;
        } else if (this.isHuman) {
            return TileType.HUMAN;
        }else if (this.isFood){
            return TileType.FOOD;
        } else{
            return TileType.WATER;
        }
    }

}
