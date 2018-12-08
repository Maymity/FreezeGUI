package it.maymity.freezegui.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import it.maymity.freezegui.managers.FreezeManager;

public class ChatEvents implements Listener {

    private final FreezeManager freeze = FreezeManager.getInstance();

    @EventHandler
    public void OnAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        if (freeze.getFreezeList().contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }
}