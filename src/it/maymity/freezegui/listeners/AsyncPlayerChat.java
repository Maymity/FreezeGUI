package it.maymity.freezegui.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import it.maymity.freezegui.Utils;

public class AsyncPlayerChat implements Listener {

    @EventHandler
    public void OnAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        if ((Utils.getInstance().getFreezeList().contains(event.getPlayer()))) {
            event.setCancelled(true);
        }
    }
}

