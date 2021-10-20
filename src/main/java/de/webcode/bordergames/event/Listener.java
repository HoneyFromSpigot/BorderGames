package de.webcode.bordergames.event;

import de.webcode.bordergames.BorderGames;
import de.webcode.bordergames.event.impl.PlayerJoinGameEvent;
import de.webcode.bordergames.event.impl.Second1Event;
import de.webcode.bordergames.event.impl.Second5Event;
import de.webcode.bordergames.game.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
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
    public void onEntityDamage(EntityDamageByEntityEvent event){
        if(!(event.getEntity() instanceof Player)) return;
        if(!(event.getDamager() instanceof Player)) return;

        Player player = (Player) event.getEntity();
        Player damager = (Player) event.getDamager();

        if(!BorderGames.INSTANCE.getGame().isInFight() && BorderGames.INSTANCE.getGame().getPlayers().contains(damager) && BorderGames.INSTANCE.getGame().getPlayers().contains(player)){
            event.setCancelled(true);
            event.setDamage(0);
            damager.sendMessage("§cDu kannst in der Farmzeit niemand anderen Angreifen!");
            return;
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();

        if(BorderGames.INSTANCE.getGame().getPlayers().contains(player) && BorderGames.INSTANCE.getGame().isMoveDisabled()){
            event.setCancelled(true);
            event.setTo(event.getFrom());
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        event.setDeathMessage("");
        Player player = event.getEntity();

        if(!BorderGames.INSTANCE.getGame().getPlayers().contains(player)) return;

        BorderGames.INSTANCE.getGame().onPlayerDeath(player);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        event.setQuitMessage("");
        Player player = event.getPlayer();

        BorderGames.INSTANCE.getGame().removePlayer(player);
    }
}
