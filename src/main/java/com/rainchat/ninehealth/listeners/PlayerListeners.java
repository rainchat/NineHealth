package com.rainchat.ninehealth.listeners;

import com.rainchat.ninehealth.config.ConfigVillage;
import com.rainchat.ninehealth.managers.PlayerManager;
import com.rainchat.ninehealth.utilitis.object.PlayerProgress;
import org.bukkit.GameMode;
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

public class PlayerListeners implements Listener {

    public PlayerManager playerManager;

    public PlayerListeners(PlayerManager playerManager){
        this.playerManager = playerManager;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        playerManager.createProfile(event.getPlayer());
        event.getPlayer().setMaxHealth(playerManager.getProfile(event.getPlayer()).getHealth());
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
            playerHealth.addHealth(-1);
            if (playerHealth.getHealth() <=0){
                player.sendTitle("У вас закончились жизни", "", 1, 100, 1);
                player.setGameMode(GameMode.SPECTATOR);
            } else {
                player.sendTitle("У вас осталось " + playerHealth.getHealth() + " здоровья", "", 1, 100, 1);
            }
        }
    }

    @EventHandler
    public void onTotemDeath(EntityResurrectEvent e){
        if (!(e.getEntity() instanceof Player)) {
            return;
        }

        Player player = (Player) e.getEntity();
        PlayerProgress playerHealth = playerManager.getProfile(player);

        playerHealth.addPoints(-10);
        player.sendMessage("У вас осталось поинтов " + playerHealth.getPoints());
    }

    @EventHandler
    public void onCompleteAchievement(PlayerAdvancementDoneEvent e){
        Player player = (Player) e.getPlayer();
        PlayerProgress playerHealth = playerManager.getProfile(player);

        int x = ConfigVillage.ACHIEVEMENT_GET.getRandom();
        playerHealth.addPoints(x);
        player.sendMessage("Вы получили ачивку и заработали очки " + x);
    }


}
