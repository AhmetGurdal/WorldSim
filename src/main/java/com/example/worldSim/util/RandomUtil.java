package com.example.worldSim.util;

import com.example.worldSim.type.DirectionType;

import java.util.List;
import java.util.NoSuchElementException;

public class RandomUtil {
    public static int randomIntBetween(int s, int e){
        if(s < e){
            return (int) Math.round((Math.random() * (e-s -1))  + (s));
        }
        throw new IllegalArgumentException();
    }

    public static int uniqueRandomInt(int s, int e, List<Integer> prev){
        if(e-s == prev.size()){
            throw new NoSuchElementException();
        }

        while(true) {
            int random = randomIntBetween(s, e);
            if(!prev.contains(random)){
                return random;
            }
        }
    }

    public static DirectionType selectDirection(){
        return DirectionType.DOWN;
    };
}
