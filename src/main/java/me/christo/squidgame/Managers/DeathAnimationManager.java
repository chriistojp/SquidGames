package me.christo.squidgame.Managers;

import me.christo.squidgame.Main;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.*;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class DeathAnimationManager {

    public static void playDeathAnimation(Player p) {

//        Pig pig = (Pig) p.getWorld().spawnEntity(p.getLocation(), EntityType.PIG);
//
//        pig.addPassenger(p);
//       // pig.setInvisible(true);
//        pig.setGravity(false);
//
//
//
//
//
//       // for(int i = 0; i < 100; i++) {
//            Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {
//                double y = pig.getLocation().getY() + .1;
//                Location loc = new Location(p.getWorld(), pig.getLocation().getX(), y, pig.getLocation().getZ());
//                System.out.println(pig.getLocation().getY());
//                pig.removePassenger(p);
//                pig.teleport(loc);
//                pig.addPassenger(p);
//               // p.teleport(loc);
//               // pig.addPassenger(p);
//                System.out.println("hi");
//            }, 1, 1);
//      //  }
        spawnFireworks(p.getLocation(), 1);
        Pig as = (Pig) p.getWorld().spawnEntity(p.getLocation(), EntityType.PIG);
        as.setInvisible(true);
        Location changed = as.getLocation().clone();
        Vector v = new Vector(0, 2, 0);
        as.addPassenger(p);
        p.setVelocity(v);
        as.setVelocity(v);
        as.setInvulnerable(true);
        for(String s : Main.getInstance().getConfig().getStringList("deathCommands")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s);
        }
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            as.removePassenger(p);
            p.damage(100);


        }, 30);


    }

    public static void spawnFireworks(Location location, int amount){
        Location loc = location;
        Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();

        fwm.setPower(2);
        fwm.addEffect(FireworkEffect.builder().withColor(Color.RED).flicker(false).build());

        fw.setFireworkMeta(fwm);
        fw.detonate();

        for(int i = 0;i<amount; i++){
            Firework fw2 = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
            fw2.setFireworkMeta(fwm);
        }
    }

}
