package com.example.worldSim.controller.world.response;

import com.example.worldSim.entity.World;
import com.example.worldSim.util.TileUtils;
import lombok.Data;

import java.util.List;

@Data
public class WorldResponse {
    int id;
    String name;
    boolean isWorldValid;
    int totalPeople;
    int totalFood;
    int totalWater;
    int sizeX;
    int sizeY;
    List<String> tiles;

    public WorldResponse(int id, World world){
        this.id = id;
        this.name = world.getName();
        this.isWorldValid = world.getIsWorldValid();
        this.totalPeople = world.getNOP();
        this.totalFood = world.getNOF();
        this.totalWater = world.getNOW();
        this.sizeX = world.getSizeX();
        this.sizeY = world.getSizeY();
        this.tiles = TileUtils.getTilesString(world.getTiles(), world.getSizeX(), world.getSizeY());
    }

}
