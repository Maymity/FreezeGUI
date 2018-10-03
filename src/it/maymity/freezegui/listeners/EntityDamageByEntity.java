package it.maymity.freezegui.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import it.maymity.freezegui.Utils;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntity implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
            if (event.getEntity() instanceof Player || event.getDamager() instanceof Player) {
                if (Utils.getInstance().getFreezeList().contains(event.getEntity()) || Utils.getInstance().getFreezeList().contains(event.getDamager())  ) {
                    event.setCancelled(true);
                }
            }
        }
    }