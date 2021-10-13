package de.webcode.bordergames.event;

import de.webcode.bordergames.BorderGames;
import de.webcode.bordergames.event.impl.PlayerJoinGameEvent;
import de.webcode.bordergames.event.impl.Second1Event;
import de.webcode.bordergames.event.impl.Second5Event;
import de.webcode.bordergames.game.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listener implements org.bukkit.event.Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage("");
        Game game = BorderGames.INSTANCE.getGame();
        Player player = event.getPlayer();


        if (!game.isFull() && !game.isStarted()) {
            game.addPlayer(player);
        }else{
            player.sendMessage("§cDu konntest dem Spiel nicht beitreten!");

            //TODO: Resend player on Bungee Message Channel with returned boolean
        }

    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        event.setQuitMessage("");
        Player player = event.getPlayer();

        BorderGames.INSTANCE.getGame().removePlayer(player);
    }
}
