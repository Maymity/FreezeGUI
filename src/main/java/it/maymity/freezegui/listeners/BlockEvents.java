package it.maymity.freezegui.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import it.maymity.freezegui.managers.FreezeManager;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockEvents implements Listener{

    private final FreezeManager freeze = FreezeManager.getInstance();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (freeze.getFreezeList().contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (freeze.getFreezeList().contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }
}