package com.example.worldSim.controller.world.request;

import com.example.worldSim.type.TileType;
import lombok.Data;

@Data
public class UpdateWorldRequest {
    public String name;
    public Integer x;
    public Integer y;
    public TileType type;

    public Integer amount;

}
