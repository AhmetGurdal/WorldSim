package com.example.worldSim.entity;


import com.example.worldSim.controller.world.request.AddResourceWorldRequest;
import com.example.worldSim.factory.Tile;
import com.example.worldSim.factoryImpl.StateImpl;
import com.example.worldSim.service.HumanService;
import com.example.worldSim.type.TileType;
import com.example.worldSim.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

public class World implements StateImpl {

    private String name;
    private int sizeX;
    private int sizeY;
    private boolean isWorldValid;
    private int nop;
    private int nof;
    private int now;
    private Tile[] tiles;

    private boolean isCreaturesAlive;

    private int age;
    private static int worldCount = 0;


    private ArrayList<WorldState> states;

    public World(String _name, int _sizeX, int _sizeY) {
        this.nop = 0;
        this.nof = 0;
        this.now = 0;
        this.age = 0;
        this.name = _name;
        this.sizeX = _sizeX;
        this.sizeY = _sizeY;
        this.tiles = new Tile[_sizeY * _sizeX];
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                this.tiles[x * sizeY + y] = new Ground(x, y);
            }
        }
        this.isWorldValid = true;
        this.states = new ArrayList<>();
        this.states.add(new WorldState(this));
        this.isCreaturesAlive = false;
    }

    public World(String name, int sizeX, int sizeY, int nop, int nof, int now) {
        this.nop = nop;
        this.nof = nof;
        this.now = now;
        this.name = name;
        this.age = 0;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.isCreaturesAlive = nop > 0;
        this.tiles = new Tile[sizeX * sizeY];

        // add Random People
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < nop; i++) {
            int randomPos = RandomUtil.uniqueRandomInt(0, sizeX * sizeY, positions);
            int posX = randomPos / sizeY;
            int posY = randomPos % sizeY;
            this.tiles[randomPos] = new Human(HumanService.humans.size(), posX, posY, "Test", 100, 100);
            positions.add(randomPos);
        }
        // add Random Food
        for (int i = 0; i < nof; i++) {
            int randomPos = RandomUtil.uniqueRandomInt(0, sizeX * sizeY, positions);
            int posX = randomPos / sizeY;
            int posY = randomPos % sizeY;
            this.tiles[randomPos] = new Food(posX, posY, 100);
            positions.add(randomPos);
        }
        // add Random Water
        for (int i = 0; i < now; i++) {
            int randomPos = RandomUtil.uniqueRandomInt(0, sizeX * sizeY, positions);
            int posX = randomPos / sizeY;
            int posY = randomPos % sizeY;
            this.tiles[randomPos] = new Water(posX, posY, 100);
            positions.add(randomPos);
        }

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                if (this.tiles[x * sizeY + y] == null) {
                    this.tiles[x * sizeY + y] = new Ground(x, y);
                }
            }
        }
        this.isWorldValid = true;
        this.states = new ArrayList<>();
        this.states.add(new WorldState(this));
    }

    public boolean getIsWorldValid() {
        return this.isWorldValid;
    }

    public static int getWorldCount() {
        return worldCount;
    }

    public static void setWorldCount(int _worldCount) {
        worldCount = _worldCount;
    }

    public String getName() {
        return this.name;
    }

    public int getNOP() {
        return this.nop;
    }

    public int getNOF() {
        return this.nof;
    }

    public int getNOW() {
        return this.now;
    }

    public int getSizeX() {
        return this.sizeX;
    }

    public int getSizeY() {
        return this.sizeY;
    }

    public Tile[] getTiles() {
        return this.tiles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNop(int nop) {
        this.nop = nop;
    }

    public void setNof(int nof) {
        this.nof = nof;
    }

    public void setNow(int now) {
        this.now = now;
    }

    public boolean getIsCreaturesAlive(){
        return this.isCreaturesAlive;
    }
    public ArrayList<WorldState> getStates(){
        return this.states;
    }


    public void setTile(Integer x, Integer y, TileType type) {
        if (x < this.sizeX && y < this.sizeY) {
            switch (type) {
                case WATER:
                    this.tiles[x * this.sizeY + y] = new Water(x, y, 100);
                    break;
                case FOOD:
                    this.tiles[x * this.sizeY + y] = new Food(x, y, 100);
                    break;
                default:
                    this.tiles[x * this.sizeY + y] = new Ground(x, y);
                    break;
            }

        }
    }

    public String toString() {
        String result = "";
        for (int x = 0; x < this.sizeX; x++) {
            String line = "";
            for (int y = 0; y < this.sizeY; y++) {
                line += this.tiles[x * this.sizeY + y];
            }
            result += (line + "\n");
        }
        return result;
    }

    public void changeTiles(Tile source, Tile target) {
        int sourcePosX = source.posX;
        int sourcePosY = source.posY;
        int targetPosX = target.posX;
        int targetPosY = target.posY;
        source.posX = targetPosX;
        source.posY = targetPosY;
        this.tiles[targetPosX * sizeY + targetPosY] = source;
        this.tiles[sourcePosX * sizeY + sourcePosY] = new Ground(sourcePosX, sourcePosY);
    }

    public void addResource(AddResourceWorldRequest aRWR) {
        if (aRWR.x < sizeX && aRWR.y < sizeY) {
            switch (aRWR.type) {
                case WATER:
                    if (tiles[aRWR.x * this.sizeY + aRWR.y].isGround) {
                        tiles[aRWR.x * this.sizeY + aRWR.y] = new Water(aRWR.x, aRWR.y, aRWR.amount);
                    }
                    break;
                case FOOD:
                    if (tiles[aRWR.x * this.sizeY + aRWR.y].isGround) {
                        tiles[aRWR.x * this.sizeY + aRWR.y] = new Food(aRWR.x, aRWR.y, aRWR.amount);
                    }
                    break;
            }
        }
    }

    @Override
    public WorldState getPrevState() {
        this.states.remove(this.states.size() - 1);
        return this.getCurrentState();
    }

    @Override
    public WorldState getCurrentState() {
        return this.states.get(this.states.size() - 1);
    }

    @Override
    public WorldState getNextState() {
        this.age += 1;
        if(isCreaturesAlive){
            if (isWorldValid && this.nop > 0) {
                isCreaturesAlive = false;
                for (int x = 0; x < sizeX; x++) {
                    for (int y = 0; y < sizeY; y++) {
                        if (tiles[x * this.sizeY + y].getType() == TileType.HUMAN) {
                            Human human = (Human) tiles[x * this.sizeY + y];
                            isCreaturesAlive = isCreaturesAlive || human.getIsAlive();
                            if(human.getIsAlive() && this.age > human.getAge()){
                                human.move(this);
                            }
                        }
                    }
                }
            }
            WorldState currentState = new WorldState(this);
            this.states.add(currentState);
            return currentState;
        }
        else{
            return this.states.get(this.states.size() - 1);
        }
    }
}
