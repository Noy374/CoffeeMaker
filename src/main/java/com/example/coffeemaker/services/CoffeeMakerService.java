package com.example.coffeebrew.services;


import com.example.coffeebrew.entity.CoffeeMaker;
import com.example.coffeebrew.payload.request.AddCoffeeMakerRequest;
import com.example.coffeebrew.repositorys.CoffeeMakerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoffeeMakerService {

    private final CoffeeMakerRepository coffeeMakerRepository;

    public CoffeeMakerService(CoffeeMakerRepository coffeeMakerRepository) {
        this.coffeeMakerRepository = coffeeMakerRepository;
    }

    private CoffeeMaker fromCMRToCM(AddCoffeeMakerRequest request){
        CoffeeMaker coffeeMaker =new CoffeeMaker();
        coffeeMaker.setName(request.getName());
        return coffeeMaker;
    }

    public Long addAndGetId(AddCoffeeMakerRequest request) {
        CoffeeMaker coffeeMaker =fromCMRToCM(request);
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
