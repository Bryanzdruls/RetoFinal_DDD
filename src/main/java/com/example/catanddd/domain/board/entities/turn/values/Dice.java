package com.example.catanddd.domain.board.entities.turn.values;

import com.example.catanddd.domain.generic.ValueObject;

public class Dice implements ValueObject<Integer> {
    private final Integer quantity;

    private Dice(Integer quantity){
        final int MINIMUM_DICE_POSSIBLE = 2;
        final int MAXIMUM_DICE_POSSIBLE = 12;
        if (quantity != null && quantity >= MINIMUM_DICE_POSSIBLE && quantity <= MAXIMUM_DICE_POSSIBLE) {
            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException("Quantity has to be between 2 and 12");
        }
    }
    public static Dice of(Integer quantity){
        return new Dice(quantity);
    }
    @Override
    public Integer value() {
        return quantity;
    }
}
