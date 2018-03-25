package it.maymity.freezegui.gui;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import it.maymity.freezegui.Utils;

public class MainGui {
    private static MainGui instance;
    private Inventory freeze;

    public static synchronized MainGui getInstance() {
        if (instance == null) {
            instance = new MainGui();
        }
        return instance;
    }

    public Inventory getFreeze() {
        return freeze;
    }

    public static void setItem(int numberitem) {

        Material material = Material.valueOf(Utils.getInstance().getConfig().getString("freezegui.Items."+numberitem+".material").toUpperCase());
        int amount = Utils.getInstance().getConfig().getInt("freezegui.Items."+numberitem+".amount");
        int intdamage = Utils.getInstance().getConfig().getInt("freezegui.Items."+numberitem+".damage");
        short damage = (short) intdamage;

        ItemStack item = new ItemStack(material, amount,damage);
        String name = ChatColor.translateAlternateColorCodes('&', Utils.getInstance().getConfig().getString("freezegui.Items."+numberitem+".name"));
        ArrayList lore = new ArrayList<String>();
        int slot = Utils.getInstance().getConfig().getInt("freezegui.Items."+numberitem+".slot");

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);

        for (String line : Utils.getInstance().getConfig().getStringList("freezegui.Items."+numberitem+".lore")) {
            lore.add(ChatColor.translateAlternateColorCodes('&', line));
        }

        meta.setLore(lore);
        item.setItemMeta(meta);

        getInstance().freeze.setItem(slot, item);
    }

    public Inventory getFreezeInventory() {
        freeze = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', Utils.getInstance().getConfig().getString("freezegui.display_name")));
        getInstance().setItem(1);
        getInstance().setItem(2);
        getInstance().setItem(3);

        return freeze;
    }

}