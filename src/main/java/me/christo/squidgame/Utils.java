package me.christo.squidgame;


import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class Utils {


    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    public static String message(String path) {
        String message = Main.getInstance().getConfig().getString("messages." + path);

        return Utils.color(message);
    }

}
