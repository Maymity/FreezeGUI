package it.maymity.freezegui.actions;

import it.maymity.freezegui.FreezeGUI;
import it.maymity.freezegui.managers.TimerManager;
import it.xquickglare.qlib.actions.Action;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

public class ClickTeamspeakActions extends Action {

    private final FreezeGUI plugin = FreezeGUI.getInstance();

    public ClickTeamspeakActions() {
        super("CLICK-TEAMSPEAK");
    }

    @Override
    public boolean execute(Player player, String[] args) {
        if(!TimerManager.getInstance().hasDelay(player)) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.hasPermission("freezegui.staff"))
                    plugin.getMessages().getMessage("messages.entering_on_teamspeak").setVariable("player", player.getName()).sendMessage(p);
            }
            plugin.getMessages().getMessage("messages.teamspeak_allert_sent").sendMessage(player);
            TimerManager.getInstance().setDelay(player, plugin.getConfiguration().getInt("settings.timer_teamspeak_click"));
        }
        else
            plugin.getMessages().getMessage("messages.timer").setVariable("seconds", TimerManager.getInstance().getTimeRemaining(player).toString()).sendMessage(player);
        return true;
    }
}