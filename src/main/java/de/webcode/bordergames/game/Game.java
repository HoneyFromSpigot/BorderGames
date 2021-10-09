package de.webcode.bordergames.game;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private int maxPlayerCount = 8;
    private int playerCount;
    private boolean started;

    public Game(){
        this.players = new ArrayList<>();
        this.playerCount = 0;
        this.started = false;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void addPlayer(ArrayList<Player> player){
        for (Player player1 : player) {
            players.add(player1);
        }
    }

    public boolean isFull(){
        return playerCount >= maxPlayerCount;
    }

    public boolean isStarted() {
        return started;
    }

}
