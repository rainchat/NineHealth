package com.rainchat.ninehealth.resource.commandsHealth.subcommands;

import com.rainchat.ninehealth.managers.PlayerManager;
import com.rainchat.ninehealth.utilitis.global.Chat;
import com.rainchat.ninehealth.utilitis.global.Command;
import com.rainchat.ninehealth.utilitis.global.Message;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class AddCommand extends Command {

    PlayerManager playerManager;

    public AddCommand(PlayerManager playerManager) {
        super("add", "add");
        this.playerManager = playerManager;
    }

    @Override
    public boolean run(Player player, String[] args) {

        if (args.length == 4) {
            if (args[1].equals("health")) {
                Player player1 = Bukkit.getPlayer(args[1]);
                playerManager.getProfile(player1).addHealth(Integer.parseInt(args[2]));
                player.sendMessage(Chat.format(Message.REQUEST_NULL.toString()));
            }
            if (args[1].equals("points")) {
                Player player1 = Bukkit.getPlayer(args[1]);
                playerManager.getProfile(player1).addPoints(Integer.parseInt(args[2]));
                player.sendMessage(Chat.format(Message.REQUEST_NULL.toString()));
            }
            if (args[1].equals("lives")) {
                Player player1 = Bukkit.getPlayer(args[1]);
                playerManager.getProfile(player1).addLives(Integer.parseInt(args[2]));
                player.sendMessage(Chat.format(Message.REQUEST_NULL.toString()));
            }


        }
        return false;
    }
}