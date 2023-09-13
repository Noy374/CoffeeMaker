package com.example.coffeemaker.services;


import com.example.coffeemaker.entity.CoffeeMaker;
import com.example.coffeemaker.payload.request.AddCoffeeMakerRequest;
import com.example.coffeemaker.repositorys.CoffeeMakerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CoffeeMakerService {

    private final CoffeeMakerRepository coffeeMakerRepository;

    private final CoffeeHouseService coffeeHouseService;

    private CoffeeMaker fromCMRToCM(AddCoffeeMakerRequest request){
        CoffeeMaker coffeeMaker =new CoffeeMaker();
        coffeeMaker.setName(request.getName());
        return coffeeMaker;
    }

    public Long addAndGetId(AddCoffeeMakerRequest request,String coffeeHouseUsername) {
        CoffeeMaker coffeeMaker =fromCMRToCM(request);
        coffeeMaker.setCoffeeHouse(coffeeHouseService.getUserByUsername(coffeeHouseUsername));
        coffeeMakerRepository.save(coffeeMaker);
        return coffeeMaker.getId();
    }

    public boolean deleteCoffeeMachine(Long id) {
       if(coffeeMakerRepository.findById(id).isPresent()) {
           coffeeMakerRepository.deleteById(id);
           return  true;
       }
       return false;
    }

    public CoffeeMaker getCoffeeMakerById(Long id) {
        Optional<CoffeeMaker> coffeeMaker = coffeeMakerRepository.findById(id);
        return coffeeMaker.orElse(null);
    }
}
