package com.example.catanddd.domain.player.values;

import com.example.catanddd.domain.generic.ValueObject;

public class PlayerName implements ValueObject<String> {
    private final String playerName;

    private PlayerName(String playerName){
        if (playerName != null && !playerName.isEmpty()) {
            this.playerName = playerName;
        } else {
            throw new IllegalArgumentException("Player name Cannot be empty");
        }
    }
    public static PlayerName of(String playerName){
        return new PlayerName(playerName);
    }
    @Override
    public String value() {
        return playerName;
    }
}
