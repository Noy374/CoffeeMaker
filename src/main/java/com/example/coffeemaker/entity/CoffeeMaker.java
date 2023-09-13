package com.example.coffeemaker.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class CoffeeMaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "coffeeMaker")
    private List<CoffeeBrewingRecord> coffeeBrewingRecord;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "coffee_house_id",
            referencedColumnName = "id"
    )
    private CoffeeHouse coffeeHouse;
}