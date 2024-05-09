package com.example.catanddd.domain.player;

import com.example.catanddd.domain.board.entities.terrain.values.TerrainId;
import com.example.catanddd.domain.generic.AggregateRoot;
import com.example.catanddd.domain.generic.DomainEvent;
import com.example.catanddd.domain.player.entities.structure.Structure;
import com.example.catanddd.domain.player.events.GeneratedPlayer;
import com.example.catanddd.domain.player.events.GeneratedStructure;
import com.example.catanddd.domain.player.events.primitives.StructurePrimitive;
import com.example.catanddd.domain.player.events.structure.UpdatedSettlement;
import com.example.catanddd.domain.player.values.PlayerId;

import com.example.catanddd.domain.player.values.PlayerName;
import com.example.catanddd.domain.player.values.Points;
import com.example.catanddd.domain.player.values.Resource;

import java.util.ArrayList;
import java.util.List;

public class Player extends AggregateRoot<PlayerId> {

    protected PlayerName playerName;

    protected Points points;

    protected List<Resource> resources;

    protected List<Structure> structures;

    private Player(PlayerId id) {
        super(id);
        subscribe(new PlayerBehavior(this));
    }

    public Player(String playerId, String playerName,
                  Integer points, List<String> resources, List<StructurePrimitive> structures) {
        super(PlayerId.of(playerId));
        subscribe(new PlayerBehavior(this));
        appendChangeEvent(new GeneratedPlayer(playerId, playerName, points,  resources,  structures)).apply();
    }

    public static Player from(String playerId, List<DomainEvent> events){
        Player player = new Player(PlayerId.of(playerId));
        events.forEach(player::applyEvent);
        return player;
    }

    public static Player from(String playerId, String playerName){
        final Integer ZERO_POINTS_INITIAL = 0;
        return new Player(playerId, playerName, ZERO_POINTS_INITIAL, new ArrayList<String>(), new ArrayList<StructurePrimitive>());
    }
    public String playerName() {
        return playerName.value();
    }

    public Integer points() {
        return points.value();
    }

    public void addPoints(int points){
        this.points = Points.of(this.points.value()  + points);
    }
    public List<Resource> resources() {
        return resources;
    }

    public List<Structure> structures() {
        return structures;
    }

    public void generateStructure(String playerId, String structureId, String structureType, List<TerrainId> terrainRelated){
        appendChangeEvent(new GeneratedStructure(playerId, structureId, structureType,terrainRelated)).apply();
    }

    public void updateSettlement(String playerId, Integer playerPoints, List<String> resourcesType, List<Integer> resourcesQuantity, String structureId,
                                 String structureType, List<String> terrainsRelated){
        appendChangeEvent(new UpdatedSettlement(playerId, playerPoints,resourcesType, resourcesQuantity, structureId,
                structureType, terrainsRelated)).apply();
    }

    public void addResources(Resource resource){
        this.resources.add(resource);
    }
}
