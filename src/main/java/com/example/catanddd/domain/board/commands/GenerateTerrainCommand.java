package com.example.catanddd.domain.board.commands;

import com.example.catanddd.domain.generic.Command;

public class GenerateTerrainCommand extends Command {

    private String boardId;

    private String terrainId;

    private Integer terrainNumber;

    private String terrainType;

    public GenerateTerrainCommand() {
    }

    public GenerateTerrainCommand(String boardId, String terrainId, Integer terrainNumber, String terrainType) {
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

