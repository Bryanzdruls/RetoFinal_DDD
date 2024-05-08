package com.example.catanddd.domain.board.events;

import com.example.catanddd.domain.board.events.enums.EventEnum;
import com.example.catanddd.domain.generic.DomainEvent;

import java.util.List;

public class GeneratedBoard extends DomainEvent {

    private Integer dice;

    private String knightId;

    private String turnId;

    private String turnPlayerName;

    private List<String> playersIds;

    private String playerInTurnId;

    public GeneratedBoard() {
        super(EventEnum.GENERATED_TERRAIN.name());
    }

    public GeneratedBoard(Integer dice, String knightId, String turnId, String turnPlayerName, List<String> playersIds, String playerInTurnId) {
        super(EventEnum.GENERATED_TERRAIN.name());
        this.dice = dice;
        this.knightId = knightId;
        this.turnId = turnId;
        this.turnPlayerName = turnPlayerName;
        this.playersIds = playersIds;
        this.playerInTurnId = playerInTurnId;
    }

    public GeneratedBoard(List<String> playersIds) {
        super(EventEnum.GENERATED_TERRAIN.name());

        this.playersIds = playersIds;

    }
    public Integer dice() {
        return dice;
    }

    public String knightId() {
        return knightId;
    }

    public String turnId() {
        return turnId;
    }

    public String turnPlayerName() {
        return turnPlayerName;
    }

    public List<String> playersIds() {
        return playersIds;
    }

    public String playerInTurnId() {
        return playerInTurnId;
    }
}
