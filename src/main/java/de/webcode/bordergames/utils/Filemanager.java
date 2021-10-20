package de.webcode.bordergames.utils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Filemanager {
    private File FOLDER, cfgFile, playerSettingsFile, playerDataFile;

    private YamlConfiguration cfg, playerSettings, playerData;

    public Filemanager(){
        this.FOLDER = new File("./plugins/bordergames/");
        this.cfgFile = new File(FOLDER, "config.yml");
        this.playerSettingsFile = new File(FOLDER, "playerSettings.yml");
        this.playerDataFile = new File(FOLDER, "playerData.yml");

        try {
            boolean setupCfg = false;

            if (!FOLDER.exists()) FOLDER.mkdirs();
            if (!cfgFile.exists()){
                setupCfg = true;
                cfgFile.createNewFile();
            }
            if (!playerDataFile.exists()) playerDataFile.createNewFile();
            if (!playerSettingsFile.exists()) playerSettingsFile.createNewFile();

            cfg = YamlConfiguration.loadConfiguration(cfgFile);
            playerData = YamlConfiguration.loadConfiguration(playerDataFile);
            playerSettings = YamlConfiguration.loadConfiguration(playerSettingsFile);

            if (setupCfg) {
                cfg.set("Messgae.Prefix", "&8[&3&lBORDERGAMES&8]");
                cfg.set("Locations.Game.Lobby.X", 0);
                cfg.set("Locations.Game.Lobby.Y", 60);
                cfg.set("Locations.Game.Lobby.Z", 0);
                cfg.set("Locations.Game.Lobby.World", "world");
                cfg.set("Locations.Game.Arena.X", 0);
                cfg.set("Locations.Game.Arena.Y", 60);
                cfg.set("Locations.Game.Arena.Z", 0);
                cfg.set("Locations.Game.Arena.World", "world");
                cfg.set("Locations.Game.Spawn.X", 0);
                cfg.set("Locations.Game.Spawn.Y", 60);
                cfg.set("Locations.Game.Spawn.Z", 0);
                cfg.set("Locations.Game.Spawn.World", "world");
                cfg.set("Locations.Game.World", "world");

                saveFiles();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getStringColorTranslated(String path){
        return ChatColor.translateAlternateColorCodes('&', cfg.getString(path));
    }

    public void saveFiles() {
        try {
            cfg.save(cfgFile);
            playerSettings.save(playerSettingsFile);
            playerData.save(playerDataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public YamlConfiguration getCfg() {
        return cfg;
    }

    public YamlConfiguration getPlayerSettings() {
        return playerSettings;
    }

    public YamlConfiguration getPlayerData() {
        return playerData;
    }
}
