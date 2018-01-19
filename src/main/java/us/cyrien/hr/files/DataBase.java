package us.cyrien.hr.files;

import us.cyrien.mcutils.config.ConfigManager;

public class DataBase extends BaseConfig {

    public DataBase(ConfigManager configManager) {
        super(configManager, new String[]{"HourlyRate User Data"});
    }

    @Override
    public void initialize() {
    }

    public void set(String path, Object val) {
        config.set(path, val);
    }

    public Object get(String path) {
        return config.get(path);
    }
}