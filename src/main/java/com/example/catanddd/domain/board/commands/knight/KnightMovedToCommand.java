package com.example.catanddd.domain.board.commands.knight;

import com.example.catanddd.domain.generic.Command;

public class KnightMovedToCommand extends Command {
    private String boardId;
    private String knightId;

    private String terrainId;

    public KnightMovedToCommand(String boardId, String knightId, String terrainId) {
        this.boardId = boardId;
        this.knightId = knightId;
        this.terrainId = terrainId;
    }

    public String boardId() {
        return boardId;
    }

    public String knightId() {
        return knightId;
    }

    public String terrainId() {
        return terrainId;
    }
}
