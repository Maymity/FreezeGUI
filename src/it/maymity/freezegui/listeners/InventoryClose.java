package it.maymity.freezegui.listeners;

import it.maymity.freezegui.Main;
import it.maymity.freezegui.gui.FreezeAllGui;
import it.maymity.freezegui.gui.MainGui;
import it.maymity.freezegui.gui.SureGui;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import it.maymity.freezegui.Utils;
import org.bukkit.inventory.Inventory;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;

public class InventoryClose implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Inventory inventory = event.getInventory();

        if (Utils.getInstance().getFreezeList().contains(player)) {
            if (inventory != null) {
                if (inventory.getName() != null) {
                    if (!inventory.getName().isEmpty()) {
                        if (!Utils.getInstance().getFreezeallcheck()) {
                            if (inventory.getName().equals(MainGui.getInstance().getFreeze().getName()) || inventory.getName().equals(SureGui.getInstance().getSure().getName())) {
                                Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                                    if (player.getOpenInventory().getType() != InventoryType.CHEST)
                                        player.openInventory(inventory);
                                }, 5L);
                            }
                        }
                        else {
                            if (inventory.getName().equals(FreezeAllGui.getInstance().getFreezeAll().getName())) {
                                Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                                    player.openInventory(FreezeAllGui.getInstance().getFreezeAllInventory());
                                }, 5L);
                            }
                        }
                    }
                }
            }
        }
    }
}