package com.example.coffeemaker.services;


import com.example.coffeemaker.entity.CoffeeHouse;
import com.example.coffeemaker.entity.CoffeeMaker;
import com.example.coffeemaker.payload.request.RegistrationRequest;
import com.example.coffeemaker.repositorys.CoffeeHouseRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoffeeHouseService implements UserDetailsService {

    private final CoffeeHouseRepository coffeeHouseRepository;

    public CoffeeHouseService(CoffeeHouseRepository coffeeHouseRepository) {
        this.coffeeHouseRepository = coffeeHouseRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CoffeeHouse user = coffeeHouseRepository.findByUsername(username).orElseThrow((()->new UsernameNotFoundException("Coffee House not found") ));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>()
        );
    }

    public void saveCoffeeHouse(RegistrationRequest registrationRequest) {
        CoffeeHouse coffeeHouse=new CoffeeHouse();
        coffeeHouse.setUsername(registrationRequest.getUsername());
        coffeeHouse.setPassword(new BCryptPasswordEncoder().encode(registrationRequest.getPassword()));
        coffeeHouse.setCoffeeHouseName(registrationRequest.getCoffeeHouseName());
        coffeeHouseRepository.save(coffeeHouse);
    }

        public boolean checkCoffeeHouseByUsername(String username) {
       return coffeeHouseRepository.findByUsername(username).isEmpty();
    }

    public CoffeeHouse getUserByUsername(String coffeeHouseUsername) {
        return coffeeHouseRepository.findByUsername(coffeeHouseUsername).orElse(null);
    }

    public boolean checkCoffeeMaker(String username, CoffeeMaker coffeeMaker) {
        List<CoffeeMaker> coffeeMakerList = coffeeHouseRepository.findByUsername(username).get().getCoffeeMakerList();
        if (coffeeMakerList == null) {
            return false;
        }
        return coffeeMakerList.contains(coffeeMaker);
    }

}
