package it.maymity.freezegui.gui;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import it.maymity.freezegui.Utils;

public class SureGui {
    private static SureGui instance;
    private Inventory sure;

    public static synchronized SureGui getInstance() {
        if (instance == null) {
            instance = new SureGui();
        }
        return instance;
    }

    public Inventory getSure() {
        return sure;
    }

    public static void setItem(int numberitem) {

        Material material = Material.valueOf(Utils.getInstance().getConfig().getString("suregui.Items."+numberitem+".material").toUpperCase());
        int amount = Utils.getInstance().getConfig().getInt("suregui.Items."+numberitem+".amount");
        int intdamage = Utils.getInstance().getConfig().getInt("suregui.Items."+numberitem+".damage");
        short damage = (short) intdamage;

        ItemStack item = new ItemStack(material, amount,damage);
        String name = ChatColor.translateAlternateColorCodes('&', Utils.getInstance().getConfig().getString("suregui.Items."+numberitem+".name"));
        ArrayList lore = new ArrayList<String>();
        int slot = Utils.getInstance().getConfig().getInt("suregui.Items."+numberitem+".slot");

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);

        for (String line : Utils.getInstance().getConfig().getStringList("suregui.Items."+numberitem+".lore")) {
            lore.add(ChatColor.translateAlternateColorCodes('&', line));
        }

        meta.setLore(lore);
        item.setItemMeta(meta);

        getInstance().sure.setItem(slot, item);
    }


    public Inventory getSureInventory() {
        sure = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', Utils.getInstance().getConfig().getString("suregui.display_name")));

        setItem(1);
        setItem(2);

        return sure;
    }

}