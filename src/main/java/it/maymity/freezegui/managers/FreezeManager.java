package it.maymity.freezegui.managers;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.*;
import org.bukkit.entity.Player;

public class FreezeManager {

    private static FreezeManager instance;

    @Getter private ArrayList FreezeList = new ArrayList();
    @Getter @Setter private boolean update = false;

    public synchronized  static FreezeManager getInstance() {
        if (instance == null) {
            instance = new FreezeManager();
        }
        return instance;
    }

    public void setFreeze(Player p) { FreezeList.add(p); }
    public void removeFreeze(Player p) { FreezeList.remove(p); }

    public void correctPosition(Player player) {
        if (!player.isOnGround()) {
            World world = player.getWorld();
            double x = player.getLocation().getX();
            double z = player.getLocation().getZ();
            double y = player.getWorld().getHighestBlockAt((int) x, (int) z).getY();
            Location teleport = new Location(world, x, y, z);
            player.teleport(teleport);
        }
    }

}