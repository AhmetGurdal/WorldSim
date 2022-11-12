package com.example.worldSim.factory;

import com.example.worldSim.entity.Food;
import com.example.worldSim.entity.Water;
import com.example.worldSim.entity.World;
import com.example.worldSim.type.TileType;

public abstract class Creature extends Tile {

    private int id;
    private boolean isAlive;
    private int satietyLevel;
    private int hydrationLevel;
    private int age;
    public static int creatureCount;
    public Creature(int x, int y, TileType type, boolean isAlive, int satietyLevel, int hydrationLevel) {
        super(x, y, type);
        this.id = creatureCount;
        this.age = 0;
        this.hydrationLevel = hydrationLevel;
        this.satietyLevel = satietyLevel;
        this.isAlive = isAlive;
        creatureCount += 1;
    }

    public abstract void eat(Food food);
    public abstract void drink(Water water);

    public void consume(Tile tile){
        if(tile.getType() == TileType.WATER){
            this.drink((Water) tile);
        }
        else if(tile.getType() == TileType.FOOD){
            this.eat((Food) tile);
        }
    }

    public abstract void move(World world);

    public void die(World world){
        world.setNop(world.getNOP() - 1);
        this.isAlive = false;
    };

    public boolean getIsAlive(){
        return this.isAlive;
    }

    public int getSatietyLevel() {
        return this.satietyLevel;
    }

    public int getHydrationLevel(){
        return this.hydrationLevel;
    }

    public void increaseAge(){
        this.age += 1;
    }

    public void decreaseSatietyLevelBy(int amount){
        this.satietyLevel -= amount;
    }
    public void decreaseHydrationLevelBy(int amount){
        this.hydrationLevel -= amount;
    }

    public void increaseSatietyLevelBy(int amount){
        this.satietyLevel += amount;
    }

    public void increaseHydrationLevelBy(int amount){
        this.hydrationLevel += amount;
    }

}
