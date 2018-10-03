package it.maymity.freezegui.commands;

import it.maymity.freezegui.gui.MainGui;
import it.maymity.freezegui.managers.MessagesManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import it.maymity.freezegui.Utils;

public class Freeze implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!Utils.getInstance().getConfig().getBoolean("settings.console_command")) {
            if (!(sender instanceof Player)) {
                MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getMessages().getString("messages.must_player"));
                return false;
            }
        }

        if (!sender.hasPermission("freezegui.use")){
            MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getMessages().getString("messages.no_permission"));
            return false;
        }

        if (args.length == 0 || args.length > 1){
            MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getMessages().getString("messages.freeze_usage"));
            return false;
        }

        if (args.length == 1) {
            Player target = Bukkit.getPlayerExact(args[0]);

            if (!(target != null)){
                MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getMessages().getString("messages.player_not_found"));
                return false;
            }

            if (sender.getName().equals(target.getName())) {
                MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getMessages().getString("messages.no_freeze_yourself"));
                return false;
            }

            if (target.hasPermission("freezegui.use")) {
                MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getMessages().getString("messages.no_freeze_player"));
                return false;
            }

            if (Utils.getInstance().getFreezeList().contains(target)) {
                MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getMessages().getString("messages.already_freeze"));
                return false;
            }

            MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getMessages().getString("messages.freeze_message").replaceAll("%player%", target.getName()));
            target.closeInventory();
            Utils.getInstance().correctPosition(target);
            Utils.getInstance().setFreeze(target);
            target.openInventory(MainGui.getInstance().getFreezeInventory());
            MessagesManager.getInstance().sendMessage(target, Utils.getInstance().getMessages().getString("messages.notifyfreeze_message"));
        }
        return true;
    }
}