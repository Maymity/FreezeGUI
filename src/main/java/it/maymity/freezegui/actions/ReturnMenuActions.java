package it.maymity.freezegui.actions;

import it.maymity.freezegui.FreezeGUI;
import it.xquickglare.qlib.actions.Action;
import org.bukkit.entity.Player;

public class ReturnMenuActions extends Action {

    private final FreezeGUI plugin = FreezeGUI.getInstance();

    public ReturnMenuActions() {
        super("RETURN-MENU");
    }

    @Override
    public boolean execute(Player player, String[] args) {
        plugin.getFreezeMenu().openInventory(player);
        return true;
    }
}
