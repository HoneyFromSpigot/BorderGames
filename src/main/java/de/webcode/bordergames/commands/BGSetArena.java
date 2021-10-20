package de.webcode.bordergames.commands;

import de.webcode.bordergames.BorderGames;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class BGSetArena implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("§cDu musst diesen Command als Spieler ausführen!");
            return false;
        }

        Player player = (Player) sender;
        if (player.isOp()) {
            YamlConfiguration cfg = BorderGames.INSTANCE.getFilemanager().getCfg();
            Location loc = player.getLocation();

            String s = loc.getWorld().getName();
            int x = loc.getBlockX();
            int y = loc.getBlockY();
            int z = loc.getBlockZ();

            cfg.set("Locations.Game.Arena.World", s);
            cfg.set("Locations.Game.Arena.X", x);
            cfg.set("Locations.Game.Arena.Y", y);
            cfg.set("Locations.Game.Arena.Z", z);

            player.sendMessage("§aDu hast die §6Arena §agesetzt! §8(§e" + x + " " + y + " " + z + " :" + s + "§8)");

            BorderGames.INSTANCE.getFilemanager().saveFiles();
        }
        return false;
    }
}
