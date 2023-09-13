package com.example.coffeebrew.controllers;

import com.example.coffeebrew.entity.CoffeeBrewingRecord;
import com.example.coffeebrew.entity.CoffeeMaker;
import com.example.coffeebrew.payload.request.FinishedBrewingCoffeeRequest;
import com.example.coffeebrew.payload.request.StartedBrewingCoffeeRequest;
import com.example.coffeebrew.payload.response.MessageResponse;
import com.example.coffeebrew.payload.response.StartedBrewingCoffeeResponse;
import com.example.coffeebrew.services.CoffeeBrewingRecordService;
import com.example.coffeebrew.services.CoffeeMakerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/coffee_brewing")
@AllArgsConstructor
public class CoffeeBrewingRecordController {

    private final CoffeeMakerService coffeeMakerService;
    private final CoffeeBrewingRecordService coffeeBrewingRecordService;

    @PostMapping("/started")
    ResponseEntity<Object>StartedBrewingCoffee(@RequestBody StartedBrewingCoffeeRequest startedBrewingCoffeeRequest){
        CoffeeMaker coffeeMaker = coffeeMakerService.getCoffeeMakerById(startedBrewingCoffeeRequest.getId());
        if( coffeeMaker ==null)
                 ResponseEntity.badRequest().body(new MessageResponse("There is no coffee maker with ID "
                        + startedBrewingCoffeeRequest.getId()));


        CoffeeBrewingRecord coffeeBrewingRecord =new CoffeeBrewingRecord();
        coffeeBrewingRecord.setStartTime(LocalDateTime.now());
        coffeeBrewingRecord.setCoffeeMaker(coffeeMaker);
        coffeeBrewingRecordService.creatCoffeeBrewingRecord(coffeeBrewingRecord);
        return ResponseEntity.ok().body(new StartedBrewingCoffeeResponse(coffeeBrewingRecord.getId()));
    }

    @PostMapping("/finished")
    ResponseEntity<Object>FinishedBrewingCoffee(@RequestBody FinishedBrewingCoffeeRequest finishedBrewingCoffeeRequest){
        if(coffeeBrewingRecordService.updateEndTime(finishedBrewingCoffeeRequest.getId(),LocalDateTime.now()))
           return ResponseEntity.ok(new MessageResponse("Information updated successfully"));
        return ResponseEntity.badRequest().body(new MessageResponse("There is no coffee brewing record with ID:"
                +finishedBrewingCoffeeRequest.getId()));
       }

}
