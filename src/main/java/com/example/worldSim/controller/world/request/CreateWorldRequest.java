package com.example.worldSim.controller.world.request;

import lombok.Data;

@Data
public class CreateWorldRequest {
    public String name;
    public Integer x;
    public Integer y;
    public Integer nop;
    public Integer nof;
    public Integer now;

    @Override
    public String toString(){
        return  "NAME : " + this.name + "\n" +
                "X    : " + this.x + "\n" +
                "Y    : " + this.y + "\n" +
                "NOP  : " + this.nop + "\n" +
                "NOF  : " + this.nof + "\n" +
                "NOW  : " + this.now + "\n";

    }
}
