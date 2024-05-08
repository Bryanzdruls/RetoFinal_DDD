package com.example.catanddd.domain.board.commands;

import com.example.catanddd.domain.generic.Command;

public class AddResourceToPlayerCommand extends Command {
    private String boardId;

    private String cornerId;

    private Integer quantityResource;

    private Boolean isCornerAvailable;

    private String playerId;

    private String playerName;

    public AddResourceToPlayerCommand(String boardId, String cornerId, Integer quantityResource,Boolean isCornerAvailable, String playerId, String playerName) {
        this.boardId = boardId;
        this.cornerId = cornerId;
        this.quantityResource = quantityResource;
        this.isCornerAvailable = isCornerAvailable;
        this.playerId = playerId;
        this.playerName = playerName;
    }

    public String boardId() {
        return boardId;
    }

    public String cornerId() {
        return cornerId;
    }

    public Boolean isCornerAvailable() {
        return isCornerAvailable;
    }

    public Integer quantityResource() {
        return quantityResource;
    }

    public String playerId() {
        return playerId;
    }

    public String playerName() {
        return playerName;
    }
}
