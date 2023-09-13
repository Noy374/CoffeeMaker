package com.example.coffeebrew.services;


import com.example.coffeebrew.entity.CoffeeBrewingRecord;
import com.example.coffeebrew.repositorys.CoffeeBrewingRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CoffeeBrewingRecordService {

    private final CoffeeBrewingRecordRepository coffeeBrewingRecordRepository;

    public CoffeeBrewingRecordService(CoffeeBrewingRecordRepository coffeeBrewingRecordRepository) {
        this.coffeeBrewingRecordRepository = coffeeBrewingRecordRepository;
    }

    public void creatCoffeeBrewingRecord(CoffeeBrewingRecord coffeeBrewingRecord) {
        coffeeBrewingRecordRepository.save(coffeeBrewingRecord);
    }

    public boolean updateEndTime(Long id, LocalDateTime now) {
        if(coffeeBrewingRecordRepository.existsById(id)) {
            coffeeBrewingRecordRepository.updateEndTime(id, now);
            return true;
        }
        return false;
    }

    public List<CoffeeBrewingRecord> getAllById(Long id) {
       return coffeeBrewingRecordRepository.findAll();
    }
}
