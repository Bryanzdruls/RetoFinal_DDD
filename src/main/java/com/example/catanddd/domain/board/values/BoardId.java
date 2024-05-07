package com.example.catanddd.domain.board.values;

import com.example.catanddd.domain.generic.Identity;
import com.example.catanddd.domain.player.values.PlayerId;

public class BoardId extends Identity {
    private BoardId() {
        super();
    }

    private BoardId(String uuid){
        super(uuid);
    }

    public static BoardId of(String uuid){
        return new BoardId(uuid);
    }
}
