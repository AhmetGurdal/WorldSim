package com.example.worldSim.service;

import com.example.worldSim.controller.world.request.AddResourceWorldRequest;
import com.example.worldSim.controller.world.request.CreateWorldRequest;
import com.example.worldSim.controller.world.request.UpdateWorldRequest;
import com.example.worldSim.controller.world.response.WorldResponse;
import com.example.worldSim.entity.*;

import java.util.HashMap;

public class WorldService {

    public static HashMap<Integer, World> worlds = new HashMap<Integer, World>();


    public static WorldResponse createWorld(CreateWorldRequest createWorldRequest) {
        int id = World.getWorldCount();
        World world;
        if (createWorldRequest.nop != null && createWorldRequest.nof != null && createWorldRequest.now != null) {
            world = new World(createWorldRequest.name, createWorldRequest.x, createWorldRequest.y, createWorldRequest.nop, createWorldRequest.nof, createWorldRequest.now);
        }
        else{
            world = new World(createWorldRequest.name, createWorldRequest.x, createWorldRequest.y);
        }

        WorldService.worlds.put(id, world);
        World.setWorldCount(World.getWorldCount() + 1);
        return new WorldResponse(id, world);
    }

    public static WorldResponse updateWorld(int id, UpdateWorldRequest updateWorldRequest) {
        World _world = WorldService.worlds.get(id);
        System.out.println(updateWorldRequest);
        if (updateWorldRequest.name != null) {
            _world.setName(updateWorldRequest.name);
        }
        if (updateWorldRequest.x != null && updateWorldRequest.y != null && updateWorldRequest.type != null) {
            int _x = updateWorldRequest.x;
            int _y = updateWorldRequest.y;
            System.out.println(updateWorldRequest.type);
            _world.setTile(_x, _y, updateWorldRequest.type);

        }
        //System.out.println(_world);
        worlds.put(id, _world);
        return new WorldResponse(id, _world);
    }

    public static WorldResponse addResource(int id, AddResourceWorldRequest addResourceWorldRequest) {
        World _world = worlds.get(id);
        _world.addResource(addResourceWorldRequest);
        worlds.put(id, _world);
        return new WorldResponse(id, _world);
    }
}
