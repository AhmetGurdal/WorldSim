package com.example.worldSim.controller.main.response;

import lombok.Data;

import java.util.List;

@Data
public class NextWorldStateResponse {
    List<String> tiles;
    int world_age;
    boolean isHumansAlive;

    int current_nop;

    public NextWorldStateResponse(List<String> tiles, int world_age, boolean isHumansAlive, int total_people){
        this.tiles = tiles;
        this.world_age = world_age;
        this.isHumansAlive = isHumansAlive;
        this.current_nop = total_people;
    }
}
