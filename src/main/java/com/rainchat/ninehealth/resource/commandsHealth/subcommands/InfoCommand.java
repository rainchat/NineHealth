package com.rainchat.ninehealth.resource.commandsHealth.subcommands;

import com.rainchat.ninehealth.api.placeholders.ArgsReplacements;
import com.rainchat.ninehealth.api.placeholders.NineHealthReplacements;
import com.rainchat.ninehealth.managers.FileManager;
import com.rainchat.ninehealth.managers.PlayerManager;
import com.rainchat.ninehealth.utilitis.global.Chat;
import com.rainchat.ninehealth.utilitis.global.Command;
import com.rainchat.ninehealth.utilitis.global.Message;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class InfoCommand extends Command {

    PlayerManager playerManager;

    public InfoCommand(PlayerManager playerManager) {
        super("info", "info <player>");
        this.playerManager = playerManager;
    }

    @Override
    public boolean run(Player player, String[] args) {
        Chat.sendTranslation(player, true, Message.MENU_INFORMATION_LORE.toString(), new NineHealthReplacements(playerManager.getProfile(player)));
        return false;
    }

    @Override
    public List<String> getTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        return null;
    }
}