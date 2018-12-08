package it.maymity.freezegui.actions;

import it.maymity.freezegui.FreezeGUI;
import it.xquickglare.qlib.actions.Action;
import org.bukkit.entity.Player;

public class OpenSureMenuActions extends Action {

    private final FreezeGUI plugin = FreezeGUI.getInstance();

    public OpenSureMenuActions() {
        super("OPEN-SURE-MENU");
    }

    @Override
    public boolean execute(Player player, String[] args) {
        plugin.getSureMenu().openInventory(player);
        return true;
    }
}
