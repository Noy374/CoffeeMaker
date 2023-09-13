package com.example.coffeebrew.payload.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DeleteCoffeeMakerRequest {
    private Long id;

    @JsonCreator
    public DeleteCoffeeMakerRequest(@JsonProperty("id") Long id) {
        this.id=id;
    }

}
