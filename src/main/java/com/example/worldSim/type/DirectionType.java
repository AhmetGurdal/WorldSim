package com.example.worldSim.type;

public enum DirectionType {
    LEFT_UP,
    UP,
    RIGHT_UP,
    LEFT,
    RIGHT,
    LEFT_DOWN,
    DOWN,
    RIGHT_DOWN;

    public DirectionType[] directionTypes(){
        return DirectionType.values();
    }
}
