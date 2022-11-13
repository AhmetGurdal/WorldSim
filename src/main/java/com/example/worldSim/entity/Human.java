package com.example.worldSim.entity;

import com.example.worldSim.factory.Creature;
import com.example.worldSim.factory.Resource;
import com.example.worldSim.factory.Tile;
import com.example.worldSim.service.HumanService;
import com.example.worldSim.type.DirectionType;
import com.example.worldSim.type.SearchType;
import com.example.worldSim.type.TileType;
import com.example.worldSim.util.RandomUtil;
import com.example.worldSim.util.TileUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Human extends Creature {

    private String name;

    public Human(int id, int posX, int posY, String name, int satietyLevel, int hydrationLevel){
        super(id ,posX, posY, TileType.HUMAN, true,satietyLevel,hydrationLevel);
        this.name = name;

        HumanService.humans.put(id,this);
    }

    private Tile checkDirectionTile(DirectionType directionType, World world){
        switch (directionType){
            case LEFT_UP:
                if(this.posX >= 1  && this.posY >= 1){
                    return world.getTiles()[(this.posX-1) * world.getSizeY() + (this.posY-1)];
                }
                return null;
            case UP:
                if(this.posX >= 1){
                    return world.getTiles()[(this.posX - 1) * world.getSizeY() + this.posY];
                }
                return null;
            case RIGHT_UP:
                if(this.posX >= 1 && this.posY < world.getSizeY() -1){
                    return world.getTiles()[(this.posX-1) * world.getSizeY() + (this.posY+1)];
                }
                return null;
            case LEFT:
                if(this.posY >= 1){
                    return world.getTiles()[(this.posX) * world.getSizeY() + (this.posY - 1)];
                }
                return null;
            case RIGHT:
                if(this.posY < world.getSizeY()-1){
                    return world.getTiles()[(this.posX) * world.getSizeY() + (this.posY +  1)];
                }
                return null;
            case LEFT_DOWN:
                if(this.posX < world.getSizeX() - 1 && this.posY >= 1){
                    return world.getTiles()[(this.posX+1) * world.getSizeY() + (this.posY-1)];
                }
                return null;
            case DOWN:
                if(this.posX < world.getSizeX() -1){
                    return world.getTiles()[(this.posX + 1) * world.getSizeY() + this.posY];
                }
                return null;
            case RIGHT_DOWN:
                if(this.posX < world.getSizeX() -1 && this.posY < world.getSizeY()-1){
                    return world.getTiles()[(this.posX+1) * world.getSizeY() + (this.posY+1)];
                }
                return null;
            default:
                return null;
        }
    }

    private List<DirectionType> availableDirections(World world){
        List<DirectionType> directions = new ArrayList(Arrays.asList(DirectionType.values()));
        if(this.posY == world.getSizeY() - 1){
            TileUtils.removeFromList(directions,DirectionType.RIGHT_UP);
            TileUtils.removeFromList(directions,DirectionType.RIGHT);
            TileUtils.removeFromList(directions,DirectionType.RIGHT_DOWN);
        }
        else if (this.posY == 0){
            TileUtils.removeFromList(directions,DirectionType.LEFT_UP);
            TileUtils.removeFromList(directions,DirectionType.LEFT);
            TileUtils.removeFromList(directions,DirectionType.LEFT_DOWN);
        }

        if(this.posX == world.getSizeX() - 1){
            TileUtils.removeFromList(directions,DirectionType.LEFT_DOWN);
            TileUtils.removeFromList(directions,DirectionType.DOWN);
            TileUtils.removeFromList(directions,DirectionType.RIGHT_DOWN);
        }
        else if (this.posX == 0){
            TileUtils.removeFromList(directions,DirectionType.LEFT_UP);
            TileUtils.removeFromList(directions,DirectionType.UP);
            TileUtils.removeFromList(directions,DirectionType.RIGHT_UP);
        }
        return directions;
    }

    private DirectionType selectDirection(World world){
        SearchType searchType;
        if(this.getHydrationLevel() <= this.getSatietyLevel() && this.getHydrationLevel() <= 40){
            searchType = SearchType.WATER;
        }
        else if(this.getSatietyLevel() <= this.getHydrationLevel() && this.getSatietyLevel() <= 40){
            searchType = SearchType.FOOD;
        }
        else{
            searchType = SearchType.GROUND;
            // Search for other human;
            //searchType = SearchType.HUMAN;
        }
        List<DirectionType> directions = availableDirections(world);
        for(DirectionType directionType : directions){
            Tile tile = checkDirectionTile(directionType,world);
            switch (searchType){
                case WATER:
                    if(tile.getType() == TileType.WATER){
                        return directionType;
                    }
                    break;
                case FOOD:
                    if(tile.getType() == TileType.FOOD){
                        return directionType;
                    }
                    break;

                /*case HUMAN:
                    if(tile.getType() == TileType.HUMAN){
                        return directionType;
                    }
                    break;
                 */
            }
        }

        return directions.get(RandomUtil.randomIntBetween(0,directions.size()));
    }

    @Override
    public void move(World world){
        if(this.getIsAlive() && (this.getHydrationLevel() == 0 || this.getSatietyLevel() == 0)){
            this.die(world);
        }
        else{
            this.increaseAge();
            this.decreaseSatietyLevelBy(10);
            this.decreaseHydrationLevelBy(10);
            DirectionType directionType = selectDirection(world);
            Tile targetTile = checkDirectionTile(directionType, world);


            if(targetTile != null){
                if(targetTile.isHuman){
                    return;
                }
                else if(targetTile.getClass().getSuperclass() == Resource.class){
                    this.consume(targetTile);
                }
                targetTile = new Ground(targetTile.posX, targetTile.posY);
                world.changeTiles(this,targetTile);
                System.out.println("HumanID : " + this.getId() + " - SL : " + this.getSatietyLevel() + " - HL : " + this.getHydrationLevel() );
                System.out.println("Source : " + this.posX + " - " + this.posY);
                System.out.println("Target : " + targetTile.posX + " - " + targetTile.posY);
                System.out.println("TargetClass : "+ targetTile.getClass());
                System.out.println("----------");
            }
        }

        //return new WorldState(world);
    };

    @Override
    public void drink(Water water){
        this.increaseHydrationLevelBy(water.getAmount());
    };

    @Override
    public void eat(Food food){
        this.increaseSatietyLevelBy(food.getAmount());
    };

}
