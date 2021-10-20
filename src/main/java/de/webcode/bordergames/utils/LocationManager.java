package de.webcode.bordergames.utils;

import de.webcode.bordergames.BorderGames;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

public class LocationManager {
    private Location gameLobby;
    private Location gameArena;
    private Location gameSpawn;

    public LocationManager() {
        YamlConfiguration cfg = BorderGames.INSTANCE.getFilemanager().getCfg();
        this.gameLobby = new Location(Bukkit.getWorld(cfg.getString("Locations.Game.Lobby.World")), cfg.getInt("Locations.Game.Lobby.X"), cfg.getInt("Locations.Game.Lobby.Y"), cfg.getInt("Locations.Game.Lobby.Z"));
        this.gameArena = new Location(Bukkit.getWorld(cfg.getString("Locations.Game.Arena.World")), cfg.getInt("Locations.Game.Arena.X"), cfg.getInt("Locations.Game.Arena.Y"), cfg.getInt("Locations.Game.Arena.Z"));
        this.gameSpawn = new Location(Bukkit.getWorld(cfg.getString("Locations.Game.Spawn.World")), cfg.getInt("Locations.Game.Spawn.X"), cfg.getInt("Locations.Game.Spawn.Y"), cfg.getInt("Locations.Game.Spawn.Z"));
    }

    public Location getGameArena() {
        return gameArena;
    }

    public Location getGameLobby() {
        return gameLobby;
    }

    public Location getGameSpawn() {
        return gameSpawn;
    }
}
