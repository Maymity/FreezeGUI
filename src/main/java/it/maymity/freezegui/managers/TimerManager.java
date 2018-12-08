package it.maymity.freezegui.managers;

import it.maymity.freezegui.FreezeGUI;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.HashMap;

public class TimerManager {

    private static TimerManager instance;
    private final FreezeGUI plugin = FreezeGUI.getInstance();

    @Getter private HashMap<Player, Integer> cooldownTime = new HashMap<Player, Integer>();
    private HashMap<Player, BukkitRunnable> cooldownTask = new HashMap<Player, BukkitRunnable>();

    public synchronized  static TimerManager getInstance() {
        if (instance == null) {
            instance = new TimerManager();
        }
        return instance;
    }

    public Boolean hasDelay(Player player)
    {
        if (getCooldownTime().containsKey(player))
            return true;
        else
            return false;
    }

    public void setDelay(Player player, int time)
    {
        cooldownTime.put(player, time);
        cooldownTask.put(player, new BukkitRunnable() {
            public void run() {
                cooldownTime.put(player, cooldownTime.get(player) - 1);
                if (cooldownTime.get(player) == 0) {
                    cooldownTime.remove(player);
                    cooldownTask.remove(player);

                    cancel();
                }
            }
        });
        cooldownTask.get(player).runTaskTimer(plugin, 20, 20);
    }

    public Integer getTimeRemaining(Player player) {
        int t = 0;
        if (getCooldownTime().containsKey(player)) {
            t = cooldownTime.get(player);
        }
        return t;
    }
}
