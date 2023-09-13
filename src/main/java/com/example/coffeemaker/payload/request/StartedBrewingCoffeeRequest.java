package com.example.coffeebrew.payload.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StartedBrewingCoffeeRequest {
    private Long id;


    @JsonCreator
    public StartedBrewingCoffeeRequest(@JsonProperty("id") Long id) {
        this.id=id;
    }
}
