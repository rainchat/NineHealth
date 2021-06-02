package com.rainchat.ninehealth.resource.commandsHealth.subcommands;

import com.rainchat.ninehealth.api.placeholders.ArgsReplacements;
import com.rainchat.ninehealth.managers.PlayerManager;
import com.rainchat.ninehealth.utilitis.global.Chat;
import com.rainchat.ninehealth.utilitis.global.Command;
import com.rainchat.ninehealth.utilitis.global.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AddCommand extends Command {

    PlayerManager playerManager;

    public AddCommand(PlayerManager playerManager) {
        super("add", "add [value] [player] [number]");
        this.playerManager = playerManager;
    }

    @Override
    public boolean run(Player player, String[] args) {

        if (args.length == 4) {
            if (args[1].equals("health")) {
                Player player1 = Bukkit.getPlayer(args[2]);
                playerManager.getProfile(player1).addHealth(Integer.parseInt(args[3]));
                Chat.sendTranslation(player, true, Message.ADD_HEALTH.toString(), new ArgsReplacements(args[3],args[2]));
                player.setMaxHealth(playerManager.getProfile(player).getHealth());
            }
            if (args[1].equals("points")) {
                Player player1 = Bukkit.getPlayer(args[2]);
                playerManager.getProfile(player1).addPoints(Integer.parseInt(args[3]));
                Chat.sendTranslation(player, true, Message.ADD_POINTS.toString(), new ArgsReplacements(args[3],args[2]));
            }
            if (args[1].equals("lives")) {
                Player player1 = Bukkit.getPlayer(args[2]);
                playerManager.getProfile(player1).addLives(Integer.parseInt(args[3]));
                Chat.sendTranslation(player, true, Message.ADD_LIVES.toString(), new ArgsReplacements(args[3],args[2]));
            }


        } else {
            Chat.sendTranslation(player, false, Message.COMMAND_FORMAT.toString(), new ArgsReplacements("/ninehealth " + getUsage()));
        }

        return false;
    }

    @Override
    public List<String> getTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        List<String> list = new ArrayList<>();
        if (strings.length == 2) {
            list.add("health");
            list.add("points");
            list.add("lives");
        } else {
            for (Player player: Bukkit.getOnlinePlayers()) {
                list.add(player.getName());
            }
        }
        return list;
    }
}