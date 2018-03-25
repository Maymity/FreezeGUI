package it.maymity.freezegui.listeners;

import it.maymity.freezegui.Main;
import it.maymity.freezegui.Utils;
import it.maymity.freezegui.gui.FreezeAllGui;
import it.maymity.freezegui.managers.MessagesManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

            if (Utils.getInstance().getConfig().getBoolean("settings.check_update_on_join")) {
                if (player.hasPermission("freezegui.checkupdate") && Utils.getInstance().getBoolUpdate()) {
                    MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getMessages().getString("messages.update_message"));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cDownload:&e " + Utils.getInstance().getUpdateLink()));
                }
            }

            if (player.hasPermission("freezegui.use") || player.hasPermission("freezegui.freezeall")) {
                player.sendMessage(" ");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eThis server uses the &c&lFREE VERSION &eof &7Freeze&bGUI"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&LPREMIUM VERSION:"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7https://www.spigotmc.org/resources/freezegui-premium-anydesk-gui-admitcheating-gui-confirmban-gui.49801/"));
            }

            if (Utils.getInstance().getFreezeallcheck()) {
                if (!player.hasPermission("freezegui.use") && !player.hasPermission("freezegui.freezeall")) {
                    Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                        Utils.getInstance().setFreeze(player);
                        player.openInventory(FreezeAllGui.getInstance().getFreezeAllInventory());
                    }, 5L);
                    MessagesManager.getInstance().sendMessage(player, Utils.getInstance().getMessages().getString("messages.notifyfreeze_message"));
                }
            }
        }
    }
