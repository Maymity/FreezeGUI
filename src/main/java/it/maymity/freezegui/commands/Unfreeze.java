package it.maymity.freezegui.commands;

import it.maymity.freezegui.FreezeGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import it.maymity.freezegui.managers.FreezeManager;

public class Unfreeze implements CommandExecutor {

    private final FreezeGUI plugin = FreezeGUI.getInstance();
    private final FreezeManager freeze = FreezeManager.getInstance();
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!sender.hasPermission("freezegui.commands.unfreeze")) {
            plugin.getMessages().getMessage("messages.no_permission").sendMessage(sender);
            return false;
        }

        if (!(sender instanceof Player)) {
            plugin.getMessages().getMessage("messages.must_player").sendMessage(sender);
            return false;
        }

        if (args.length == 0 || args.length > 1) {
            plugin.getMessages().getMessage("messages.unfreeze_usage").sendMessage(sender);
            return false;
        }

        if (args.length == 1) {
            Player target = Bukkit.getServer().getPlayerExact(args[0]);

            if (target == null) {
                plugin.getMessages().getMessage("messages.player_not_found").sendMessage(sender);
                return false;
            }

            if (target.hasPermission("freezegui.staff")){
                plugin.getMessages().getMessage("messages.no_unfreeze_player").sendMessage(sender);
                return false;
            }

            if (freeze.getFreezeList().contains(target)) {
                plugin.getMessages().getMessage("messages.unfreeze_message").setVariable("player", target.getName()).sendMessage(sender);
                freeze.removeFreeze(target);
                plugin.getMessages().getMessage("messages.notifyunfreeze_message").sendMessage(target);
                target.closeInventory();
                target.setAllowFlight(false);
            }
            else
                plugin.getMessages().getMessage("messages.no_freeze").sendMessage(sender);
        }
        return true;
    }
}