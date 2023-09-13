package com.example.coffeemaker.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class CoffeeHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String CoffeeHouseName;

    @Column(unique = true,nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "coffeeHouse")
    private List<CoffeeMaker> coffeeMakerList;

}
