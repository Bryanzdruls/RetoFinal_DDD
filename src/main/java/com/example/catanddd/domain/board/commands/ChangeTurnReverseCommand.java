package com.example.catanddd.domain.board.commands;

import com.example.catanddd.domain.generic.Command;
import com.example.catanddd.domain.player.values.PlayerId;

import java.util.Queue;

public class ChangeTurnReverseCommand extends Command {
    private String boardId;

    private String turnId;

    private String turnPlayerName;

    private Queue<String> playersInGame;

    public ChangeTurnReverseCommand(String boardId, String turnId, String turnPlayerName, Queue<String> playersInGame) {
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
