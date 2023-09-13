package com.example.coffeebrew.entity;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "coffee_machine_id",
            referencedColumnName = "id"
    )
    private CoffeeMaker coffeeMaker;
}