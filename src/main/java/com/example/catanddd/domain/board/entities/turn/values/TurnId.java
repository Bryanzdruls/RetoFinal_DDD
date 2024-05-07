package com.example.catanddd.domain.board.entities.turn.values;

import com.example.catanddd.domain.generic.Identity;

public class TurnId extends Identity {
    private TurnId() {
        super();
    }

    private TurnId(String uuid){
        super(uuid);
    }

    public static TurnId of(String uuid){
        return new TurnId(uuid);
    }
}
