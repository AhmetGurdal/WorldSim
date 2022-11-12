package com.example.worldSim.entity;

import com.example.worldSim.factory.Tile;
import lombok.Data;

@Data
public class WorldState {

    public Tile[] currentTileState;
    public int currentNOP;
    public int currentNOF;
    public int currentNOW;

    WorldState(World world){
        this.currentTileState = world.getTiles().clone();
        this.currentNOP = world.getNOP();
        this.currentNOF = world.getNOF();
        this.currentNOW = world.getNOW();
    }



}
