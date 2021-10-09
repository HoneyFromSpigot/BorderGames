package de.webcode.bordergames.utils;

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
            if (!FOLDER.exists()) FOLDER.mkdirs();
            if (!cfgFile.exists()) cfgFile.createNewFile();
            if (!playerDataFile.exists()) playerDataFile.createNewFile();
            if (!playerSettingsFile.exists()) playerSettingsFile.createNewFile();

            cfg = YamlConfiguration.loadConfiguration(cfgFile);
            playerData = YamlConfiguration.loadConfiguration(playerDataFile);
            playerSettings = YamlConfiguration.loadConfiguration(playerSettingsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
