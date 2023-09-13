package com.example.coffeemaker.repositorys;

import com.example.coffeemaker.entity.CoffeeHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoffeeHouseRepository extends JpaRepository<CoffeeHouse,Long> {

    Optional<CoffeeHouse> findByUsername(String username);
}
