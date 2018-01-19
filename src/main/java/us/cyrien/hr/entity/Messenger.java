package us.cyrien.hr.entity;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Messenger {

    private static final String PREFIX = "&6[&3HourlyReward&6]&r ";

    public static void sendMessage(CommandSender cs, String message) {
        cs.sendMessage(ChatColor.translateAlternateColorCodes('&', PREFIX + message));
    }
}
