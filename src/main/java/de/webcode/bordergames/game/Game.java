package de.webcode.bordergames.game;

import de.webcode.bordergames.BorderGames;
import de.webcode.bordergames.event.impl.PlayerJoinGameEvent;
import de.webcode.bordergames.game.scoreboard.GameScoreboard;
import de.webcode.bordergames.utils.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Game {
    private LocationManager locationManager;
    private ArrayList<Player> players;
    private World world;
    private GameScoreboard gameScoreboard;
    private int maxPlayerCount = 8;
    private int startBorderSize = 100;
    private int playerCount;
    private boolean started;

    public Game(){
        this.locationManager = new LocationManager();
        this.gameScoreboard = new GameScoreboard(this);
        this.world = Bukkit.getWorld(BorderGames.INSTANCE.getFilemanager().getCfg().getString("Locations.Game.World"));
        this.players = new ArrayList<>();
        this.playerCount = 0;
        this.started = false;

        world.getWorldBorder().setSize(100);
    }

    public void addPlayer(Player player){
        new PlayerJoinGameEvent(player).call();
        players.add(player);


        player.setScoreboard(gameScoreboard.getScoreboard());
    }

    public void addPlayer(ArrayList<Player> player){
        for (Player player1 : player) {
            addPlayer(player1);
        }
    }

    public int getPlayerCount(){
        return players.size();
    }

    public boolean isFull(){
        return playerCount >= maxPlayerCount;
    }

    public World getWorld() {
        return world;
    }

    public String getBorderSizeString(){
        return "" + world.getWorldBorder().getSize() + "x" + world.getWorldBorder().getSize();
    }

    public String getRemainingPlayerString(){
        if(isFull()) return "";

        String s = "§e" + getPlayerCount() + " §8/§e " + maxPlayerCount;

        return s;
    }

    public boolean isStarted() {
        return started;
    }

}
