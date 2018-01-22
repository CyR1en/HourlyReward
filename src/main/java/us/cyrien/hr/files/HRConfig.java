package us.cyrien.hr.files;

import us.cyrien.mcutils.config.ConfigManager;

public class HRConfig extends BaseConfig {

    public HRConfig(ConfigManager configManager) {
        super(configManager, new String[]{"HourlyReward Configuration"});
    }

    @Override
    public void initialize() {
        String[] commentArr;
        if (config.get("Command") == null) {
            commentArr = new String[]{"What command do you want to execute",
                    "when a player have played an hour?",
                    "Player placeholder is %player"};
            config.set("Command", "/msg %player You have been playing for an hour!", commentArr);
            config.saveConfig();
        }
        if (config.get("Reward_Message") == null) {
            commentArr = new String[]{"Message that you want to",
                    "send to the player that have",
                    "been rewarded."};
            config.set("Reward_Message", "Congrats! You have been rewarded!", commentArr);
            config.saveConfig();
        }
        if(config.get("Message_Prefix") == null) {
            commentArr = new String[]{"Prefix that will appear",
                    "in front of each HourlyReward",
                    "messages.", "Leave a space at the end."};
            config.set("Message_Prefix", "&6[&3HourlyReward&6]&r ", commentArr);
            config.saveConfig();
        }
    }
}
