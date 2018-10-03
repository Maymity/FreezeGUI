package it.maymity.freezegui.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import it.maymity.freezegui.Utils;

public class PlayerMove implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (Utils.getInstance().getFreezeList().contains(event.getPlayer())) {
                event.getPlayer().setAllowFlight(true);
                event.getPlayer().teleport(event.getFrom());
            }
        }
    }