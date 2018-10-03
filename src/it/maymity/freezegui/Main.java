package it.maymity.freezegui;

import it.maymity.freezegui.commands.*;
import it.maymity.freezegui.listeners.*;
import it.maymity.freezegui.managers.Configuration;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.Listener;

public class Main extends JavaPlugin implements Listener {

    static double config_version = 4.8;
    private static Main instance;
    private Configuration messages;

    public static Main getInstance(){
        return instance;
    }

    public Configuration getMessages() { return messages;}

    public void onEnable() {
        instance = this;
        System.out.println("FreezeGUI > Start plugin...");

        System.out.println("FreezeGUI > Registring the event...");
        registerListeners();
        System.out.println("FreezeGUI > Event registred!");

        System.out.println("FreezeGUI > Registring the command...");
        registerExecutors();
        System.out.println("FreezeGUI > Command registred!");

            SpigotUpdater updater = new SpigotUpdater(this, 46176);
            try {
                if (updater.checkForUpdates()) {
                    Utils.getInstance().setBoolUpdate(true);
                    Utils.getInstance().setUpdateLink(updater.getResourceURL());
                    System.out.println("========================================================");
                    System.out.println("FreezeGUI Update Checker");
                    System.out.println("There is a new update available");
                    System.out.println("Latest Version: " + updater.getLatestVersion());
                    System.out.println("Your Version: " + updater.getPlugin().getDescription().getVersion());
                    System.out.println("Get it here: " + updater.getResourceURL());
                    System.out.println("========================================================");
                } else {
                    System.out.println("========================================================");
                    System.out.println("FreezeGUI Update Checker");
                    System.out.println("You are using the latest version!");
                    System.out.println("========================================================");
                }
            } catch (Exception e) {
                System.out.println("========================================================");
                System.out.println("FreezeGUI Update Checker");
                System.out.println("Could not connect to Spigot's API!");
                System.out.println("Error: ");
                e.printStackTrace();
                System.out.println("========================================================");
            }

        System.out.println("FreezeGUI > Plugin enabled!");
        System.out.println("FreezeGUI > Plugin created by Maymity!");


        if ((!Utils.getInstance().getConfig().contains("settings.config_version")) || (Utils.getInstance().getConfig().getDouble("settings.config_version") < config_version)) {
            System.out.println("&cYou config is out of date!");
            System.out.println("&cPlease, regenerate you config file!");
            System.out.println("&eYour version: &a" + Utils.getInstance().getConfig().getDouble("settings.config_version"));
            System.out.println("&cNewest version: &a" + config_version);
        }

        messages = new Configuration("messages.yml", this, true);
        saveDefaultConfig();
    }

    public void onDisable() {
        System.out.println("FreezeGUI > Plugin disabled!");
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new AsyncPlayerChat(), this);
        Bukkit.getPluginManager().registerEvents(new BlockBreak(), this);
        Bukkit.getPluginManager().registerEvents(new BlockPlace(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamageByEntity(), this);
        Bukkit.getPluginManager().registerEvents(new EntityShootBow(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClick(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClose(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerCommandPreprocess(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDropItem(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMove(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerPickupItem(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuit(), this);
    }

    private void registerExecutors() {
        Bukkit.getPluginCommand("freeze").setExecutor(new Freeze());
        Bukkit.getPluginCommand("unfreeze").setExecutor(new Unfreeze());
        Bukkit.getPluginCommand("freezeall").setExecutor(new FreezeAll());
        Bukkit.getPluginCommand("unfreezeall").setExecutor(new UnfreezeAll());
        Bukkit.getPluginCommand("freezegui").setExecutor(new FreezeGui());
    }
}