package me.christo.squidgame;

import me.christo.squidgame.Commands.GameCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Main extends JavaPlugin {

    public static Main instance;
    public static List<Player> gamePlayerList;
    public static boolean gameStatus = false;

    @Override
    public void onEnable() {
        // Plugin startup logic


        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists())
            saveDefaultConfig();
        instance = this;
        gamePlayerList = new ArrayList<>();
        getCommand("game").setExecutor(new GameCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static boolean getGameStatus() {
        return gameStatus;
    }

    public static List<Player> getGamePlayerList() {
        return gamePlayerList;
    }
    public static Main getInstance() {
        return instance;
    }

}
