package me.christo.squidgame.Events;


import me.christo.squidgame.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveEvent implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {

        if(Main.getGameStatus()) {
            if(Main.getGamePlayerList().contains(e.getPlayer())) {
                Location location = e.getPlayer().getLocation();
                double x = location.getX();
                double y = location.getY() - 1;
                double z = location.getZ();
                Location newLocation = new Location(e.getPlayer().getWorld(), x, y, z);
                if(location.getBlock().getType() == Material.RED_TERRACOTTA) {
                    //death aminmation

                }
            }
        }

    }




}
