package com.example.catanddd.domain.board.commands;

import com.example.catanddd.domain.generic.Command;

public class AddResourceToPlayerCommand extends Command {
    private String boardId;

    private String resourceId;

    private Integer quantityResource;

    private String playerId;

    public AddResourceToPlayerCommand(String boardId, String resourceId, Integer quantityResource, String playerId) {
        this.boardId = boardId;
        this.resourceId = resourceId;
        this.quantityResource = quantityResource;
        this.playerId = playerId;
    }

    public String boardId() {
        return boardId;
    }

    public String resourceId() {
        return resourceId;
    }

    public Integer quantityResource() {
        return quantityResource;
    }

    public String playerId() {
        return playerId;
    }
}
