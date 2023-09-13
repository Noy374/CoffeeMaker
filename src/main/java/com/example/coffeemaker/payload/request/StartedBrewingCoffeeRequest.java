package com.example.coffeemaker.payload.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StartedBrewingCoffeeRequest {
    private Long id;


    @JsonCreator
    public StartedBrewingCoffeeRequest(@JsonProperty("id") Long id) {
        this.id=id;
    }
}
