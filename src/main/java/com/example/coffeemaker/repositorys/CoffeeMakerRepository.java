package com.example.coffeemaker.repositorys;


import com.example.coffeemaker.entity.CoffeeMaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeMakerRepository extends JpaRepository<CoffeeMaker,Long> {
}
