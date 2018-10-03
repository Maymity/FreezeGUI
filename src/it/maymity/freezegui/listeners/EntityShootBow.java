package it.maymity.freezegui.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import it.maymity.freezegui.Utils;
import org.bukkit.event.entity.EntityShootBowEvent;

public class EntityShootBow implements Listener {

    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            if (Utils.getInstance().getFreezeList().contains(event.getEntity())) {
                event.setCancelled(true);
            }
        }
    }
}
