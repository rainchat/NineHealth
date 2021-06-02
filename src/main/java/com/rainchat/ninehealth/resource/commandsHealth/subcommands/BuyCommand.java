package com.rainchat.ninehealth.resource.commandsHealth.subcommands;

import com.rainchat.ninehealth.api.placeholders.ArgsReplacements;
import com.rainchat.ninehealth.config.ConfigVillage;
import com.rainchat.ninehealth.managers.PlayerManager;
import com.rainchat.ninehealth.utilitis.global.Chat;
import com.rainchat.ninehealth.utilitis.global.Command;
import com.rainchat.ninehealth.utilitis.global.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class BuyCommand extends Command {

    PlayerManager playerManager;

    public BuyCommand(PlayerManager playerManager) {
        super("buy", "buy");
        this.playerManager = playerManager;
    }

    @Override
    public boolean run(Player player, String[] args) {

        int cost = playerManager.getProfile(player).getBoughtLives()* ConfigVillage.LIVES_COST_UPGRADE + ConfigVillage.LIVES_COST;

        if (cost > playerManager.getProfile(player).getPoints()) {
            Chat.sendTranslation(player, true, Message.BUY_LIVES_NO_POINTS.toString(), new ArgsReplacements(String.valueOf(cost)));
            return false;
        }

        playerManager.getProfile(player).addPoints(-cost);
        playerManager.getProfile(player).addLives(1);
        playerManager.getProfile(player).addBoughtLives(1);

        Chat.sendTranslation(player, true, Message.BUY_LIVES.toString(), new ArgsReplacements(String.valueOf(cost)));

        return false;
    }

    @Override
    public List<String> getTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        return null;
    }
}