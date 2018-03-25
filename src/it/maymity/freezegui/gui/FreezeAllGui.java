package it.maymity.freezegui.gui;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import it.maymity.freezegui.Utils;

public class FreezeAllGui {
    private static FreezeAllGui instance;
    private Inventory freezeall;

    public static synchronized FreezeAllGui getInstance() {
        if (instance == null) {
            instance = new FreezeAllGui();
        }
        return instance;
    }

    public Inventory getFreezeAll() {
        return freezeall;
    }

    public Inventory getFreezeAllInventory() {
        freezeall = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', Utils.getInstance().getConfig().getString("freezeallgui.display_name")));

        Material material = Material.valueOf(Utils.getInstance().getConfig().getString("freezeallgui.Items.1.material").toUpperCase());
        int amount = Utils.getInstance().getConfig().getInt("freezeallgui.Items.1.amount");
        int intdamage = Utils.getInstance().getConfig().getInt("freezeallgui.Items.1.damage");
        short damage = (short) intdamage;
        ItemStack item = new ItemStack(material, amount, damage);
        String name = ChatColor.translateAlternateColorCodes('&', Utils.getInstance().getConfig().getString("freezeallgui.Items.1.name"));
        ArrayList lore = new ArrayList<String>();
        int slot = Utils.getInstance().getConfig().getInt("freezeallgui.Items.1.slot");
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        for (String line : Utils.getInstance().getConfig().getStringList("freezeallgui.Items.1.lore")) {
            lore.add(ChatColor.translateAlternateColorCodes('&', line));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        getInstance().freezeall.setItem(slot, item);

        return freezeall;
    }

}