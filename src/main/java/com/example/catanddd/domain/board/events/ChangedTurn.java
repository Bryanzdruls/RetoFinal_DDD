package com.example.catanddd.domain.board.events;

import com.example.catanddd.domain.board.events.enums.EventEnum;
import com.example.catanddd.domain.generic.DomainEvent;
import com.example.catanddd.domain.player.values.PlayerId;

import java.util.Queue;

public class ChangedTurn extends DomainEvent {

    private String boardId;

    private String turnId;

    private String turnPlayerName;

    private Queue<String> playersInGame;
    public ChangedTurn(String type) {
        super(EventEnum.CHANGED_TURN.name());
    }

    public ChangedTurn(String boardId, String turnId, String turnPlayerName, Queue<String> playersInGame) {
        super(EventEnum.CHANGED_TURN.name());
        this.boardId = boardId;
        this.turnId = turnId;
        this.turnPlayerName = turnPlayerName;
        this.playersInGame = playersInGame;
    }

    public String boardId() {
        return boardId;
    }

    public String turnId() {
        return turnId;
    }

    public String turnPlayerName() {
        return turnPlayerName;
    }

    public Queue<String> playersInGame() {
        return playersInGame;
    }
}
