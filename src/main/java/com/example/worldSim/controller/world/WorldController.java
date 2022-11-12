package com.example.worldSim.controller.world;

import com.example.worldSim.controller.world.request.AddResourceWorldRequest;
import com.example.worldSim.controller.world.request.CreateWorldRequest;
import com.example.worldSim.controller.world.request.UpdateWorldRequest;
import com.example.worldSim.controller.world.response.WorldResponse;
import com.example.worldSim.service.WorldService;
import com.example.worldSim.util.RandomUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("world")
public class WorldController {

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/{id}")
    WorldResponse getWorld(@PathVariable int id) {
        if(WorldService.worlds.containsKey(id)){
            return new WorldResponse(id, WorldService.worlds.get(id));
        }
        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "ID Not Found");
    }
    // end::get-aggregate-root[]

    @GetMapping()
    List<WorldResponse> getWorlds(){
        ArrayList<WorldResponse> response = new ArrayList<WorldResponse>();
        WorldService.worlds.forEach((k,v) -> response.add(new WorldResponse(k,v)));
        return response;


    }

    @GetMapping("/random/{x}/{y}")
    int getRandom(@PathVariable int x, @PathVariable int y){
        return RandomUtil.randomIntBetween(x,y);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    WorldResponse createWorld(@RequestBody CreateWorldRequest createWorldRequest){
        if(createWorldRequest.getName() != null && createWorldRequest.getX() != null && createWorldRequest.getY() != null){
            return WorldService.createWorld(createWorldRequest);}
        throw new IllegalArgumentException();
    }

    @PatchMapping("/{id}")
    WorldResponse updateWorld(@PathVariable int id, @RequestBody UpdateWorldRequest updateWorldRequest){
        return WorldService.updateWorld(id, updateWorldRequest);
    }

    @PostMapping("/addResource/{id}")
    WorldResponse addResource(@PathVariable int id, @RequestBody AddResourceWorldRequest addResourceWorldRequest){
        return WorldService.addResource(id,addResourceWorldRequest);
    }


}
