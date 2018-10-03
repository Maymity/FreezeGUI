package it.maymity.freezegui.commands;

import it.maymity.freezegui.Main;
import it.maymity.freezegui.managers.MessagesManager;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import it.maymity.freezegui.Utils;

public class FreezeGui implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!Utils.getInstance().getConfig().getBoolean("settings.console_command")) {
            if (!(sender instanceof Player)) {
                MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getMessages().getString("messages.must_player"));
                return false;
            }
        }

        if(!sender.hasPermission("freezegui.reload")){
            MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getMessages().getString("messages.no_permission"));
            return false;
        }

        if (!(args.length > 0)) {
            MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getMessages().getString("messages.reload_usage"));
            return false;
        }

        if (args.length == 1) {
            switch(args[0]){
                case "reload":
                    Main.getInstance().reloadConfig();
                    MessagesManager.getInstance().sendMessage(sender, Utils.getInstance().getMessages().getString("messages.reload"));
                    Main.getInstance().getMessages().reload();
                    break;
            }
        }
        return true;
    }
}