package us.cyrien.hr.files;

import us.cyrien.mcutils.config.Config;
import us.cyrien.mcutils.config.ConfigManager;

import java.io.File;

public abstract class BaseConfig {

    private final String[] header;
    private ConfigManager configManager;
    protected Config config;

    public BaseConfig(ConfigManager configManager, String[] header) {
        this.configManager = configManager;
        this.header = header;
    }

    public boolean init() {
        File f = configManager.getConfigFile(this.getClass().getSimpleName() + ".yml");
        if (!f.exists()) {
            config = configManager.getNewConfig(this.getClass().getSimpleName() + ".yml", header);
            initialize();
            return false;
        }
        config = configManager.getNewConfig(this.getClass().getSimpleName() + ".yml", header);
        initialize();
        return true;
    }

    public abstract void initialize();

    public Config getConfig() {
        return config;
    }
}
