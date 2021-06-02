package com.rainchat.ninehealth.api.placeholders;

import com.rainchat.ninehealth.api.BaseReplacements;
import com.rainchat.ninehealth.config.ConfigHealth;
import com.rainchat.ninehealth.utilitis.object.PlayerProgress;
import org.bukkit.entity.Player;

public class NineHealthReplacements extends BaseReplacements<Player> {

    public PlayerProgress playerProgress;


    public NineHealthReplacements(PlayerProgress playerProgress) {
        super("ninehealth_");
        this.playerProgress = playerProgress;
    }


    @Override
    public Class<Player> forClass() {
        return Player.class;
    }

    @Override
    public String getReplacement(String base, String fullKey) {

        if (base.equalsIgnoreCase("health")){
            return String.valueOf(playerProgress.getHealth());
        }
        if (base.equalsIgnoreCase("point")){
            return String.valueOf(playerProgress.getPoints());
        }
        if (base.equalsIgnoreCase("lives")){
            return String.valueOf(playerProgress.getLives());
        }
        if (base.equalsIgnoreCase("buy")){
            int cost = playerProgress.getBoughtLives()* ConfigHealth.LIVES_COST_UPGRADE + ConfigHealth.LIVES_COST;
            return String.valueOf(cost);
        }

        return "";

    }

}