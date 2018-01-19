package us.cyrien.hr.entity;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import us.cyrien.hr.HourlyReward;
import us.cyrien.mcutils.logger.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TimeLogger {

    private HourlyReward hourlyReward;
    private Map<UUID, Long> loginTime;

    public TimeLogger(HourlyReward hourlyReward) {
        this.hourlyReward = hourlyReward;
        loginTime = new HashMap<>();
    }

    public void login(Player player) {
        long curr = System.currentTimeMillis();
        loginTime.put(player.getUniqueId(), curr);
        //return curr;
    }

    public void logout(Player player) {
        //long diff = currSessionLength(player);
        loginTime.remove(player.getUniqueId());
        //return diff;
    }

    public long currSessionLength(Player p) {
        return calculateTimeDifference(p, System.currentTimeMillis());
    }

    public void saveAll() {
        loginTime.forEach((uuid, aLong) -> {
            long curr = currSessionLength(Bukkit.getPlayer(uuid));
            long saved = hourlyReward.getDataBase().getConfig().getInt(uuid.toString());
            hourlyReward.getDataBase().getConfig().set(uuid.toString(), saved + curr);
            hourlyReward.getDataBase().getConfig().saveConfig();
            hourlyReward.getDataBase().getConfig().reloadConfig();
        });
        Logger.info("Saved all current player session!");
        loginTime = null;
    }

    public void reset(Player p) {
        login(p);
    }

    private long calculateTimeDifference(Player p, long time) {
        return time - loginTime.get(p.getUniqueId());
    }
}
