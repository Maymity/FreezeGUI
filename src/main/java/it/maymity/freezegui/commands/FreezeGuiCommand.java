package it.maymity.freezegui.commands;

import it.maymity.freezegui.FreezeGUI;
import it.xquickglare.qlib.commands.AdvancedCommand;
import it.maymity.freezegui.commands.subcommands.*;

public class FreezeGuiCommand extends AdvancedCommand {

    public FreezeGuiCommand() {
        super(FreezeGUI.getInstance().getMessages().getMultilineMessage("messages.help-commands"));

        addSubCommand(
                new Reload()
        );
    }
}