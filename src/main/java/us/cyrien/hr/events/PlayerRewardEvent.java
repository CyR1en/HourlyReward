package us.cyrien.hr.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerRewardEvent extends PlayerEvent {

    private static final HandlerList handlerList = new HandlerList();

    public PlayerRewardEvent(Player player) {
        super(player);
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
