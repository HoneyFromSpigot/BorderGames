package de.webcode.bordergames.game;

import de.webcode.bordergames.game.scoreboard.GameScoreboard;
import de.webcode.bordergames.utils.LocationManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Game {
    private LocationManager locationManager;
    private ArrayList<Player> players;
    private GameScoreboard gameScoreboard;
    private int maxPlayerCount = 8;
    private int playerCount;
    private boolean started;

    public Game(){
        this.locationManager = new LocationManager();
        this.gameScoreboard = new GameScoreboard();
        this.players = new ArrayList<>();
        this.playerCount = 0;
        this.started = false;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void addPlayer(ArrayList<Player> player){
        for (Player player1 : player) {
            addPlayer(player1);
        }
    }

    public boolean isFull(){
        return playerCount >= maxPlayerCount;
    }

    public boolean isStarted() {
        return started;
    }

}
