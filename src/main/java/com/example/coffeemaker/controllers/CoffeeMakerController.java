package com.example.coffeebrew.controllers;


import com.example.coffeebrew.entity.CoffeeBrewingRecord;
import com.example.coffeebrew.payload.request.AddCoffeeMakerRequest;
import com.example.coffeebrew.payload.request.DeleteCoffeeMakerRequest;
import com.example.coffeebrew.payload.response.MessageResponse;
import com.example.coffeebrew.payload.response.AddCoffeeMakerResponse;
import com.example.coffeebrew.services.CoffeeBrewingRecordService;
import com.example.coffeebrew.services.CoffeeMakerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffee_maker")
@AllArgsConstructor
public class CoffeeMakerController {

    private final CoffeeMakerService coffeeMakerService;
    private final CoffeeBrewingRecordService coffeeBrewingRecordService;
    @PostMapping("/add")
    ResponseEntity<Object> addCoffeeMaker(@RequestBody AddCoffeeMakerRequest request){

      return ResponseEntity.ok(new AddCoffeeMakerResponse(coffeeMakerService.addAndGetId(request)));
    }


    @DeleteMapping("/delete")
    ResponseEntity<Object> deleteCoffeeMaker(@RequestBody DeleteCoffeeMakerRequest request){
        return coffeeMakerService.deleteCoffeeMachine(request.getId()) ?
                 ResponseEntity.ok(new MessageResponse("Coffee maker successfully removed"))
                :ResponseEntity.badRequest().body(new MessageResponse("There is no coffee maker with ID "+ request.getId()));
    }

    @GetMapping("/records/{id}")
    public ResponseEntity<Object> getDataById(@PathVariable Long id) {
        List<CoffeeBrewingRecord> records=coffeeBrewingRecordService.getAllById(id);
        return ResponseEntity.ok().body(records);
    }

}
