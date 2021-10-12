package de.webcode.bordergames.game.scoreboard;

import de.webcode.bordergames.BorderGames;
import de.webcode.bordergames.game.Game;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameScoreboard extends ScoreboardBuilder{
    private int socialId;
    private Game game;

    public GameScoreboard(Game game) {
        super(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + "  BORDERGAMES");
        this.game = game;
        socialId = 0;

        run();
    }

    @Override
    public void createScoreboard() {
        setScore(ChatColor.DARK_GRAY.toString(), 7);
        setScore(ChatColor.DARK_GRAY + "Spieleranzahl : " + "0 / 0", 6);
        setScore(ChatColor.GRAY.toString(), 4);
        setScore(ChatColor.DARK_GRAY + "Mapgröße: ", 3);
        setScore(ChatColor.RED.toString(), 2);
        setScore(ChatColor.DARK_GRAY + "Zeit: ", 0);
    }

    @Override
    public void update() {

    }

    private void run() {
        new BukkitRunnable() {
            @Override
            public void run() {

                switch (socialId) {
                    case 0:
                        setScore(ChatColor.DARK_GRAY + "Spieleranzahl: " + game.getRemainingPlayerString(), 6);
                        setScore(ChatColor.DARK_GRAY + "Mapgröße: §e" + game.getBorderSizeString(), 3);
                        break;
                }

                socialId++;

                if(socialId >= 3) {
                    socialId = 0;
                }

            }
        }.runTaskTimer(BorderGames.INSTANCE, 20, 20);
    }
}
