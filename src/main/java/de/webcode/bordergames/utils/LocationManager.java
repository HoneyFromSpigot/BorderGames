package de.webcode.bordergames.utils;

import de.webcode.bordergames.BorderGames;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

public class LocationManager {
    private Location gameLobby;

    public LocationManager() {
        YamlConfiguration cfg = BorderGames.INSTANCE.getFilemanager().getCfg();
        this.gameLobby = new Location(Bukkit.getWorld(cfg.getString("Locations.Game.Lobby.World")), cfg.getInt("Locations.Game.X"), cfg.getInt("Locations.Game.Lobby.Y"), cfg.getInt("Locations.Game.Lobby.Z"));

    }

    public Location getGameLobby() {
        return gameLobby;
    }
}
