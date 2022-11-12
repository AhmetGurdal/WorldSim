package com.example.worldSim.type;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TileType {
    WATER("WATER"),
    GROUND("GROUND"),
    FOOD("FOOD"),
    HUMAN("HUMAN");

    private String tileCode;

    TileType(String tileCode) {
        this.tileCode = tileCode;
    }

    public String getTileCode() {

        return this.tileCode;
    }

    @JsonCreator
    public static TileType getDepartmentFromCode(String value) {

        for (TileType tileType : TileType.values()) {
            if (tileType.getTileCode().equals(value)) {
                return tileType;
            }
        }

        return null;
    }

}
