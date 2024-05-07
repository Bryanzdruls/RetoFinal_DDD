package com.example.catanddd.domain.player.values;

import com.example.catanddd.domain.generic.Identity;

public class PlayerId extends Identity {
    private PlayerId() {
        super();
    }

    private PlayerId(String uuid){
        super(uuid);
    }

    public static PlayerId of(String uuid){
        return new PlayerId(uuid);
    }
}
