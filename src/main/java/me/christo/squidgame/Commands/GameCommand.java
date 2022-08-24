package me.christo.squidgame.Commands;


import me.christo.squidgame.Main;
import me.christo.squidgame.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("selector")) {
                if (p.hasPermission("squidgame.admin")) {

                    ItemStack item = new ItemStack(Material.STICK);
                    ItemMeta meta = item.getItemMeta();
                    meta.setDisplayName(Utils.color("&6Positions Selector"));
                    item.setItemMeta(meta);
                    p.getInventory().addItem(item);
                }
            }
            if(args[0].equalsIgnoreCase("setlocation")) {
                if(p.hasPermission("squidgame.admin")) {

                    Main.getInstance().getConfig().set("teleportLocation.location", p.getLocation());
                    p.sendMessage(Utils.message("locationSet"));



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
                            p.getInventory().clear();
                            ItemStack green = new ItemStack(Material.GREEN_DYE);
                            ItemMeta meta = (ItemMeta) green.getItemMeta();
                            meta.setDisplayName(Utils.color("&aGreen Light"));
                            green.setItemMeta(meta);

                            ItemStack red = new ItemStack(Material.RED_DYE);
                            ItemMeta redMeta = (ItemMeta) red.getItemMeta();
                            redMeta.setDisplayName(Utils.color("&cRed Light"));
                            red.setItemMeta(redMeta);

                            p.getInventory().addItem(green);
                            p.getInventory().addItem(red);
                            
                            
                            Bukkit.broadcastMessage(Utils.message("gameStarted"));
                            Main.setGameStatus(true);

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
