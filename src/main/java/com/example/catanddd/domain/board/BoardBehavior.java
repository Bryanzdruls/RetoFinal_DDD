package com.example.catanddd.domain.board;

import com.example.catanddd.domain.board.entities.knight.Knight;
import com.example.catanddd.domain.board.entities.knight.values.KnightId;
import com.example.catanddd.domain.board.entities.terrain.Terrain;
import com.example.catanddd.domain.board.entities.terrain.values.TerrainEnum;
import com.example.catanddd.domain.board.entities.terrain.values.TerrainId;
import com.example.catanddd.domain.board.entities.terrain.values.TerrainName;
import com.example.catanddd.domain.board.entities.terrain.values.TerrainNumber;
import com.example.catanddd.domain.board.entities.turn.Turn;
import com.example.catanddd.domain.board.entities.turn.values.PlayerName;
import com.example.catanddd.domain.board.entities.turn.values.TurnId;
import com.example.catanddd.domain.board.events.ChangedTurn;
import com.example.catanddd.domain.board.events.ChangedTurnReverse;
import com.example.catanddd.domain.board.events.GeneratedBoard;
import com.example.catanddd.domain.board.events.GeneratedTerrain;
import com.example.catanddd.domain.generic.EventChange;
import com.example.catanddd.domain.generic.Identity;
import com.example.catanddd.domain.player.Player;
import com.example.catanddd.domain.player.values.PlayerId;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BoardBehavior extends EventChange {

    public BoardBehavior(Board board) {
        apply((GeneratedBoard event)->{
            board.dice = null;
            board.knight = new Knight(KnightId.of(event.knightId()));

            Queue<Player> playerQueue = new LinkedList<>();
            final String NAME = "PLAYER_";
            for (String playerId : event.playersIds()) {
                Player player = Player.from(
                        playerId,
                        NAME + playerId.toUpperCase()
                );
                playerQueue.add(player);
            }

            Player firstPlayer = playerQueue.poll();
            playerQueue.add(firstPlayer);


            board.turn = Turn.from(
                    TurnId.of(event.turnId()),
                    PlayerName.of(event.turnPlayerName()),
                    firstPlayer,
                    playerQueue
            );
            board.terrains = new ArrayList<Terrain>();
        });

        apply((GeneratedTerrain event)->{
            try {
                TerrainEnum.valueOf(event.terrainType());
            }catch (Exception e) {
                throw new IllegalArgumentException("TerrainType " + event.terrainType()+ " Is not supported");
            }

            Terrain terrain = Terrain.from(
                    TerrainId.of(event.terrainId()),
                    TerrainNumber.of(event.terrainNumber()),
                    TerrainName.of(TerrainEnum.valueOf(event.terrainType()))
            );


            if (board.terrains().size() < 19){
                terrainValidations(board,terrain);
            }
        });

        apply((ChangedTurn event)->{
            Turn turn = board.turn;

            if (turn == null){
                throw new IllegalStateException("Turn wasn't instantiated properly. Did you generate the board?");
            }
            if (turn.playerQueue().isEmpty()) {
                throw new IllegalStateException("For some unexpected reason playerQueue is empty");
            }
            Queue<Player> playerQueue = turn.playerQueue();
            Player playerInTurn = playerQueue.poll();
            playerQueue.add(playerInTurn);

            if (playerInTurn == null) {
                throw new IllegalStateException("For some reason playerInTurn was null");
            }

            turn.updateTurn(playerInTurn, playerQueue);
        });

        apply((ChangedTurnReverse event)->{
            Turn turn = board.turn;

            if (turn == null){
                throw new IllegalStateException("Turn wasn't instantiated properly. Did you generate the board?");
            }
            if (turn.playerQueue().isEmpty()) {
                throw new IllegalStateException("For some unexpected reason playerQueue is empty");
            }

            Stack<Player> turnReverse = new Stack<Player>();
            Queue<Player> playerQueueBefore= new LinkedList<>(turn.playerQueue());
            Queue<Player> playerQueue = new LinkedList<>(turn.playerQueue());

            while (!playerQueue.isEmpty()) {
                turnReverse.push(playerQueue.poll());
            }

            while (!turnReverse.isEmpty()) {
                playerQueue.add(turnReverse.pop());
            }


            Player playerInTurn = playerQueue.poll();
            playerQueue.add(playerInTurn);

            if (playerInTurn == null) {
                throw new IllegalStateException("For some reason playerInTurn was null");
            }

            turn.updateTurn(playerInTurn, playerQueue);
        });
    }

    private void terrainValidations(Board board, Terrain terrain){
        if (board.terrains().size() < 19){
            internalTerrainValidations(board,terrain);

        } else {
            throw new IllegalStateException("Already exists 19 terrain is not possible to add more");
        }
    }

    private void internalTerrainValidations(Board board, Terrain terrain){
        int countForest = 0;
        int countHill = 0;
        int countPasture = 0;
        int countField = 0;
        int countMountain = 0;
        int countDesert = 0;

        for (Terrain t : board.terrains()) {
            switch (t.terrainType()) {
                case FOREST -> countForest++;
                case HILLS -> countHill++;
                case PASTURE -> countPasture++;
                case FIELDS -> countField++;
                case MOUNTAINS -> countMountain++;
                case DESERT -> countDesert++;
            }
        }


        if (terrain.terrainType() == TerrainEnum.DESERT) {
            if (countDesert == 0) {
                board.terrains.add(terrain);
            } else {
                throw new IllegalStateException("Already exists Desert terrain and cannot be more than one");
            }
        } else {
            internalTypeMaximumTerrainVerification(board, terrain, countForest, countHill,  countPasture, countField , countMountain);
        }
    }

    private void internalTypeMaximumTerrainVerification(Board board, Terrain terrain,int countForest, int countHill, int countPasture,int countField ,int countMountain){
        if ((terrain.terrainType() == TerrainEnum.FOREST && countForest < 4) ||
                (terrain.terrainType() == TerrainEnum.HILLS && countHill < 4) ||
                (terrain.terrainType() == TerrainEnum.PASTURE && countPasture < 4) ||
                (terrain.terrainType() == TerrainEnum.FIELDS && countField < 3) ||
                (terrain.terrainType() == TerrainEnum.MOUNTAINS && countMountain < 3)) {
            board.terrains.add(terrain);
        } else {
            throw new IllegalStateException("Terrain type: "+ terrain.terrainType() + " already exceeded his limit try with other terrain");
        }
    }
}
