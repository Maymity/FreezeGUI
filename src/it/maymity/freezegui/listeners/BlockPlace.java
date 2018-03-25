package it.maymity.freezegui.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import it.maymity.freezegui.Utils;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (Utils.getInstance().getFreezeList().contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }
}
