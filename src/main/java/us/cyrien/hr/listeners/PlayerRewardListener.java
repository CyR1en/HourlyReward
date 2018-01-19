package us.cyrien.hr.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import us.cyrien.hr.HourlyReward;
import us.cyrien.hr.entity.Messenger;
import us.cyrien.hr.entity.TimeLogger;
import us.cyrien.hr.events.PlayerRewardEvent;
import us.cyrien.mcutils.config.Config;

public class PlayerRewardListener implements Listener {

    private HourlyReward hourlyReward;
    private Config hrConfig;
    private TimeLogger timeLogger;

    public PlayerRewardListener(HourlyReward hourlyReward) {
        this.hourlyReward = hourlyReward;
        hrConfig = hourlyReward.getHRConfig();
        timeLogger = hourlyReward.getTimeLogger();
    }

    @EventHandler
    public void onReward(PlayerRewardEvent event) {
        hourlyReward.getDataBase().getConfig().set(event.getPlayer().getUniqueId().toString(), 0L);
        hourlyReward.getDataBase().getConfig().saveConfig();
        hourlyReward.getDataBase().getConfig().reloadConfig();
        timeLogger.reset(event.getPlayer());
        String command = parsePreCommand(event.getPlayer());
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
        String msg = hourlyReward.getHRConfig().getString("Reward_Message");
        msg = msg == null ? "You have been rewarded!" : msg;
        Messenger.sendMessage(event.getPlayer(), msg);
    }

    private String parsePreCommand(Player p) {
        String command = hrConfig.getString("Command").replaceAll("/", "");
        return command.replaceAll("%player", p.getName());
    }
}
