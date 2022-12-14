package me.christo.squidgame;

//import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import me.christo.squidgame.Commands.DeathTestCommand;
import me.christo.squidgame.Commands.GameCommand;
import me.christo.squidgame.Events.AdminClickEvent;
import me.christo.squidgame.Events.MoveEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Main extends JavaPlugin {

    public static Main instance;
    public static List<Player> gamePlayerList;
    public static boolean gameStatus = false;
    public static boolean redStatus = false;

    @Override
    public void onEnable() {
        // Plugin startup logic


        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists())
            saveDefaultConfig();
        instance = this;
        gamePlayerList = new ArrayList<>();
        getCommand("game").setExecutor(new GameCommand());
        getCommand("deathtest").setExecutor(new DeathTestCommand());

        Bukkit.getPluginManager().registerEvents(new AdminClickEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MoveEvent(), this);

    }

    @Override
    public void onDisable() {
        File file = new File(getDataFolder(), "config.yml");
        try {
            Main.getInstance().getConfig().save(file);// Plugin shutdown logic
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static boolean getGameStatus() {
        return gameStatus;
    }
    public static void setGameStatus(boolean b) {
        gameStatus = b;
    }
    public static boolean getRedStatus() {
        return redStatus;
    }
    public static void setRedStatus(boolean b) {
        redStatus = b;
    }

    public static List<Player> getGamePlayerList() {
        return gamePlayerList;
    }
    public static Main getInstance() {
        return instance;
    }

 //   public static WorldGuardPlugin getWorldGuard() {
      //  Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");

      //  return (WorldGuardPlugin) plugin;
  //  }

}
