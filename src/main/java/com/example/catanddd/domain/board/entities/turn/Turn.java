package com.example.catanddd.domain.board.entities.turn;

import com.example.catanddd.domain.board.entities.turn.values.Dice;
import com.example.catanddd.domain.player.Player;
import com.example.catanddd.domain.board.entities.turn.values.PlayerName;
import com.example.catanddd.domain.board.entities.turn.values.TurnId;
import com.example.catanddd.domain.generic.Entity;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Turn extends Entity<TurnId> {

    private Dice dice;

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


        Random random = new Random();
        int randomNumberBetween2And12 = random.nextInt(11) + 2;
        this.dice= Dice.of(randomNumberBetween2And12);
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

    public boolean firstRoundCheck(Player player){

        if (!playerQueue.isEmpty() && !player.equals(playerQueue.peek())) {
            return true;
        } else {
            return false;
        }
    }

    public Turn updateTurn(Player playerInTurn, Queue<Player> playerQueue){
        this.playerName = PlayerName.of(playerInTurn.playerName());
        this.playerInTurn = playerInTurn;
        this.playerQueue = playerQueue;

        return this;
    }

    public int rollDice() {
        Random random = new Random();
        return random.nextInt(11) + 2;
    }
}
