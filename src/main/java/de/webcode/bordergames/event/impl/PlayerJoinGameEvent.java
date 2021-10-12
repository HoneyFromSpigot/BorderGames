package de.webcode.bordergames.event.impl;

import de.webcode.bordergames.event.Event;
import org.bukkit.entity.Player;
public class PlayerJoinGameEvent extends Event {
    private Player player;

    public PlayerJoinGameEvent(Player player){
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
