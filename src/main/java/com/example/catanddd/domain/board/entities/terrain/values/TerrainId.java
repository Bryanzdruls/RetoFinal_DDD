package com.example.catanddd.domain.board.entities.terrain.values;

import com.example.catanddd.domain.board.entities.knight.values.KnightId;
import com.example.catanddd.domain.generic.Identity;

public class TerrainId extends Identity {
    private TerrainId() {
        super();
    }

    private TerrainId(String uuid){
        super(uuid);
    }

    public static TerrainId of(String uuid){
        return new TerrainId(uuid);
    }
}
