package de.webcode.bordergames.event;

import de.webcode.bordergames.BorderGames;
import de.webcode.bordergames.event.impl.PlayerJoinGameEvent;
import de.webcode.bordergames.event.impl.Second1Event;
import de.webcode.bordergames.event.impl.Second5Event;
import de.webcode.bordergames.game.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listener implements org.bukkit.event.Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Game game = BorderGames.INSTANCE.getGame();
        Player player = event.getPlayer();


        if (!game.isFull()) {
            game.addPlayer(player);
        }else{
            player.sendMessage("§cDu konntest dem Spiel nicht beitreten!");

            //TODO: Resend player on Bungee Message Channel with returned boolean
        }
    }
}
