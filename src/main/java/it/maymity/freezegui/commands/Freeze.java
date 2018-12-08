package it.maymity.freezegui.commands;

import it.maymity.freezegui.managers.FreezeManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import it.maymity.freezegui.FreezeGUI;

public class Freeze implements CommandExecutor {

    private final FreezeGUI plugin = FreezeGUI.getInstance();
    private final FreezeManager freeze = FreezeManager.getInstance();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!sender.hasPermission("freezegui.commands.freeze")) {
            plugin.getMessages().getMessage("messages.no_permission").sendMessage(sender);
            return false;
        }

        if (args.length == 0 || args.length > 1) {
            plugin.getMessages().getMessage("messages.freeze_usage").sendMessage(sender);
            return false;
        }

        if (!(sender instanceof Player)) {
            plugin.getMessages().getMessage("messages.must_player").sendMessage(sender);
            return false;
        }


        if (args.length == 1) {
            Player target = Bukkit.getPlayerExact(args[0]);

            if (target == null) {
                plugin.getMessages().getMessage("messages.player_not_found").sendMessage(sender);
                return false;
            }

            if (sender.getName().equals(target.getName())) {
                plugin.getMessages().getMessage("messages.no_freeze_yourself").sendMessage(sender);
                return false;
            }

            if (target.hasPermission("freezegui.staff")) {
                plugin.getMessages().getMessage("messages.no_freeze_player").sendMessage(sender);
                return false;
            }

            if (FreezeManager.getInstance().getFreezeList().contains(target)) {
                plugin.getMessages().getMessage("messages.already_freeze").sendMessage(sender);
                return false;
            }

            freeze.correctPosition(target);
            plugin.getMessages().getMessage("messages.freeze_message").setVariable("player", target.getName()).sendMessage(sender);
            target.closeInventory();
            freeze.setFreeze(target);
            plugin.getFreezeMenu().openInventory(target);
            plugin.getMessages().getMessage("messages.notifyfreeze_message").sendMessage(target);
        }
        return true;
    }
}