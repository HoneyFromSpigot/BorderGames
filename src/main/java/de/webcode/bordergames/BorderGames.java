package de.webcode.bordergames;

import de.webcode.bordergames.commands.BGGetPlayerCount;
import de.webcode.bordergames.commands.BGIsWaiting;
import de.webcode.bordergames.event.EventManager;
import de.webcode.bordergames.event.Listener;
import de.webcode.bordergames.event.impl.GameStartEvent;
import de.webcode.bordergames.game.Game;
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
    private Game game;

    @Override
    public void onEnable() {
        INSTANCE = this;

        this.filemanager = new Filemanager();
        this.eventListener = new Listener();
        this.game = new Game();

        registerCommands();
        registerEvents();

        this.threadManager = new ThreadManager();
    }

    private void registerCommands() {
        getCommand("bgplayercount").setExecutor(new BGGetPlayerCount());
        getCommand("bgiswaiting").setExecutor(new BGIsWaiting());
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();

        EventManager.register(eventListener);
        pm.registerEvents(eventListener, this);
    }

    public Game getGame() {
        return game;
    }

    public Filemanager getFilemanager() {
        return filemanager;
    }
}
