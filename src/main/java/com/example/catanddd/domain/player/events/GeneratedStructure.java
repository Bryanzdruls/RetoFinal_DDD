package com.example.catanddd.domain.player.events;

import com.example.catanddd.domain.board.entities.terrain.values.TerrainId;
import com.example.catanddd.domain.generic.DomainEvent;
import com.example.catanddd.domain.player.events.enums.EventEnum;

import java.util.List;

public class GeneratedStructure extends DomainEvent {

    private String playerId;

    private String structureId;

    private String structureType;

    private List<TerrainId> terrainRelated;

    public GeneratedStructure() {
        super(EventEnum.GENERATED_STRUCTURE.name());
    }

    public GeneratedStructure(String playerId, String structureId, String structureType, List<TerrainId> terrainRelated) {
        super(EventEnum.GENERATED_STRUCTURE.name());
        this.playerId = playerId;
        this.structureId = structureId;
        this.structureType = structureType;
        this.terrainRelated = terrainRelated;
    }

    public String playerId() {
        return playerId;
    }

    public String structureId() {
        return structureId;
    }

    public String structureType() {
        return structureType;
    }

    public List<TerrainId> terrainsRelated() {
        return terrainRelated;
    }
}
