package com.example.worldSim.controller.world.request;

import com.example.worldSim.type.TileType;
import lombok.Data;

@Data
public class AddResourceWorldRequest {
    public int x;
    public int y;
    public int amount;
    public TileType type;
}
