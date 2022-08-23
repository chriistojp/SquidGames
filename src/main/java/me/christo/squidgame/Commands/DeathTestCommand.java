package me.christo.squidgame.Commands;

import me.christo.squidgame.Managers.DeathAnimationManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeathTestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player) {
            Player p = (Player) sender;
            DeathAnimationManager.playDeathAnimation(p);
        }

        return false;
    }
}
