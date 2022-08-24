package me.christo.squidgame.Events;


import me.christo.squidgame.Main;
import me.christo.squidgame.Managers.DeathAnimationManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveEvent implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {

        if(Main.getGameStatus()) {
            System.out.println(1);
            if(Main.getGamePlayerList().contains(e.getPlayer())) {
                System.out.println(2);
                Location location = e.getPlayer().getLocation();
                double x = location.getX();
                double y = location.getY() - 1;
                double z = location.getZ();
                Location newLocation = new Location(e.getPlayer().getWorld(), x, y, z);
                if(newLocation.getBlock().getType() == Material.RED_TERRACOTTA) {
                    if(Main.getRedStatus()) {
                        System.out.println(3);
                        Bukkit.broadcastMessage("dead");
                        //DeathAnimationManager.playDeathAnimation(e.getPlayer());
                        //execute death command
                        Main.getGamePlayerList().remove(e.getPlayer());

                    }

                }
            }
        }

    }




}
