package me.christo.squidgame.Events;


import com.fastasyncworldedit.core.FaweAPI;
import com.fastasyncworldedit.core.util.TaskManager;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.EditSessionBuilder;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.function.pattern.RandomPattern;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldedit.world.block.BlockState;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
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
        if (Main.getGameStatus()) {
            if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
                if (e.getItem().getType() == Material.LIME_DYE && e.getItem().getItemMeta().getDisplayName().equals(Utils.color(
                        "&aGreen Light"))) {

                    changeColor(e.getPlayer(), "green");
                    Bukkit.broadcastMessage(Utils.message("greenLight"));

                    //code here
                    if (e.getItem().getType() == Material.RED_DYE && e.getItem().getItemMeta().getDisplayName().equals(Utils.color(
                            "&cRed Light"))) {

                        changeColor(e.getPlayer(), "red");
                        Bukkit.broadcastMessage(Utils.message("redLight"));

                    }
                }
            }
        }
    }

    public void changeColor(Player p, String color) {

        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        World world = new BukkitWorld(Bukkit.getWorld(p.getWorld().getName()));
        RegionManager regions = container.get(world);
        ProtectedRegion r = regions.getRegion("squidgame");


        for (int x = r.getMinimumPoint().getBlockX(); x < r.getMaximumPoint().getBlockX() + 1; x++) {
            for (int y = r.getMinimumPoint().getBlockY(); y < r.getMaximumPoint().getBlockY()
                    + 1; y++) {
                for (int z = r.getMinimumPoint().getBlockZ(); z < r.getMaximumPoint().getBlockZ()
                        + 1; z++) {

                    if(color.equals("red")) {
                        p.getWorld().getBlockAt(new Location(p.getWorld(), x, y, z)).setType(Material.RED_TERRACOTTA);
                    }
                    if(color.equals("green")) {
                        p.getWorld().getBlockAt(new Location(p.getWorld(), x, y, z)).setType(Material.GREEN_TERRACOTTA);
                    }

                }
            }
        }




    }

}
