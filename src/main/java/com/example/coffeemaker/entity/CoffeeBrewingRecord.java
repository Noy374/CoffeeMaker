package com.example.coffeemaker.entity;


import javax.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class CoffeeBrewingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


    @ManyToOne
    @JoinColumn(
            name = "coffee_maker_id",
            referencedColumnName = "id"
    )
    private CoffeeMaker coffeeMaker;
}