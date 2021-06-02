package com.rainchat.ninehealth.listeners;

import com.rainchat.ninehealth.api.placeholders.ArgsReplacements;
import com.rainchat.ninehealth.config.ConfigVillage;
import com.rainchat.ninehealth.managers.PlayerManager;
import com.rainchat.ninehealth.utilitis.global.Chat;
import com.rainchat.ninehealth.utilitis.global.Message;
import com.rainchat.ninehealth.utilitis.object.PlayerProgress;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.advancement.Advancement;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Iterator;

public class PlayerListeners implements Listener {

    public PlayerManager playerManager;

    public PlayerListeners(PlayerManager playerManager){
        this.playerManager = playerManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        playerManager.createProfile(event.getPlayer());
        event.getPlayer().setMaxHealth(playerManager.getProfile(event.getPlayer()).getHealth());
        if (playerManager.getProfile(event.getPlayer()).getLives() <= 0) {
            event.getPlayer().kickPlayer(Message.DEATH_LAST.toString());
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        playerManager.removeProfile(event.getPlayer());
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player player = e.getEntity();
        PlayerProgress playerHealth = playerManager.getProfile(player);
        if (playerHealth != null){
            playerHealth.addLives(-1);
            if (playerHealth.getLives() <=0){
                player.kickPlayer(Message.DEATH_LAST.toString());
            } else {
                Chat.sendTranslation(player, true, Message.DEATH.toString(), new ArgsReplacements(String.valueOf(playerHealth.getLives())));
            }
        }
    }

    @EventHandler
    public void onTotemDeath(EntityResurrectEvent e){
        if (!(e.getEntity() instanceof Player) || e.isCancelled()) {
            return;
        }

        Player player = (Player) e.getEntity();
        PlayerProgress playerHealth = playerManager.getProfile(player);

        int x = ConfigVillage.TOTEM_USE.getRandom();
        playerHealth.addPoints(-x);
        if (playerHealth.getPoints() >= 0) {
            Chat.sendTranslation(player, true, Message.TOTEM_USE.toString(), new ArgsReplacements(String.valueOf(x),"0"));
        } else {
            Chat.sendTranslation(player, true, Message.TOTEM_USE.toString(), new ArgsReplacements(String.valueOf(x),"1"));
            playerHealth.addLives(-1);
            if (playerHealth.getLives() <=0){
                player.kickPlayer(Message.DEATH_LAST.toString());
            }
        }

    }

    @EventHandler
    public void onCompleteAchievement(PlayerAdvancementDoneEvent e){

        if (e.getAdvancement().getCriteria().contains("has_the_recipe")) {
            return;
        }

        Player player = (Player) e.getPlayer();
        PlayerProgress playerHealth = playerManager.getProfile(player);


        int x = ConfigVillage.ACHIEVEMENT_GET.getRandom();
        playerHealth.addPoints(x);
        Chat.sendTranslation(player, true, Message.ACHIEVEMENT_GET.toString(), new ArgsReplacements(String.valueOf(x)));

    }


}
