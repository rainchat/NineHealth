package com.rainchat.ninehealth.resource.menus;


import com.rainchat.ninehealth.managers.PlayerManager;
import com.rainchat.ninehealth.utilitis.global.Item;
import com.rainchat.ninehealth.utilitis.global.Message;
import com.rainchat.ninehealth.utilitis.menus.Menu;
import com.rainchat.ninehealth.utilitis.menus.MenuItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class PermissionsMenu extends Menu {

    private final PlayerManager playerManager;

    PermissionsMenu(Plugin plugin, PlayerManager playerManager) {
        super(plugin, "Menu", 27);
        this.playerManager = playerManager;
    }

    @Override
    public Menu build() {
        addItems(new MenuItem(13, back(), inventoryClickEvent ->
        {
            inventoryClickEvent.getWhoClicked().closeInventory();
        }));
        return this;
    }

    private ItemStack back() {
        return new Item()
                .material(Material.PISTON)
                .name(Message.MENU_INFORMATION_TITLE.toString())
                .lore(Message.MENU_INFORMATION_LORE.toList())
                .build();
    }
}
