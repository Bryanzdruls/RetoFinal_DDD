package com.example.catanddd.domain.board.entities.corner.values;

import com.example.catanddd.domain.board.entities.terrain.values.TerrainNumber;
import com.example.catanddd.domain.generic.ValueObject;

public class IsCornerAvailable implements ValueObject<Boolean> {
    private final Boolean isCornerAvailable;

    private IsCornerAvailable(Boolean isCornerAvailable){
        if (isCornerAvailable != null ) {
            this.isCornerAvailable = isCornerAvailable;
        } else {
            throw new IllegalArgumentException("Is corner available has to be or true or false");
        }
    }
    public static IsCornerAvailable of(Boolean isCornerAvailable){
        return new IsCornerAvailable(isCornerAvailable);
    }
    @Override
    public Boolean value() {
        return isCornerAvailable;
    }
}