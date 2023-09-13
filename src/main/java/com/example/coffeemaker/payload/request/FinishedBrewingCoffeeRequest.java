package com.example.coffeebrew.payload.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FinishedBrewingCoffeeRequest {

    Long id;
    @JsonCreator
    public FinishedBrewingCoffeeRequest(@JsonProperty("id") Long id) {
        this.id=id;
    }
}
