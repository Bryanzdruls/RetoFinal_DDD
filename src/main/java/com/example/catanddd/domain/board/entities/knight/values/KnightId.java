package com.example.catanddd.domain.board.entities.knight.values;

import com.example.catanddd.domain.board.entities.turn.values.TurnId;
import com.example.catanddd.domain.generic.Identity;

public class KnightId extends Identity {
    public KnightId() {
        super();
    }

    private KnightId(String uuid){
        super(uuid);
    }

    public static KnightId of(String uuid){
        return new KnightId(uuid);
    }
}
