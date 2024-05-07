package com.example.catanddd.domain.player.commands;

import com.example.catanddd.domain.board.entities.terrain.values.TerrainId;
import com.example.catanddd.domain.generic.Command;

import java.util.List;

public class GenerateStructureCommand extends Command {
    private String playerId;

    private String structureId;

    private String structureType;

    private List<TerrainId> terrainRelated;

    public GenerateStructureCommand(String playerId, String structureId, String structureType, List<TerrainId> terrainRelated) {
        this.playerId = playerId;
        this.structureId = structureId;
        this.structureType = structureType;
        this.terrainRelated = terrainRelated;
    }

    public GenerateStructureCommand() {
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getStructureId() {
        return structureId;
    }

    public String getStructureType() {
        return structureType;
    }

    public List<TerrainId> getTerrainRelated() {
        return terrainRelated;
    }
}
