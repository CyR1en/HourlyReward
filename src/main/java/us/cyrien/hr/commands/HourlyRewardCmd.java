package us.cyrien.hr.commands;

import org.bukkit.command.CommandSender;
import us.cyrien.hr.HourlyReward;
import us.cyrien.hr.entity.Messenger;
import us.cyrien.mcutils.annotations.Command;
import us.cyrien.mcutils.annotations.Sender;

public class HourlyRewardCmd {
    @Command(aliases = {"hourlyreward", "hr"}, usage = "/hourlyreward reload", desc = "Default HourlyReward commands")
    public void onCommand(@Sender CommandSender cs, String arg) {
        if(arg == null) {
            Messenger.sendMessage(cs, "Please provide an argument for the command!");
            return;
        }
        if(arg.equalsIgnoreCase("reload")) {
            HourlyReward.getInstance().getHRConfig().reloadConfig();
            Messenger.sendMessage(cs, "&aConfiguration have been successfully reloaded!");
        }
    }
}
