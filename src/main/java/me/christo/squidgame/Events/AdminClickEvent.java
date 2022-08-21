package me.christo.squidgame.Events;


import me.christo.squidgame.Main;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class AdminClickEvent implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (Main.getGameStatus()) {
            if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
                if(e.getItem().getType() == Material.LIME_DYE) {

                }
            }
        }
    }


}
