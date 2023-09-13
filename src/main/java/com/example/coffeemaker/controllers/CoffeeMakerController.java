package com.example.coffeemaker.controllers;


import com.example.coffeemaker.entity.CoffeeBrewingRecord;
import com.example.coffeemaker.payload.request.AddCoffeeMakerRequest;
import com.example.coffeemaker.payload.request.DeleteCoffeeMakerRequest;
import com.example.coffeemaker.payload.response.MessageResponse;
import com.example.coffeemaker.payload.response.AddCoffeeMakerResponse;
import com.example.coffeemaker.security.JwtTokenUtils;
import com.example.coffeemaker.services.CoffeeBrewingRecordService;
import com.example.coffeemaker.services.CoffeeHouseService;
import com.example.coffeemaker.services.CoffeeMakerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffee_maker")
@AllArgsConstructor
public class CoffeeMakerController {

    private final CoffeeMakerService coffeeMakerService;
    private final CoffeeHouseService coffeeHouseService;
    private final JwtTokenUtils jwtTokenUtils;
    private final CoffeeBrewingRecordService coffeeBrewingRecordService;
    @PostMapping("/add")
    ResponseEntity<Object> addCoffeeMaker(@RequestBody AddCoffeeMakerRequest request,@RequestHeader(value = "Authorization") String jwtToken){

      return ResponseEntity
              .ok(new AddCoffeeMakerResponse(
                      coffeeMakerService
                              .addAndGetId(request,jwtTokenUtils.getUsername(jwtToken.substring(7)))));
    }


    @DeleteMapping("/delete")
    ResponseEntity<Object> deleteCoffeeMaker(@RequestBody DeleteCoffeeMakerRequest request,
                                             @RequestHeader(value = "Authorization") String jwtToken){
        if(!coffeeHouseService.checkCoffeeMaker(jwtTokenUtils.getUsername(jwtToken.substring(7))
                ,coffeeMakerService.getCoffeeMakerById(request.getId())))
            return ResponseEntity.ok(new MessageResponse("You do not have access to this coffee maker"));
        return coffeeMakerService.deleteCoffeeMachine(request.getId()) ?
                 ResponseEntity.ok(new MessageResponse("Coffee maker successfully removed"))
                :ResponseEntity.badRequest().body(new MessageResponse("There is no coffee maker with ID "+ request.getId()));
    }

    @GetMapping("/records/{id}")
    public ResponseEntity<Object> getDataById(@PathVariable Long id,
            @RequestHeader(value = "Authorization") String jwtToken){
        if(!coffeeHouseService.checkCoffeeMaker(jwtTokenUtils.getUsername(jwtToken.substring(7))
                ,coffeeMakerService.getCoffeeMakerById(id)))
            return ResponseEntity.ok(new MessageResponse("You do not have access to this coffee maker"));
        List<CoffeeBrewingRecord> records=coffeeBrewingRecordService.getAllById(id);
        return ResponseEntity.ok().body(records);
    }

}
