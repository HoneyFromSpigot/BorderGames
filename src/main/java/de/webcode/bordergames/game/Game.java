package de.webcode.bordergames.game;

import de.webcode.bordergames.BorderGames;
import de.webcode.bordergames.event.impl.PlayerJoinGameEvent;
import de.webcode.bordergames.game.scoreboard.GameScoreboard;
import de.webcode.bordergames.utils.LocationManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

public class Game {
    private LocationManager locationManager;
    private ArrayList<Player> players;
    private BukkitTask startingTimer;
    private BukkitTask worldTimer;
    private BukkitTask fightTimer;
    private World world;
    private GameScoreboard gameScoreboard;
    private int maxPlayerCount = 2;
    private int startingCountdown = 10;
    private int startBorderSize = 100;
    private int secondsForFight = 60; // 5 Minutes = 300
    private int secondsToNextHour = 60; //1 Hour = 3600 Seconds
    private boolean started;
    private boolean inFight;
    private boolean disableMove;

    public Game(){
        this.locationManager = new LocationManager();
        this.world = Bukkit.getWorld(BorderGames.INSTANCE.getFilemanager().getCfg().getString("Locations.Game.World"));
        this.players = new ArrayList<>();
        this.gameScoreboard = new GameScoreboard(this);
        this.started = false;
        this.inFight = false;
        this.disableMove = false;

        world.getWorldBorder().setSize(startBorderSize);
    }

    public void addPlayer(Player player){
        new PlayerJoinGameEvent(player).call();


        if(players.size() != 0){
            for (Player p : players) {
                p.sendMessage("§e" + player.getName() + " §a ist beigetreten! §8(§e"+ (players.size() + 1) + "§8/§e" + maxPlayerCount + "§8)");
            }
        }

        players.add(player);
        player.teleport(locationManager.getGameLobby());
        player.setScoreboard(gameScoreboard.getScoreboard());

        if (!isWaiting()) {
            this.startingTimer = new BukkitRunnable() {
                @Override
                public void run() {
                    if (startingCountdown > 0) {
                        startingCountdown--;
                    }else{
                        cancel();
                        startingCountdown = 10;
                        startGame();
                    }
                }
            }.runTaskTimer(BorderGames.INSTANCE, 0, 20);
        }
    }

    public void startGame(){
        //TODO: Teleport Players
        this.started = true;
        this.disableMove = false;
        inFight = false;
        this.worldTimer = new BukkitRunnable() {
            int prefSec = secondsToNextHour;
            @Override
            public void run() {
                if (secondsToNextHour > 0) {
                    secondsToNextHour--;
                }else{
                    cancel();
                    secondsToNextHour = prefSec;
                    prepareFight();
                }
            }
        }.runTaskTimer(BorderGames.INSTANCE, 0, 20);
    }

    public void prepareFight(){
        inFight = true;
        disableMove = true;

        new BukkitRunnable() {
            int countdown = 5;

            @Override
            public void run() {
                if (countdown > 0) {
                    for (Player player : players) {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§aKampf beginnt in: §6" + countdown));
                    }
                    countdown--;
                } else {
                    for (Player player : players) {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§aNach dem Kampf vergrößert sich die Border!"));
                    }
                    cancel();
                    startFight();
                }
            }
        }.runTaskTimer(BorderGames.INSTANCE, 0, 20);
    }

    public void startFight(){
        inFight = true;
        disableMove = false;
        this.fightTimer = new BukkitRunnable() {
            int prefSecs = secondsForFight;

            @Override
            public void run() {
                if (secondsForFight > 0) {
                    secondsForFight--;
                }else{
                    cancel();
                    secondsForFight = prefSecs;
                    startGame();
                }
            }
        }.runTaskTimer(BorderGames.INSTANCE, 0, 20);
    }

    public void onPlayerDeath(Player player){
        players.remove(player);
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());


        if(players.size() <= 1){
            win();
        }
    }

    public void removePlayer(Player player){
        if(!players.contains(player)) return;

        players.remove(player);

        if(player.isOnline()){
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        }

        if(isWaiting() && !isStarted()){
            for (Player p : players) {
                p.sendMessage("§e" + player.getName() + " §chat das Spiel verlassen");
            }
        }

        if (!isStarted()) {
            startingTimer.cancel();
            this.startingCountdown = 10;
            for (Player p : players) {
                p.sendMessage("§cStart abgebrochen!");
            }
        }

        if(isStarted() && players.size() <= 1){
            win();
        }
    }

    public void win(){
        if(!(players.size() <= 1)) return;
        Player winner = players.get(0);

        winner.playSound(winner.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 5);
        winner.sendMessage("§aDu hast das Spiel gewonnen!");
        winner.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());


        this.players.clear();
        this.inFight = false;
        this.started = false;

        new BukkitRunnable() {
            int countdown = 10;

            @Override
            public void run() {

                if(countdown > 0){
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage("§cDie Map wird in §6" + countdown + " Sekunden §cresetet!");
                    }

                    countdown--;
                }else{
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.kickPlayer("§cDie Map wird resetet! Du kannst nun wieder auf dem Hauptserver joinen, und in ein paar sekunden einem weiterem Spiel beitreten!");
                    }
                    Bukkit.getServer().reload();
                    cancel();
                    //TODO: Reset Map
                }
            }
        }.runTaskTimer(BorderGames.INSTANCE, 0, 20);
    }

    public void addPlayer(ArrayList<Player> player){
        for (Player player1 : player) {
            addPlayer(player1);
        }
    }

    public boolean isFull(){
        return players.size() >= maxPlayerCount;
    }

    public World getWorld() {
        return world;
    }

    public String getBorderSizeString(){
        return "" + world.getWorldBorder().getSize() + "x" + world.getWorldBorder().getSize();
    }

    public String getStartTimeString() {
        return "§e" + startingCountdown;
    }

    public String getTimeString() {
        if (!inFight) {
            int minutes = (secondsToNextHour % 3600) / 60;

            return "§e" + String.format("%02d:%02d", minutes, secondsToNextHour % 60);
        }

        int minutes = (secondsForFight % 3600) / 60;

        return "§e" + String.format("%02d:%02d", minutes, secondsForFight % 60);
    }

    public String getRemainingPlayerString(){
        String s = "§e" + players.size() + " §8/§e " + maxPlayerCount;

        return s;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public boolean isMoveDisabled() {
        return disableMove;
    }

    public boolean isWaiting() {
        if(players == null){
            return true;
        }
        return !(players.size() == maxPlayerCount);
    }

    public boolean isInFight() {
        return inFight;
    }

    public boolean isStarted() {
        return started;
    }

}
