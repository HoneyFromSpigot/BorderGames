package de.webcode.bordergames.utils;

import de.webcode.bordergames.BorderGames;
import de.webcode.bordergames.event.impl.Second1Event;
import de.webcode.bordergames.event.impl.Second5Event;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

public class ThreadManager {
    private BukkitTask r1, r5;

    public ThreadManager(){
        r1 = new BukkitRunnable() {
            @Override
            public void run() {
                new Second1Event().call();
            }
        }.runTaskTimer(BorderGames.INSTANCE, 0, 20);

        r5 = new BukkitRunnable() {
            @Override
            public void run() {
                new Second5Event().call();
            }
        }.runTaskTimer(BorderGames.INSTANCE, 0, 100);
    }

    public void cancelAll() {
        r1.cancel();
        r5.cancel();
    }
}
