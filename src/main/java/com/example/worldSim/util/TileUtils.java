package com.example.worldSim.util;

import com.example.worldSim.factory.Tile;
import com.example.worldSim.type.DirectionType;

import java.util.ArrayList;
import java.util.List;

public class TileUtils {
    public static List<String> getTilesString(Tile[] tiles, int sizeX, int sizeY) {
        List<String> result = new ArrayList<String>();
        for (int x = 0; x < sizeX; x++) {
            String line = "";
            for (int y = 0; y < sizeY; y++) {
                line += tiles[x * sizeY + y];
            }
            result.add(line);
        }
        return result;
    }

    public static void removeFromList(List<DirectionType> list, DirectionType itemToRemove){
        try {
            int index = list.indexOf(itemToRemove);
            list.remove(index);
        }
        catch (Exception e){

        }
    }
}
