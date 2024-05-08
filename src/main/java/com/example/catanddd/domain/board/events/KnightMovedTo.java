package com.example.catanddd.domain.board.events;

import com.example.catanddd.domain.board.events.enums.EventEnum;
import com.example.catanddd.domain.generic.DomainEvent;

public class KnightMovedTo extends DomainEvent {

    private String boardId;
    private String knightId;

    private String terrainId;

    public KnightMovedTo(String boardId, String knightId, String terrainId) {
        super(EventEnum.KNIGHT_MOVED_TO.name());
        this.boardId = boardId;
        this.knightId = knightId;
        this.terrainId = terrainId;
    }

    public KnightMovedTo() {
        super(EventEnum.KNIGHT_MOVED_TO.name());
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
