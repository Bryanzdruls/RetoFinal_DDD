package com.example.catanddd.domain.board;

import com.example.catanddd.domain.board.entities.knight.Knight;
import com.example.catanddd.domain.board.entities.terrain.Terrain;
import com.example.catanddd.domain.board.entities.turn.Turn;
import com.example.catanddd.domain.board.entities.turn.values.TurnId;
import com.example.catanddd.domain.board.events.*;
import com.example.catanddd.domain.board.values.BoardId;
import com.example.catanddd.domain.board.values.Dice;
import com.example.catanddd.domain.generic.AggregateRoot;
import com.example.catanddd.domain.generic.DomainEvent;
import com.example.catanddd.domain.generic.Entity;
import com.example.catanddd.domain.player.values.PlayerId;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Board extends AggregateRoot<BoardId> {

    protected Dice dice;

    protected Knight knight;

    protected Turn turn;

    //round

    protected List<Terrain> terrains;

    public Board(BoardId id) {
        super(id);
        subscribe(new BoardBehavior(this));
        this.terrains = new ArrayList<>();
    }

    public Board(String boardId, Integer dice, String knightId,
                 String turnId, String turnPlayerName,
                 List<String> players, String playerInTurnId ){

        super(BoardId.of(boardId));
        subscribe(new BoardBehavior(this));
        appendChangeEvent(new GeneratedBoard(dice,  knightId,
                 turnId,  turnPlayerName, players,  playerInTurnId)).apply();
    }

    public static Board from(String boardId, List<DomainEvent> events){
       Board board = new Board(BoardId.of(boardId));
       events.forEach(board::applyEvent);
       return board;
    }

    public Integer dice() {
        return dice.value();
    }

    public Integer knightTerrainLocated() {
        return knight.terrainLocated().terrainNumber();
    }

    public Turn turn() {
        return turn;
    }

    public List<Terrain> terrains() {
        return terrains;
    }


    public void generateTerrain(String boardId, String terrainId, Integer terrainNumber, String terrainType){
        appendChangeEvent(new GeneratedTerrain(boardId, terrainId, terrainNumber, terrainType)).apply();
    }

    public void changeTurn(String boardId, String turnId, String turnPlayerName, Queue<String> playersInGame){
        appendChangeEvent(new ChangedTurn(boardId, turnId, turnPlayerName,  playersInGame)).apply();
    }

    public void changeTurnReverse(String boardId, String turnId, String turnPlayerName, Queue<String> playersInGame){
        appendChangeEvent(new ChangedTurnReverse(boardId, turnId, turnPlayerName, playersInGame)).apply();
    }

    public void addResourceToPlayer(String boardId, String resourceId, Integer quantityResource, String playerId){
        appendChangeEvent(new AddedResourceToPlayer(boardId, resourceId,  quantityResource, playerId)).apply();
    }
}
