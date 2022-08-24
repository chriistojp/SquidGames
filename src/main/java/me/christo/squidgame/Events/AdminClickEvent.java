package me.christo.squidgame.Events;


//import com.fastasyncworldedit.core.FaweAPI;
//import com.fastasyncworldedit.core.util.TaskManager;
//import com.sk89q.worldedit.EditSession;
//import com.sk89q.worldedit.EditSessionBuilder;
//import com.sk89q.worldedit.bukkit.BukkitAdapter;
//import com.sk89q.worldedit.bukkit.BukkitWorld;
//import com.sk89q.worldedit.function.pattern.RandomPattern;
//import com.sk89q.worldedit.regions.Region;
//import com.sk89q.worldedit.world.World;
//import com.sk89q.worldedit.world.block.BlockState;
//import com.sk89q.worldguard.WorldGuard;
//import com.sk89q.worldguard.protection.managers.RegionManager;
//import com.sk89q.worldguard.protection.regions.ProtectedRegion;
//import com.sk89q.worldguard.protection.regions.RegionContainer;
import me.christo.squidgame.Main;
import me.christo.squidgame.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.regex.Pattern;

public class AdminClickEvent implements Listener {


    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        try {
            if (e.getAction().equals(Action.LEFT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

                if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                    e.setCancelled(true);
                    if (e.getItem().getType().equals(Material.STICK)) {
                        if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Utils.color("&6Positions Selector"))) {
                            Main.getInstance().getConfig().set("locations.pos1", e.getClickedBlock().getLocation());
                            e.getPlayer().sendMessage(Utils.message("positionOneSet"));
                        }
                    }
                }
                if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    if (e.getItem().getType().equals(Material.STICK)) {
                        if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Utils.color("&6Positions Selector"))) {
                            Main.getInstance().getConfig().set("locations.pos2", e.getClickedBlock().getLocation());
                            e.getPlayer().sendMessage(Utils.message("positionTwoSet"));
                        }
                    }
                }


            }
            System.out.println("1");
            if (Main.getGameStatus()) {
                System.out.println("2");
                System.out.println(e.getAction());
                if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

                    System.out.println("yes");
                    try {


                        if (e.getItem().getType() == Material.GREEN_DYE && e.getItem().getItemMeta().getDisplayName().equals(Utils.color(
                                "&aGreen Light"))) {

                            changeColor(Main.getInstance().getConfig().getLocation("locations.pos1"), Main.getInstance().getConfig().getLocation("locations.pos2"), Material.GREEN_TERRACOTTA);
                            Bukkit.broadcastMessage(Utils.message("greenLight"));
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "blockclock start squidgame");

                            Main.setRedStatus(false);
                            //code here
                        }
                        if (e.getItem().getType() == Material.RED_DYE && e.getItem().getItemMeta().getDisplayName().equals(Utils.color(
                                "&cRed Light"))) {

                            changeColor(Main.getInstance().getConfig().getLocation("locations.pos1"), Main.getInstance().getConfig().getLocation("locations.pos2"), Material.RED_TERRACOTTA);
                            Bukkit.broadcastMessage(Utils.message("redLight"));
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "blockclock stop squidgame");

                            Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                                Main.setRedStatus(true);
                            }, 20);

                        }
                    } catch (NullPointerException exc) {

                    }
                }
            }
        } catch (NullPointerException exception) {
        }
        }


        private void changeColor(Location start, Location end, Material m) {
            int topBlockX = (start.getBlockX() < end.getBlockX() ? end.getBlockX() : start.getBlockX());
            int bottomBlockX = (start.getBlockX() > end.getBlockX() ? end.getBlockX() : start.getBlockX());

            int topBlockY = (start.getBlockY() < end.getBlockY() ? end.getBlockY() : start.getBlockY());
            int bottomBlockY = (start.getBlockY() > end.getBlockY() ? end.getBlockY() : start.getBlockY());

            int topBlockZ = (start.getBlockZ() < end.getBlockZ() ? end.getBlockZ() : start.getBlockZ());
            int bottomBlockZ = (start.getBlockZ() > end.getBlockZ() ? end.getBlockZ() : start.getBlockZ());

            for(int x = bottomBlockX; x <= topBlockX; x++)
            {
                for(int z = bottomBlockZ; z <= topBlockZ; z++)
                {
                    for(int y = bottomBlockY; y <= topBlockY; y++)
                    {
                        Block block = start.getWorld().getBlockAt(x, y, z);
                        block.setType(m);
                    }
                }
            }
        }

    }
