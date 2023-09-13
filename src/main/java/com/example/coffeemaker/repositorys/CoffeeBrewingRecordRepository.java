package com.example.coffeemaker.repositorys;

import com.example.coffeemaker.entity.CoffeeBrewingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface CoffeeBrewingRecordRepository extends JpaRepository<CoffeeBrewingRecord,Long> {

    @Modifying
    @Query("UPDATE CoffeeBrewingRecord u SET u.endTime = :now WHERE u.id= :id")
    @Transactional
    void updateEndTime(@Param("id") Long id, @Param("now") LocalDateTime now);


}
