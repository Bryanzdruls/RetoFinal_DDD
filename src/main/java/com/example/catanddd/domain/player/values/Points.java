package com.example.catanddd.domain.player.values;

import com.example.catanddd.domain.generic.ValueObject;

public class Points implements ValueObject<Integer> {
    private final Integer quantity;

    private Points(Integer quantity){
        if (quantity != null && quantity >= 0) {
            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException("Points Cannot be null");
        }
    }
    public static Points of( Integer quantity){
        return new Points(quantity);
    }
    @Override
    public Integer value() {
        return quantity;
    }
}
