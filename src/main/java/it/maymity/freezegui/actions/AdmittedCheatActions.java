package it.maymity.freezegui.actions;

import it.maymity.freezegui.FreezeGUI;
import it.maymity.freezegui.managers.FreezeManager;
import it.xquickglare.qlib.actions.Action;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

public class AdmittedCheatActions extends Action {

    private final FreezeGUI plugin = FreezeGUI.getInstance();
    private final FreezeManager freeze = FreezeManager.getInstance();

    public AdmittedCheatActions() {
        super("ADMITTED-CHEAT");
    }

    @Override
    public boolean execute(Player player, String[] args) {
        freeze.removeFreeze(player);
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), plugin.getConfiguration().getMessage("commands.punish_command").setVariable("player", player.getName()).toString());
        return true;
    }
}
