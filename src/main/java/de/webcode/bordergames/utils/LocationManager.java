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

    public Location getArenaSpawnLocation(int arenaID, int spawnID) {
        YamlConfiguration cfg = BorderGames.INSTANCE.getFilemanager().getCfg();

        return new Location(Bukkit.getWorld(cfg.getString("Locations.Game.Arena." + arenaID + "." + spawnID + ".World")), cfg.getInt("Locations.Game.Arena." + arenaID + "." + spawnID + ".X"), cfg.getInt("Locations.Game.Arena." + arenaID + "." + spawnID + ".Y"), cfg.getInt("Locations.Game.Arena." + arenaID + "." + spawnID + ".Z"));
    }



    public Location getGameLobby() {
        return gameLobby;
    }
}
