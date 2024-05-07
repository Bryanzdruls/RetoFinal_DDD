package com.example.catanddd.domain.board.events;

import com.example.catanddd.domain.board.events.enums.EventEnum;
import com.example.catanddd.domain.generic.DomainEvent;

public class GeneratedTerrain extends DomainEvent {

    private String boardId;

    private String terrainId;

    private Integer terrainNumber;

    private String terrainType;

    public GeneratedTerrain() {
        super(EventEnum.GENERATED_TERRAIN.name());
    }

    public GeneratedTerrain(String boardId, String terrainId, Integer terrainNumber, String terrainType) {
        super(EventEnum.GENERATED_TERRAIN.name());
        this.boardId = boardId;
        this.terrainId = terrainId;
        this.terrainNumber = terrainNumber;
        this.terrainType = terrainType;
    }

    public String boardId() {
        return boardId;
    }

    public String terrainId() {
        return terrainId;
    }

    public Integer terrainNumber() {
        return terrainNumber;
    }

    public String terrainType() {
        return terrainType;
    }
}
