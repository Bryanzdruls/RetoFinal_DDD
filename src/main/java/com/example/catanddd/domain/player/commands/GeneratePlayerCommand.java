package com.example.catanddd.domain.player.commands;

import com.example.catanddd.domain.generic.Command;
import com.example.catanddd.domain.player.events.primitives.StructurePrimitive;

import java.util.List;

public class GeneratePlayerCommand extends Command{

    private String playerId;

    private String playerName;

    private Integer points;

    private List<String> resources;

    private List<StructurePrimitive> structures;

    public GeneratePlayerCommand(String playerId, String playerName, Integer points, List<String> resources, List<StructurePrimitive> structures) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.points = points;
        this.resources = resources;
        this.structures = structures;
    }

    public GeneratePlayerCommand() {
    }

    public String playerId() {
        return playerId;
    }

    public String playerName() {
        return playerName;
    }

    public Integer points() {
        return points;
    }

    public List<String> resources() {
        return resources;
    }

    public List<StructurePrimitive> structures() {
        return structures;
    }
}
