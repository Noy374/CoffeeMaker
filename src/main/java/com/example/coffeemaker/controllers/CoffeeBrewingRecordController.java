package com.example.coffeemaker.controllers;


import com.example.coffeemaker.entity.CoffeeMaker;
import com.example.coffeemaker.payload.request.FinishedBrewingCoffeeRequest;
import com.example.coffeemaker.payload.request.StartedBrewingCoffeeRequest;
import com.example.coffeemaker.payload.response.MessageResponse;
import com.example.coffeemaker.payload.response.StartedBrewingCoffeeResponse;
import com.example.coffeemaker.security.JwtTokenUtils;
import com.example.coffeemaker.services.CoffeeBrewingRecordService;
import com.example.coffeemaker.services.CoffeeHouseService;
import com.example.coffeemaker.services.CoffeeMakerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/coffee_brewing")
@AllArgsConstructor
public class CoffeeBrewingRecordController {

    private final CoffeeMakerService coffeeMakerService;
    private final CoffeeBrewingRecordService coffeeBrewingRecordService;
    private final JwtTokenUtils jwtTokenUtils;
    private final CoffeeHouseService coffeeHouseService;

    @PostMapping("/started")
    ResponseEntity<Object>StartedBrewingCoffee(@RequestBody StartedBrewingCoffeeRequest startedBrewingCoffeeRequest,
                                               @RequestHeader(value = "Authorization") String jwtToken){

        CoffeeMaker coffeeMaker = coffeeMakerService.getCoffeeMakerById(startedBrewingCoffeeRequest.getId());
        if( coffeeMaker ==null)
                 ResponseEntity.badRequest().body(new MessageResponse("There is no coffee maker with ID "
                        + startedBrewingCoffeeRequest.getId()));

        if(!coffeeHouseService.checkCoffeeMaker(jwtTokenUtils.getUsername(jwtToken.substring(7))
                ,coffeeMaker))
            return ResponseEntity.ok(new MessageResponse("You do not have access to this coffee maker"));

        return ResponseEntity.ok().
                body(new StartedBrewingCoffeeResponse
                        (coffeeBrewingRecordService.creatCoffeeBrewingRecord(coffeeMaker)));
    }

    @PostMapping("/finished")
    ResponseEntity<Object>FinishedBrewingCoffee(@RequestBody FinishedBrewingCoffeeRequest finishedBrewingCoffeeRequest){
        if(coffeeBrewingRecordService.updateEndTime(finishedBrewingCoffeeRequest.getId(),LocalDateTime.now()))
           return ResponseEntity.ok(new MessageResponse("Information updated successfully"));
        return ResponseEntity.badRequest().body(new MessageResponse("There is no coffee brewing record with ID:"
                +finishedBrewingCoffeeRequest.getId()));
       }

}
