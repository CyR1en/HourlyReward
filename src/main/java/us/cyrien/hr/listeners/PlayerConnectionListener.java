package us.cyrien.hr.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import us.cyrien.hr.HourlyReward;
import us.cyrien.hr.entity.TimeLogger;

public class PlayerConnectionListener implements Listener {

    private TimeLogger timeLogger;
    private HourlyReward hr;

    public PlayerConnectionListener(HourlyReward hr, TimeLogger timeLogger) {
        this.timeLogger = timeLogger;
        this.hr = hr;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        timeLogger.login(e.getPlayer());
        if(hr.getDataBase().get(e.getPlayer().getUniqueId().toString()) == null) {
            hr.getDataBase().set(e.getPlayer().getUniqueId().toString(), 0L);
            hr.getDataBase().getConfig().saveConfig();
            hr.getDataBase().getConfig().reloadConfig();
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        long curr = timeLogger.currSessionLength(e.getPlayer());
        long saved = hr.getDataBase().getConfig().getInt(e.getPlayer().getUniqueId().toString());
        hr.getDataBase().set(e.getPlayer().getUniqueId().toString(), curr + saved);
        hr.getDataBase().getConfig().saveConfig();
        hr.getDataBase().getConfig().reloadConfig();
        timeLogger.logout(e.getPlayer());
    }
}
