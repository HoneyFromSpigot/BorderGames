package de.webcode.bordergames.commands;

import de.webcode.bordergames.BorderGames;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BGStartGameNow implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.isOp()){
            sender.sendMessage("§cDu hast nicht genug Rechte, um diesen Befehl zu benutzen!");
            return false;
        }

        BorderGames.INSTANCE.getGame().startGame();
        sender.sendMessage("§eDu hast das Spiel gestartet! Auf diese Art und Weise kann es zu einigen Bugs kommen!");
        return true;
    }
}
