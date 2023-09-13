package com.example.coffeebrew.payload.request;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddCoffeeMakerRequest {

    private String name;

    @JsonCreator
    public AddCoffeeMakerRequest(@JsonProperty("name") String name) {
        this.name=name;
    }
}
