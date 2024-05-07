package com.example.catanddd.domain.board.events;

import com.example.catanddd.domain.board.events.enums.EventEnum;
import com.example.catanddd.domain.generic.DomainEvent;

public class AddedResourceToPlayer extends DomainEvent {

    private String boardId;

    private String resourceId;

    private Integer quantityResource;

    private String playerId;

    public AddedResourceToPlayer() {
        super(EventEnum.ADDED_RESOURCE_TO_PLAYER.name());
    }

    public AddedResourceToPlayer(String boardId, String resourceId, Integer quantityResource, String playerId) {
        super(EventEnum.ADDED_RESOURCE_TO_PLAYER.name());
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
