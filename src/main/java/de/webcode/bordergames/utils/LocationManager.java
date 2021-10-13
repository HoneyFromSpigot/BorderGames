package de.webcode.bordergames.utils;

import de.webcode.bordergames.BorderGames;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

public class LocationManager {
    private Location gameLobby;
    private Location gameArena;

    public LocationManager() {
        YamlConfiguration cfg = BorderGames.INSTANCE.getFilemanager().getCfg();
        this.gameLobby = new Location(Bukkit.getWorld(cfg.getString("Locations.Game.Lobby.World")), cfg.getInt("Locations.Game.Lobby.X"), cfg.getInt("Locations.Game.Lobby.Y"), cfg.getInt("Locations.Game.Lobby.Z"));
        this.gameArena = new Location(Bukkit.getWorld(cfg.getString("Locations.Game.Arena.World")), cfg.getInt("Locations.Game.Arena.X"), cfg.getInt("Locations.Game.Arena.Y"), cfg.getInt("Locations.Game.Arena.Z"));
    }

    public Location getGameArena() {
        return gameArena;
    }

    public Location getGameLobby() {
        return gameLobby;
    }
}
