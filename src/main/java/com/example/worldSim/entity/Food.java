package com.example.worldSim.entity;

import com.example.worldSim.factory.Resource;
import com.example.worldSim.type.TileType;

public class Food extends Resource {

    public Food(int posX, int posY, int amount) {
        super(posX, posY, TileType.FOOD, amount);
    }
}
