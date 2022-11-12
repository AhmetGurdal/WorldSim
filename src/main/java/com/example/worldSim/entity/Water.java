package com.example.worldSim.entity;

import com.example.worldSim.factory.Resource;
import com.example.worldSim.type.TileType;

public class Water extends Resource {
    public Water(int posX, int posY, int amount){
        super(posX, posY, TileType.WATER, amount);
    }
}
