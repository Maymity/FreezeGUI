package it.maymity.freezegui.listeners;

import it.maymity.freezegui.managers.FreezeManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;

public class EntityEvents implements Listener {

    private final FreezeManager freeze = FreezeManager.getInstance();

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player || event.getDamager() instanceof Player) {
            if (freeze.getFreezeList().contains(event.getEntity()) || freeze.getFreezeList().contains(event.getDamager())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
            if (freeze.getFreezeList().contains(event.getEntity())) {
                event.setCancelled(true);
            }
        }
    }
}