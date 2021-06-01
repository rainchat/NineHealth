package com.rainchat.ninehealth.managers;


import com.rainchat.ninehealth.NineHealth;
import com.rainchat.ninehealth.config.ConfigVillage;
import com.rainchat.ninehealth.utilitis.object.PlayerProgress;
import com.rainchat.ninehealth.utilitis.storage.ConfigLoader;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PlayerManager {

    private final Map<Player, PlayerProgress> profiles;
    private final NineHealth plugin;

    public PlayerManager(NineHealth plugin) {
        this.profiles = new HashMap<>();
        this.plugin = plugin;
    }

    public void createProfile(Player player) {
        PlayerProgress playerProgress = ConfigLoader.read(plugin, "date" + File.separator + player.getUniqueId().toString(), PlayerProgress.class);
        if (playerProgress == null) {
            playerProgress = new PlayerProgress(player,ConfigVillage.DEF_HEALTH,ConfigVillage.DEF_LIFE,ConfigVillage.DEF_VALUE);
        }

        ConfigLoader.write(plugin, "date" + File.separator + player.getUniqueId().toString(), playerProgress);
        profiles.put(player, playerProgress);
    }


    public PlayerProgress getProfile(Player player) {
        return profiles.get(player);
    }

    public void removeProfile(Player player) {
        ConfigLoader.write(plugin, "date" + File.separator + player.getUniqueId().toString(), getProfile(player));
        profiles.remove(player);
    }

}
