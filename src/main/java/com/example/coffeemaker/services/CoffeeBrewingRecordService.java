package com.example.coffeemaker.services;


import com.example.coffeemaker.entity.CoffeeBrewingRecord;
import com.example.coffeemaker.entity.CoffeeMaker;
import com.example.coffeemaker.repositorys.CoffeeBrewingRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoffeeBrewingRecordService {

    private final CoffeeBrewingRecordRepository coffeeBrewingRecordRepository;

    public CoffeeBrewingRecordService(CoffeeBrewingRecordRepository coffeeBrewingRecordRepository) {
        this.coffeeBrewingRecordRepository = coffeeBrewingRecordRepository;
    }

    public Long creatCoffeeBrewingRecord(CoffeeMaker coffeeMaker) {
        CoffeeBrewingRecord coffeeBrewingRecord=new CoffeeBrewingRecord();
        coffeeBrewingRecord.setCoffeeMaker(coffeeMaker);
        coffeeBrewingRecord.setStartTime(LocalDateTime.now());
        coffeeBrewingRecordRepository.save(coffeeBrewingRecord);
        return coffeeBrewingRecord.getId();
    }

    public boolean updateEndTime(Long id, LocalDateTime now) {
        if(coffeeBrewingRecordRepository.existsById(id)) {
            coffeeBrewingRecordRepository.updateEndTime(id, now);
            return true;
        }
        return false;
    }

    public List<CoffeeBrewingRecord> getAllById(Long id) {
        return coffeeBrewingRecordRepository
                .findAll()
                .stream()
                .filter(record -> record.getCoffeeMaker().getId().equals(id))
                .collect(Collectors.toList());
    }
}
