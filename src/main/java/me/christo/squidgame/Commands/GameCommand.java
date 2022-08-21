package me.christo.squidgame.Commands;


import me.christo.squidgame.Main;
import me.christo.squidgame.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("setlocation")) {
                if(p.hasPermission("squidgame.admin")) {

                    Main.getInstance().getConfig().set("teleportLocation.location", p.getLocation());
                    p.sendMessage(Utils.message("messages.locationSet"));


                } else {
                    p.sendMessage(Utils.message("noPermission"));
                }
            }
        }

        if(args.length == 2) {
            if(args[0].equalsIgnoreCase("join")) {
                if (args[1].equalsIgnoreCase("squidgame")) {
                    if (Main.getGameStatus() == false) {

                        Location loc = Main.getInstance().getConfig().getLocation("teleportLocation.location");


                        Main.getGamePlayerList().add(p);
                        p.teleport(loc);

                        p.sendMessage(Utils.message("joinSuccess"));

                    } else {
                        p.sendMessage(Utils.message("alreadyStarted"));
                    }
                }
            }
            if(args[0].equalsIgnoreCase("start")) {
                if(args[1].equalsIgnoreCase("squidgame")) {
                    if (p.hasPermission("squidgame.admin")) {
                        if (Main.getGameStatus() == false) {

                            Location loc = Main.getInstance().getConfig().getLocation("teleportLocation.location");


                            Main.getGamePlayerList().add(p);
                            p.teleport(loc);
                            Bukkit.broadcastMessage(Utils.message("gameStarted"));

                        } else {
                            p.sendMessage(Utils.message("alreadyRunning"));
                        }


                    } else {
                        p.sendMessage(Utils.message("noPermission"));
                    }
                }
            }
        }



        return false;
    }
}
