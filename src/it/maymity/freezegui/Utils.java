package it.maymity.freezegui;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;

public class Utils {

    private static Utils instance;
    private ArrayList FreezeList = new ArrayList();
    private FileConfiguration config;
    private String updatelink;

    private boolean freezeallcheck = false;
    private boolean newupdate = false;

    public static synchronized Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    public ArrayList getFreezeList() {
        return FreezeList;
    }
    public FileConfiguration getConfig() { config = Main.getInstance().getConfig(); return config; }
    public FileConfiguration getMessages() { return Main.getInstance().getMessages().getConfig(); }
    public String getUpdateLink() { return updatelink; }

    public Boolean getFreezeallcheck() {
        return freezeallcheck;
    }
    public Boolean getBoolUpdate(){ return newupdate; }

    public void setFreeze(Player p) { FreezeList.add(p); }
    public void removeFreeze(Player p) {
        FreezeList.remove(p);
    }

    public void setBoolFreezeAll(boolean b){
        freezeallcheck = b;
    }
    public void setBoolUpdate(boolean b){
        newupdate = b;
    }

    public void setUpdateLink(String b){
        updatelink = b;
    }

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