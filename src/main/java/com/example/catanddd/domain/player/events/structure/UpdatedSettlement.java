package com.example.catanddd.domain.player.events.structure;

import com.example.catanddd.domain.generic.DomainEvent;
import com.example.catanddd.domain.player.events.enums.EventEnum;

import java.util.List;

public class UpdatedSettlement extends DomainEvent {
    private String playerId;

    private Integer playerPoints;

    private List<String> resourcesType;

    private List<Integer> resourcesQuantity;

    private String structureId;

    private String structureType;

    private List<String> terrainsRelated;


    public UpdatedSettlement() {
        super(EventEnum.UPDATED_SETTLEMENT.name());
    }

    public UpdatedSettlement(String playerId,Integer playerPoints, List<String> resourcesType, List<Integer> resourcesQuantity, String structureId, String structureType, List<String> terrainsRelated) {
        super(EventEnum.UPDATED_SETTLEMENT.name());
        this.playerId = playerId;
        this.playerPoints = playerPoints;
        this.resourcesType = resourcesType;
        this.resourcesQuantity = resourcesQuantity;
        this.structureId = structureId;
        this.structureType = structureType;
        this.terrainsRelated = terrainsRelated;
    }

    public String getPlayerId() {
        return playerId;
    }

    public Integer getPlayerPoints() {
        return playerPoints;
    }

    public List<String> getResourcesType() {
        return resourcesType;
    }

    public List<Integer> getResourcesQuantity() {
        return resourcesQuantity;
    }

    public String getStructureId() {
        return structureId;
    }

    public String getStructureType() {
        return structureType;
    }

    public List<String> getTerrainsRelated() {
        return terrainsRelated;
    }
}
