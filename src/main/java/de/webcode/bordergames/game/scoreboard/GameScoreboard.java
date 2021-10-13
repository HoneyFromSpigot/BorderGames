package de.webcode.bordergames.game.scoreboard;

import de.webcode.bordergames.BorderGames;
import de.webcode.bordergames.game.Game;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameScoreboard extends ScoreboardBuilder{
    private Game game;

    public GameScoreboard(Game game) {
        super(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + "  BORDERGAMES");
        this.game = game;

        run();
    }

    @Override
    public void createScoreboard() {
        setScore(ChatColor.DARK_GRAY.toString(), 7);//
        setScore(ChatColor.DARK_PURPLE.toString(), 6);//Players:
        setScore(ChatColor.RED.toString(), 5);//
        setScore(ChatColor.DARK_RED.toString(), 4);//Mapsize:
        setScore(ChatColor.GREEN.toString(), 3);//
        setScore(ChatColor.DARK_BLUE.toString(), 2);//Time:
        setScore(ChatColor.BLACK.toString(), 1);//
    }

    @Override
    public void update() {

    }

    private void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (game.isWaiting() && !game.isStarted()) {
                    setScore(ChatColor.DARK_GRAY + "Spieleranzahl: " + game.getRemainingPlayerString(), 6);
                    setScore(ChatColor.RED + "Warte auf weitere Spieler...",4);
                } else if (!game.isWaiting() && !game.isStarted()) {
                    setScore(ChatColor.DARK_GRAY + "Spieleranzahl: " + game.getRemainingPlayerString(), 6);
                    setScore(ChatColor.RED + "Starte in: " + game.getStartTimeString(),4);
                } else if(game.isStarted()){
                    setScore(ChatColor.DARK_GRAY + "Spieleranzahl: §e" + game.getPlayers().size(), 6);
                    setScore(ChatColor.DARK_GRAY + "Map: §e" + game.getBorderSizeString(), 4);
                    setScore(ChatColor.DARK_GRAY + "Zeit: §e" + game.getTimeString(), 2);
                }
            }
        }.runTaskTimer(BorderGames.INSTANCE, 20, 20);
    }
}
