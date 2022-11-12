package com.example.worldSim.controller.state;

import com.example.worldSim.controller.state.response.StateResponse;
import com.example.worldSim.controller.world.response.WorldResponse;
import com.example.worldSim.entity.World;
import com.example.worldSim.service.WorldService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("state")
public class StateController {

    @GetMapping("/{wid}")
    List<StateResponse> getStates(@PathVariable int wid){
        ArrayList<StateResponse> response = new ArrayList<StateResponse>();
        World _world = WorldService.worlds.get(wid);
        _world.getStates().forEach((k) -> response.add(new StateResponse(k, _world.getSizeX(), _world.getSizeY())));
        // System.out.println(response.size());
        return response;
    }

}
