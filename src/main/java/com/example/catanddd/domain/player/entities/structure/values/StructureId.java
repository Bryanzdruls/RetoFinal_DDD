package com.example.catanddd.domain.player.entities.structure.values;

import com.example.catanddd.domain.generic.Identity;

public class StructureId extends Identity {
    private StructureId() {
        super();
    }

    private StructureId(String uuid){
        super(uuid);
    }

    public static StructureId of(String uuid){
        return new StructureId(uuid);
    }
}
