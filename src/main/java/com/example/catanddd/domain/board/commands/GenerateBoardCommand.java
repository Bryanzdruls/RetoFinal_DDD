package com.example.catanddd.domain.board.commands;

import com.example.catanddd.domain.generic.Command;

import java.util.List;

public class GenerateBoardCommand extends Command {

    private String boardId;

    private Integer dice;

    private String knightId;

    private String turnId;

    private String turnPlayerName;

    private List<String> playersIds;

    private String playerInTurnId;

    public GenerateBoardCommand() {
    }

    public GenerateBoardCommand(String boardId, Integer dice, String knightId, String turnId, String turnPlayerName, List<String> playersIds, String playerInTurnId) {
        this.boardId = boardId;
        this.dice = dice;
        this.knightId = knightId;
        this.turnId = turnId;
        this.turnPlayerName = turnPlayerName;
        this.playersIds = playersIds;
        this.playerInTurnId = playerInTurnId;
    }

    public String boardId() {
        return boardId;
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
