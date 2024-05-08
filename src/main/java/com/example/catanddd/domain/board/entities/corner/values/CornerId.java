package com.example.catanddd.domain.board.entities.corner.values;

import com.example.catanddd.domain.board.entities.terrain.values.TerrainId;
import com.example.catanddd.domain.generic.Identity;

public class CornerId extends Identity {
    public CornerId() {
        super();
    }

    private CornerId(String uuid){
        super(uuid);
    }

    public static CornerId of(String uuid){
        return new CornerId(uuid);
    }
}
