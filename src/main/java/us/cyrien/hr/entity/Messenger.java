package us.cyrien.hr.entity;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import us.cyrien.hr.HourlyReward;

public class Messenger {

    public static void sendMessage(CommandSender cs, String message) {
        String prefix = HourlyReward.getInstance().getHRConfig().getString("Message_Prefix");
        prefix = prefix == null ? "" : prefix.isEmpty() ? "" : prefix;
        cs.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
    }
}
