package it.maymity.freezegui.listeners;

import it.maymity.freezegui.FreezeGUI;
import it.maymity.freezegui.managers.FreezeManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerEvents implements Listener{

    private final FreezeGUI plugin = FreezeGUI.getInstance();
    private final FreezeManager freeze = FreezeManager.getInstance();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("freezegui.staff") || player.hasPermission("freezegui.checkupdate")) {
            player.sendMessage(" ");
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eThis server uses the &c&lFREE VERSION &eof &7Freeze&bGUI"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&LPREMIUM VERSION:"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7https://www.spigotmc.org/resources/freezegui-premium-anydesk-gui-admitcheating-gui-confirmban-gui.49801/"));
        }

        if (!plugin.getConfiguration().getBoolean("settings.check_update_on_join"))
            return;

        if (!player.hasPermission("freezegui.checkupdate"))
            return;

        if (!freeze.isUpdate()){
            return;
        }

        plugin.getMessages().getMessage("messages.update_message").sendMessage(event.getPlayer());
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cDownload:&e " + plugin.getSpigotUpdater().getResourceURL()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (!freeze.getFreezeList().contains(event.getPlayer()))
            return;

        FreezeManager.getInstance().removeFreeze(event.getPlayer());
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), plugin.getMessages().getMessage("commands.slog_command").setVariable("%player", event.getPlayer().getName()).toString());
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("freezegui.staff"))
                plugin.getMessages().getMessage("messages.quit_message").setVariable("%player", event.getPlayer().getName()).sendMessage(player);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (freeze.getFreezeList().contains(event.getPlayer())) {
            event.getPlayer().setAllowFlight(true);
            event.getPlayer().teleport(event.getFrom());
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (freeze.getFreezeList().contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        if (freeze.getFreezeList().contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
        if (freeze.getFreezeList().contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }
}