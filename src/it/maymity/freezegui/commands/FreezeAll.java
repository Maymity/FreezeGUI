package it.maymity.freezegui.commands;

import it.maymity.freezegui.gui.FreezeAllGui;
import it.maymity.freezegui.managers.MessagesManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import it.maymity.freezegui.Utils;

public class FreezeAll implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!Utils.getInstance().getConfig().getBoolean("settings.console_command")) {
            if (!(sender instanceof Player)) {
                MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getMessages().getString("messages.must_player"));
                return false;
            }
        }

        if (!sender.hasPermission("freezegui.freezeall")) {
            MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getMessages().getString("messages.no_permission"));
            return false;
        }

        if (args.length > 1) {
            MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getMessages().getString("messages.freezeall_usage"));
            return false;
        }

        if (Utils.getInstance().getFreezeallcheck()){
            MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getMessages().getString("messages.already_freezeall"));
            return false;
        }

        if (args.length == 0) {
            Utils.getInstance().setBoolFreezeAll(true);
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (!p.hasPermission("freezegui.use") && !p.hasPermission("freezegui.freezeall")) {
                    if (!sender.getName().equals(p.getName())) {
                        Utils.getInstance().correctPosition(p);
                        Utils.getInstance().setFreeze(p);
                        p.openInventory(FreezeAllGui.getInstance().getFreezeAllInventory());
                        MessagesManager.getInstance().sendMessage(p, Utils.getInstance().getMessages().getString("messages.notifyfreeze_message"));
                    }
                }
            }
            MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getMessages().getString("messages.freezeall"));
        }
        return true;
    }
}