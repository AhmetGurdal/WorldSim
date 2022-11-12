package com.example.worldSim.factory;

import com.example.worldSim.factory.Tile;
import com.example.worldSim.factoryImpl.ResourceImpl;
import com.example.worldSim.type.TileType;

public abstract class Resource extends Tile implements ResourceImpl {
    int amount;
    public Resource(int x, int y, TileType type, int amount) {
        super(x, y, type);
        this.amount = amount;
    }

    public int getAmount(){
        return this.amount;
    }
}
