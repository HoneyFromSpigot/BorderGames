package de.webcode.bordergames;

import de.webcode.bordergames.event.EventManager;
import de.webcode.bordergames.event.Listener;
import de.webcode.bordergames.event.impl.GameStartEvent;
import de.webcode.bordergames.utils.Filemanager;
import de.webcode.bordergames.utils.LocationManager;
import de.webcode.bordergames.utils.ThreadManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class BorderGames extends JavaPlugin {
    public static BorderGames INSTANCE;

    private Filemanager filemanager;
    private Listener eventListener;
    private ThreadManager threadManager;

    @Override
    public void onEnable() {
        INSTANCE = this;

        this.filemanager = new Filemanager();
        this.eventListener = new Listener();

        registerCommands();
        registerEvents();

        this.threadManager = new ThreadManager();
    }

    private void registerCommands() {

    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();

        EventManager.register(eventListener);
        pm.registerEvents(eventListener, this);
    }

    public Filemanager getFilemanager() {
        return filemanager;
    }
}
