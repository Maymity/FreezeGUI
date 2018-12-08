package it.maymity.freezegui;

import it.maymity.freezegui.actions.AdmittedCheatActions;
import it.maymity.freezegui.actions.ClickTeamspeakActions;
import it.maymity.freezegui.actions.OpenSureMenuActions;
import it.maymity.freezegui.actions.ReturnMenuActions;
import it.maymity.freezegui.commands.Freeze;
import it.maymity.freezegui.commands.FreezeGuiCommand;
import it.maymity.freezegui.commands.Unfreeze;
import it.maymity.freezegui.listeners.*;
import it.maymity.freezegui.managers.FreezeManager;
import it.xquickglare.qlib.actions.ActionManager;
import it.xquickglare.qlib.configuration.YAMLConfiguration;
import it.xquickglare.qlib.menus.objects.ConfigMenu;
import it.xquickglare.qlib.objects.QLibPlugin;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class FreezeGUI extends QLibPlugin {

    private final double CONFIG_VERSION = 5.0;
    @Getter private static FreezeGUI instance;

    @Getter private YAMLConfiguration configuration;
    @Getter private YAMLConfiguration messages;
    @Getter private YAMLConfiguration gui_freeze;
    @Getter private YAMLConfiguration gui_sure;

    @Getter private ConfigMenu freezeMenu;
    @Getter private ConfigMenu sureMenu;

    @Getter private SpigotUpdater spigotUpdater;

    public void onEnable()
    {
        instance = this;
        super.onEnable();
        logs.infoLog("FreezeGUI > Start plugin...", true);

        checkForUpdates();
        registerConfig();
        registerGui();

        registerListeners();
        registerExecutors();
        registerActions();

        logs.infoLog("FreezeGUI > Plugin enabled!", true);
        logs.infoLog("FreezeGUI > Plugin created by Maymity!", true);
    }

    public void onDisable()
    {
        logs.infoLog("FreezeGUI > Plugin disabled!", true);
    }

    private void registerListeners()
    {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new BlockEvents(), this);
        pm.registerEvents(new ChatEvents(), this);
        pm.registerEvents(new EntityEvents(), this);
        pm.registerEvents(new PlayerEvents(), this);
        pm.registerEvents(new InventoryEvents(), this);
    }

    private void registerExecutors()
    {
        getCommand("freeze").setExecutor(new Freeze());
        getCommand("unfreeze").setExecutor(new Unfreeze());

        FreezeGuiCommand freezeGuiCommand = new FreezeGuiCommand();
        getCommand("freezegui").setExecutor(freezeGuiCommand);
        getCommand("freezegui").setTabCompleter(freezeGuiCommand);
    }

    private void registerActions(){
        ActionManager am = getQLib().getActionManager();

        am.addAction(new AdmittedCheatActions());
        am.addAction(new ClickTeamspeakActions());
        am.addAction(new OpenSureMenuActions());
        am.addAction(new ReturnMenuActions());
    }

    public void registerConfig(){
        configuration = new YAMLConfiguration("config", this);
        messages = new YAMLConfiguration("messages", this);
        gui_freeze = new YAMLConfiguration("guis/freeze_gui", this);
        gui_sure = new YAMLConfiguration("guis/sure_gui", this);

        if (configuration.getString("settings.config_version") == null || configuration.getDouble("settings.config_version") < CONFIG_VERSION) {
            logs.infoLog("&cYou config is out of date!", true);
            logs.infoLog("&cPlease, regenerate you config file!", true);
            logs.infoLog("&eYour version: &a" + configuration.getDouble("settings.config_version"),true);
            logs.infoLog("&cNewest version: &a" + CONFIG_VERSION, true);
        }
    }

    public void registerGui(){
        if(gui_freeze == null)
            gui_freeze = new YAMLConfiguration("guis/freeze_gui", this);
        else
            gui_freeze.reload();

        if(freezeMenu != null)
            getQLib().getMenuManager().removeMenu(freezeMenu);

        freezeMenu = new ConfigMenu(this, "FREEZE-MENU", gui_freeze);
        getQLib().getMenuManager().registerMenu(freezeMenu);


        if(gui_sure == null)
            gui_sure = new YAMLConfiguration("guis/sure_gui", this);
        else
            gui_sure.reload();

        if(sureMenu != null)
            getQLib().getMenuManager().removeMenu(sureMenu);

        sureMenu = new ConfigMenu(this, "SURE-MENU", gui_sure);
        getQLib().getMenuManager().registerMenu(sureMenu);
    }

    private void checkForUpdates(){
        spigotUpdater = new SpigotUpdater(this, 46176);
        try
        {
            if (spigotUpdater.checkForUpdates())
            {
                FreezeManager.getInstance().setUpdate(true);
                logs.infoLog("========================================================", true);
                logs.infoLog("FreezeGUI+ Update Checker", true);
                logs.infoLog("There is a new update available", true);
                logs.infoLog("Latest Version: " + spigotUpdater.getNewVersion(), true);
                logs.infoLog("Your Version: " + getDescription().getVersion(),true);
                logs.infoLog("Get it here: " + spigotUpdater.getResourceURL(),true);
                logs.infoLog("========================================================", true);
            }
            else
            {
                logs.infoLog("========================================================",true);
                logs.infoLog("FreezeGUI+ Update Checker",true);
                logs.infoLog("You are using the latest version!",true);
                logs.infoLog("========================================================",true);
            }
        }
        catch (Exception e)
        {
            logs.infoLog("========================================================",true);
            logs.infoLog("FreezeGUI+ Update Checker",true);
            logs.infoLog("Could not connect to Spigot's API!",true);
            logs.infoLog("========================================================",true);
        }
    }
}