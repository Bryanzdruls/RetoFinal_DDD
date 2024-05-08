package com.example.catanddd.domain.board;

import com.example.catanddd.domain.board.entities.corner.Corner;
import com.example.catanddd.domain.board.entities.knight.Knight;
import com.example.catanddd.domain.board.entities.terrain.Terrain;
import com.example.catanddd.domain.board.entities.turn.Turn;
import com.example.catanddd.domain.board.events.*;
import com.example.catanddd.domain.board.values.BoardId;
import com.example.catanddd.domain.board.entities.turn.values.Dice;
import com.example.catanddd.domain.board.values.Player;
import com.example.catanddd.domain.generic.AggregateRoot;
import com.example.catanddd.domain.generic.DomainEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Board extends AggregateRoot<BoardId> {

    protected Knight knight;

    protected Turn turn;

    protected Player player;

    protected List<Corner> corner;

    protected List<Terrain> terrains;

    public Board(BoardId id) {
        super(id);
        subscribe(new BoardBehavior(this));
        this.terrains = new ArrayList<>();
        this.corner = new ArrayList<>();
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

    public void addResourceToPlayer(String boardId, String cornerId, Integer quantityResource,boolean isCornerAvailable, String playerId, String playerName){
        appendChangeEvent(new AddedResourceToPlayer(boardId, cornerId,  quantityResource, isCornerAvailable, playerId,playerName)).apply();
    }

    public void knightMoveTo(String boardId, String knightId, String terrainId){
        appendChangeEvent(new KnightMovedTo(boardId, knightId, terrainId)).apply();
    }
}
