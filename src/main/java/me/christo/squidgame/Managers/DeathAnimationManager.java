package me.christo.squidgame.Managers;

import me.christo.squidgame.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathAnimationManager {

    public static void playDeathAnimation(Player p) {

        Pig pig = (Pig) p.getWorld().spawnEntity(p.getLocation(), EntityType.PIG);
        pig.addPassenger(p);
        pig.setInvisible(true);



        for(int i = 0; i < 100; i++) {
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                Location loc = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + .1, p.getLocation().getZ());
                pig.teleport(loc);
            }, 5);
        }

    }

}
