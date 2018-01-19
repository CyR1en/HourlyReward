package us.cyrien.hr;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import us.cyrien.hr.commands.HourlyRewardCmd;
import us.cyrien.hr.entity.TimeLogger;
import us.cyrien.hr.files.DataBase;
import us.cyrien.hr.files.HRConfig;
import us.cyrien.hr.listeners.PlayerConnectionListener;
import us.cyrien.hr.listeners.PlayerRewardListener;
import us.cyrien.hr.tasks.TimeTask;
import us.cyrien.mcutils.Frame;
import us.cyrien.mcutils.config.Config;
import us.cyrien.mcutils.config.ConfigManager;

public class HourlyReward extends JavaPlugin{

    private static HourlyReward instance;
    private DataBase dataBase;
    private HRConfig hrConfig;
    private TimeTask timeTask;
    private TimeLogger timeLogger;

    @Override
    public void onEnable() {
        super.onEnable();
        ConfigManager configManager = new ConfigManager(this);
        dataBase = new DataBase(configManager);
        dataBase.init();
        hrConfig = new HRConfig(configManager);
        hrConfig.init();
        timeTask = new TimeTask(this);
        timeLogger = new TimeLogger(this);
        Bukkit.getPluginManager().registerEvents(new PlayerConnectionListener(this, timeLogger), this);
        Bukkit.getPluginManager().registerEvents(new PlayerRewardListener(this), this);
        timeTask.runTaskTimer(this, 1, 20);
        instance = this;
        Frame.addModule(HourlyRewardCmd.class);
        Bukkit.getScheduler().runTaskLater(this, Frame::main, 1L);
    }

    @Override
    public void onDisable() {
        timeLogger.saveAll();
        try {
            timeTask.cancel();
        } catch (IllegalStateException ignore) {
        }

    }

    public TimeLogger getTimeLogger() {
        return timeLogger;
    }

    public DataBase getDataBase() {
        return dataBase;
    }

    public static HourlyReward getInstance() {
        return instance;
    }

    public Config getHRConfig() {
        return hrConfig.getConfig();
    }
}
