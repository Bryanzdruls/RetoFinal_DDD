package com.example.catanddd.domain.board.events;

import com.example.catanddd.domain.board.events.enums.EventEnum;
import com.example.catanddd.domain.generic.DomainEvent;

public class AddedResourceToPlayer extends DomainEvent {

    private String boardId;

    private String cornerId;

    private Integer quantityResource;

    private boolean isCornerAvailable;

    private String playerId;

    private String playerName;

    public AddedResourceToPlayer() {
        super(EventEnum.ADDED_RESOURCE_TO_PLAYER.name());
    }

    public AddedResourceToPlayer(String boardId, String cornerId, Integer quantityResource, boolean isCornerAvailable, String playerId, String playerName) {
        super(EventEnum.ADDED_RESOURCE_TO_PLAYER.name());
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

    public Integer quantityResource() {
        return quantityResource;
    }

    public String playerId() {
        return playerId;
    }

    public boolean isCornerAvailable() {
        return isCornerAvailable;
    }

    public String playerName() {
        return playerName;
    }
}
