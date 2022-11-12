package com.example.worldSim.controller.main;

import com.example.worldSim.controller.main.response.NextWorldStateResponse;
import com.example.worldSim.entity.World;
import com.example.worldSim.entity.WorldState;
import com.example.worldSim.service.WorldService;
import com.example.worldSim.util.TileUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

@RestController
@RequestMapping("main")
public class MainController {

    @GetMapping("/next/{wid}")
    NextWorldStateResponse nextWorldState(@PathVariable int wid){
        if(WorldService.worlds.containsKey(wid)){
            World _world = WorldService.worlds.get(wid);
            WorldState currentState = _world.getNextState();
            System.out.println(currentState);
            return new NextWorldStateResponse(
                    TileUtils.getTilesString(currentState.getCurrentTileState(), _world.getSizeX(), _world.getSizeY()),
                    _world.getStates().size() -1 ,
                    _world.getIsCreaturesAlive(),
                    _world.getNOP()
            );

        }
        throw new EmptyStackException();
    }

    @GetMapping("/run/{wid}")
    int runWorld(@PathVariable int wid){
        if(WorldService.worlds.containsKey(wid)){
            World _world = WorldService.worlds.get(wid);
            int world_year = 0;
            while(_world.getNOP() != 0){
                world_year += 1;
                _world.getNextState();
            }
            return world_year;
        }
        return 0;
    }

}
