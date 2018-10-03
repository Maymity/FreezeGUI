package it.maymity.freezegui.listeners;

import it.maymity.freezegui.gui.MainGui;
import it.maymity.freezegui.gui.SureGui;
import it.maymity.freezegui.managers.MessagesManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import it.maymity.freezegui.Utils;

public class InventoryClick implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack clicked = event.getCurrentItem();
        Inventory inventory = event.getInventory();

        if (Utils.getInstance().getFreezeList().contains(player)) {
            if (inventory != null) {
                if (inventory.getName() != null) {
                    if (!inventory.getName().isEmpty()) {
                        if (clicked != null) {
                            if (clicked.getType() != null) {
                                if (!Utils.getInstance().getFreezeallcheck()) {
                                    if (inventory.getName().equals(MainGui.getInstance().getFreeze().getName())) {
                                        if ((clicked.getType() == Material.valueOf(Utils.getInstance().getConfig().getString("freezegui.Items." + 2 + ".material").toUpperCase())) && (clicked.getDurability() == Utils.getInstance().getConfig().getInt("freezegui.Items." + 2 + ".damage")))
                                            player.openInventory(SureGui.getInstance().getSureInventory());
                                        else if ((clicked.getType() == Material.valueOf(Utils.getInstance().getConfig().getString("freezegui.Items." + 1 + ".material").toUpperCase())) && (clicked.getDurability() == Utils.getInstance().getConfig().getInt("freezegui.Items." + 2 + ".damage"))) {
                                            for (Player p : Bukkit.getOnlinePlayers()) {
                                                if (p.hasPermission("freezegui.use"))
                                                    MessagesManager.getInstance().sendMessage(p, Utils.getInstance().getMessages().getString("messages.entering_on_teamspeak").replaceAll("%player%", player.getName()));
                                            }
                                            MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getMessages().getString("messages.teamspeak_allert_sent"));
                                        }
                                        else if ((clicked.getType() == Material.valueOf(Utils.getInstance().getConfig().getString("freezegui.Items." + 3 + ".material").toUpperCase())) && (clicked.getDurability() == Utils.getInstance().getConfig().getInt("freezegui.Items." + 3 + ".damage"))) {
                                            for (Player p : Bukkit.getOnlinePlayers()) {
                                                if (p.hasPermission("freezegui.use"))
                                                    MessagesManager.getInstance().sendMessage(p, Utils.getInstance().getMessages().getString("messages.entering_on_teamspeak").replaceAll("%player%", player.getName()));
                                            }
                                            MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getMessages().getString("messages.teamspeak_allert_sent"));
                                        }
                                    }
                                    else if (inventory.getName().equals(SureGui.getInstance().getSure().getName())) {
                                        if ((clicked.getType() == Material.valueOf(Utils.getInstance().getConfig().getString("suregui.Items." + 1 + ".material").toUpperCase())) && (clicked.getDurability() == Utils.getInstance().getConfig().getInt("suregui.Items." + 1 + ".damage"))) {
                                            Utils.getInstance().removeFreeze(player);
                                            player.closeInventory();
                                            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), Utils.getInstance().getConfig().getString("commands.punish_command").replaceAll("%player%", (event.getWhoClicked()).getName()));
                                        }
                                        else if ((clicked.getType() == Material.valueOf(Utils.getInstance().getConfig().getString("suregui.Items." + 2 + ".material").toUpperCase())) && (clicked.getDurability() == Utils.getInstance().getConfig().getInt("suregui.Items." + 2 + ".damage")))
                                            player.openInventory(MainGui.getInstance().getFreezeInventory());
                                    }
                                }
                                else {
                                    if ((clicked.getType() == Material.valueOf(Utils.getInstance().getConfig().getString("freezeallgui.Items." + 1 + ".material").toUpperCase())) && (clicked.getDurability() == Utils.getInstance().getConfig().getInt("freezeallgui.Items." + 1 + ".damage")))
                                        MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getMessages().getString("messages.freezeall_item_click"));
                                }
                                event.setCancelled(true);
                            }
                        }
                    }
                }
            }
        }
    }
}