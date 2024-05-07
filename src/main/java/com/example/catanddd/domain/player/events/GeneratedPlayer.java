package com.example.catanddd.domain.player.events;

import com.example.catanddd.domain.generic.DomainEvent;
import com.example.catanddd.domain.player.entities.structure.values.StructureId;
import com.example.catanddd.domain.player.events.enums.EventEnum;
import com.example.catanddd.domain.player.events.primitives.StructurePrimitive;

import java.util.List;

public class GeneratedPlayer extends DomainEvent {

    private String playerId;

    private String playerName;

    private Integer points;

    private List<String> resources;

    private List<StructurePrimitive> structures;


    public GeneratedPlayer() {
        super(EventEnum.GENERATED_PLAYER.name());
    }

    public GeneratedPlayer(String playerId, String playerName,Integer points, List<String> resources, List<StructurePrimitive> structures ){
        super(EventEnum.GENERATED_PLAYER.name());
        this.playerId = playerId;
        this.playerName = playerName;
        this.resources = resources;
        this.structures = structures;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Integer getPoints() {
        return points;
    }

    public List<String> getResources() {
        return resources;
    }

    public List<StructurePrimitive> getStructures() {
        return structures;
    }
}
