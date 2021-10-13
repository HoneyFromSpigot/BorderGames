package de.webcode.bordergames.commands;

import de.webcode.bordergames.BorderGames;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BGIsWaiting implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("Waiting: " + BorderGames.INSTANCE.getGame().isWaiting());
        return false;
    }
}
