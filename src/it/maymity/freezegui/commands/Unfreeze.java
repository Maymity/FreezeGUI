package it.maymity.freezegui.commands;

import it.maymity.freezegui.managers.MessagesManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import it.maymity.freezegui.Utils;

public class Unfreeze implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!Utils.getInstance().getConfig().getBoolean("settings.console_command")) {
            if (!(sender instanceof Player)) {
                MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getMessages().getString("messages.must_player"));
                return false;
            }
        }

        if (!sender.hasPermission("freezegui.use")) {
            MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getMessages().getString("messages.no_permission"));
            return false;
        }

        if (args.length == 0 || args.length > 1) {
            MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getMessages().getString("messages.unfreeze_usage"));
            return false;
        }

        if (args.length == 1) {
            Player target = Bukkit.getServer().getPlayerExact(args[0]);

            if (!(target != null)) {
                MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getMessages().getString("messages.player_not_found"));
                return false;
            }

            if (target.hasPermission("freezegui.use")){
                MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getMessages().getString("messages.no_unfreeze_player"));
                return false;
            }
            if (Utils.getInstance().getFreezeList().contains(target)) {
                MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getMessages().getString("messages.unfreeze_message").replaceAll("%player%", target.getName()));
                Utils.getInstance().removeFreeze(target);
                target.closeInventory();
                target.setAllowFlight(false);
                MessagesManager.getInstance().sendMessage(target, Utils.getInstance().getMessages().getString("messages.notifyunfreeze_message"));
            }
            else
                MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getMessages().getString("messages.no_freeze"));
        }
        return true;
    }
}