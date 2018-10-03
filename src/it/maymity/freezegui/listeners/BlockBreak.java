package it.maymity.freezegui.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import it.maymity.freezegui.Utils;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
            if (Utils.getInstance().getFreezeList().contains(event.getPlayer())) {
                event.setCancelled(true);
            }
        }
    }

