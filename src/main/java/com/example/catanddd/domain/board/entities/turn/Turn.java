package com.example.catanddd.domain.board.entities.turn;

import com.example.catanddd.domain.player.Player;
import com.example.catanddd.domain.board.entities.turn.values.PlayerName;
import com.example.catanddd.domain.board.entities.turn.values.TurnId;
import com.example.catanddd.domain.generic.Entity;

import java.util.LinkedList;
import java.util.Queue;

public class Turn  extends Entity<TurnId> {

    private PlayerName playerName;

    private Player playerInTurn;

    private Queue<Player> playerQueue;

    public Turn(TurnId id) {
        super(id);
    }

    private Turn(TurnId turnId, PlayerName playerName, Player playerInTurn, Queue<Player> playerQueue ) {
        super(turnId);
        this.playerName = playerName;
        this.playerInTurn = playerInTurn;
        this.playerQueue = playerQueue;
    }

    private Turn(TurnId turnId, PlayerName playerName ) {
        super(turnId);
        this.playerName = playerName;
        this.playerInTurn = null;
        this.playerQueue = new LinkedList<Player>();
    }

    public static Turn from(TurnId turnId, PlayerName playerName, Player playerInTurn, Queue<Player> playerQueue ){
        return  new Turn(turnId, playerName, playerInTurn, playerQueue);
    }

    public static Turn from(TurnId turnId, PlayerName playerName){
        return new Turn(turnId, playerName);
    }

    public String playerName(){
        return this.playerName.value();
    }

    public Player playerInTurn(){
        return this.playerInTurn;
    }

    public Queue<Player> playerQueue(){
        return this.playerQueue;
    }

    public boolean firstRoundCheck(Player player, Player firstPlayer, Player lastPlayer){
        return true;
    }

    public Turn updateTurn(Player playerInTurn, Queue<Player> playerQueue){
        this.playerName = PlayerName.of(playerInTurn.playerName());
        this.playerInTurn = playerInTurn;
        this.playerQueue = playerQueue;

        return this;
    }
}
