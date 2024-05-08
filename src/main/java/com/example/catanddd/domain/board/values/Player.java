package com.example.catanddd.domain.board.values;

import com.example.catanddd.domain.generic.ValueObject;

public class Player implements ValueObject<String> {
    private final String playerId;

    private Player(String playerId){
        if (playerId != null) {
            this.playerId = playerId;
        } else {
            throw new IllegalArgumentException("PlayerId has to be between 2 and 12");
        }
    }
    public static Player of(String playerId){
        return new Player(playerId);
    }
    @Override
    public String value() {
        return playerId;
    }
}