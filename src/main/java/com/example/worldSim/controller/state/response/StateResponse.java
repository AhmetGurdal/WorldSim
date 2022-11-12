package com.example.worldSim.controller.state.response;

import com.example.worldSim.entity.WorldState;
import com.example.worldSim.util.TileUtils;
import lombok.Data;

import java.util.List;

@Data
public class StateResponse {

    List<String> tiles;

    public StateResponse(WorldState state, int x, int y){
        this.tiles = TileUtils.getTilesString(state.getCurrentTileState(), x,y);
    }
}
