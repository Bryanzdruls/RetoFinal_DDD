package com.example.catanddd.domain.player.entities.structure;

import com.example.catanddd.domain.board.entities.terrain.values.TerrainId;
import com.example.catanddd.domain.generic.Entity;
import com.example.catanddd.domain.player.entities.structure.values.StructureId;
import com.example.catanddd.domain.player.entities.structure.values.StructureType;

import java.util.List;

public class Structure extends Entity<StructureId> {
    private StructureType structureType;

    private List<TerrainId> terrainsRelated;

    public Structure(StructureId id) {
        super(id);
    }

    private Structure(StructureId structureId, StructureType structureType, List<TerrainId> terrainsRelated) {
        super(structureId);
        this.structureType = structureType;
        this.terrainsRelated = terrainsRelated;
    }

    public static Structure from(StructureId structureId, StructureType structureType, List<TerrainId> terrainsRelated){
        return new Structure(structureId, structureType, terrainsRelated);
    }

    public String structureType() {
        return structureType.value().toString();
    }
    public List<TerrainId> terrainsRelated() {
        return terrainsRelated;
    }

}

