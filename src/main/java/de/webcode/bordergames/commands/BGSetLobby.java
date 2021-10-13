package de.webcode.bordergames.commands;

import de.webcode.bordergames.BorderGames;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class BGSetLobby implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("§cDu musst diesen Command als Spieler ausführen!");
            return false;
        }

        Player player = (Player) sender;

        if (player.isOp()) {
            Location loc = player.getLocation();
            YamlConfiguration cfg = BorderGames.INSTANCE.getFilemanager().getCfg();

            String s = loc.getWorld().getName();
            int x = loc.getBlockX();
            int y = loc.getBlockY();
            int z = loc.getBlockZ();

            cfg.set("Locations.Game.Lobby.World", s);
            cfg.set("Locations.Game.Lobby.X", x);
            cfg.set("Locations.Game.Lobby.Y", y);
            cfg.set("Locations.Game.Lobby.Z", z);

            BorderGames.INSTANCE.getFilemanager().saveFiles();

            player.sendMessage("§aDu hast die §6Lobby §agesetzt! §8(§e" + x + " " + y + " " + z + " :" + s + "§8)");
            return true;
        }
        return false;
    }
}
