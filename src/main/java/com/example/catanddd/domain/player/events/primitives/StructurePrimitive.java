package com.example.catanddd.domain.player.events.primitives;

public class StructurePrimitive {

    private String structureId;

    private String structureType;

    public StructurePrimitive(String structureId, String structureType) {
        this.structureId = structureId;
        this.structureType = structureType;
    }

    public StructurePrimitive() {
    }

    public String getStructureId() {
        return structureId;
    }

    public String getStructureType() {
        return structureType;
    }
}
