package com.rainchat.ninehealth.resource.commandsHealth.subcommands;

import com.rainchat.ninehealth.managers.FileManager;
import com.rainchat.ninehealth.managers.PlayerManager;
import com.rainchat.ninehealth.utilitis.global.Chat;
import com.rainchat.ninehealth.utilitis.global.Command;
import com.rainchat.ninehealth.utilitis.global.Message;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ReloadCommand extends Command {

    PlayerManager playerManager;

    public ReloadCommand(PlayerManager playerManager) {
        super("reload", "reload");
        this.playerManager = playerManager;
    }

    @Override
    public boolean run(Player player, String[] args) {
        player.sendMessage(Chat.format(Message.RELOAD.toString()));
        FileManager.getInstance().reloadAllFiles();
        return false;
    }

    @Override
    public List<String> getTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        return null;
    }
}
