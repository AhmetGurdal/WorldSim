package com.example.worldSim.factoryImpl;

import com.example.worldSim.entity.WorldState;

public interface StateImpl {

    public WorldState getPrevState();

    public WorldState getCurrentState();

    public WorldState getNextState();
}
