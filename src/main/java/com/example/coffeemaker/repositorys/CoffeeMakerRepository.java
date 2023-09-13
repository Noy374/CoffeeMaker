package com.example.coffeebrew.repositorys;


import com.example.coffeebrew.entity.CoffeeMaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeMakerRepository extends JpaRepository<CoffeeMaker,Long> {
}
