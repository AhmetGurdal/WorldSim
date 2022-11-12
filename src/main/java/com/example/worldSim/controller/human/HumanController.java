package com.example.worldSim.controller.human;

import com.example.worldSim.controller.human.response.HumanResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("human")
public class HumanController {

    @GetMapping("human/wid/{id}")
    HumanResponse getHuman(@PathVariable int id){
     return new HumanResponse();
    }
}
