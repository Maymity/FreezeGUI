package it.maymity.freezegui.commands.subcommands;

import it.maymity.freezegui.FreezeGUI;
import it.xquickglare.qlib.commands.SubCommand;
import java.util.Collections;
import org.bukkit.command.CommandSender;

public class Reload extends SubCommand {

    private final FreezeGUI plugin = FreezeGUI.getInstance();

    public Reload(){
        super("reload", "freezegui.commands.reload", Collections.emptyList(), false);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        plugin.getConfiguration().reload();
        plugin.getMessages().reload();
        plugin.getGui_freeze().reload();
        plugin.getGui_sure().reload();

        plugin.getMessages().getMessage("messages.reload").sendMessage(sender);
    }
}