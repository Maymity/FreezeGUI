package it.maymity.freezegui.listeners;

import it.maymity.freezegui.FreezeGUI;
import org.bukkit.inventory.ItemStack;
import it.maymity.freezegui.managers.FreezeManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;

public class InventoryEvents implements Listener {

    private final FreezeManager freeze = FreezeManager.getInstance();
    private final FreezeGUI plugin = FreezeGUI.getInstance();

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Inventory inventory = event.getInventory();

        if (freeze.getFreezeList().contains(player)) {
            if (!inventory.getType().equals(InventoryType.ANVIL)) {
                if (inventory.getName().equals(plugin.getFreezeMenu().getTitle().toString())) {
                    Bukkit.getScheduler().runTaskLater(plugin, () -> {
                        if (player.getOpenInventory().getType() != InventoryType.CHEST)
                            plugin.getFreezeMenu().openInventory(player);
                        }, 5L);
                }
                else if (inventory.getName().equals(plugin.getSureMenu().getTitle().toString())){
                    Bukkit.getScheduler().runTaskLater(plugin, () -> {
                        if (player.getOpenInventory().getType() != InventoryType.CHEST)
                            plugin.getSureMenu().openInventory(player);
                    }, 5L);
                }
            }
        }
    }
}