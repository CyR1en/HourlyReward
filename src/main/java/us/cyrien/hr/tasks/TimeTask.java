package us.cyrien.hr.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import us.cyrien.hr.HourlyReward;
import us.cyrien.hr.events.PlayerRewardEvent;

import java.util.concurrent.TimeUnit;

public class TimeTask extends BukkitRunnable {

    private HourlyReward hourlyReward;

    public TimeTask(HourlyReward hourlyReward) {
        this.hourlyReward = hourlyReward;
    }

    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            long saved = getSavedTime(p);
            long tCurrSession = hourlyReward.getTimeLogger().currSessionLength(p);
            long total = TimeUnit.MILLISECONDS.toSeconds(saved + tCurrSession);
            if(total >= 3600 ) {
                PlayerRewardEvent onReward = new PlayerRewardEvent(p);
                Bukkit.getPluginManager().callEvent(onReward);

            }
        }
    }

    private long getSavedTime(Player p) {
        if (hourlyReward.getDataBase().get(p.getUniqueId().toString()) == null)
            return 0;
        else {
            hourlyReward.getDataBase().getConfig().reloadConfig();
            return hourlyReward.getDataBase().getConfig().getInt(p.getUniqueId().toString());
        }
    }
}
