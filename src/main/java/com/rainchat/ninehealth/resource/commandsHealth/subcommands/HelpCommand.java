package com.rainchat.ninehealth.resource.commandsHealth.subcommands;

import com.rainchat.ninehealth.resource.commandsHealth.NineHealthCommand;
import com.rainchat.ninehealth.utilitis.global.Chat;
import com.rainchat.ninehealth.utilitis.global.Command;
import com.rainchat.ninehealth.utilitis.global.Message;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HelpCommand extends Command {

    private final NineHealthCommand nineHealthCommand;

    public HelpCommand(NineHealthCommand nineHealthCommand) {
        super("help", "help [page]");
        this.nineHealthCommand = nineHealthCommand;
    }

    @Override
    public boolean run(Player player, String[] args) {
        int index = 1;

        if(args.length > 1) {
            index = Integer.parseInt(args[1]);
            if (args[1].equals("0")) index = 1;
        }

        int page = (int) Math.ceil((double) nineHealthCommand.getCommands().size() / 6);

        if (index > page) {
            player.sendMessage(Chat.format(Message.PAGE_LIMIT.toString().replace("{0}", String.valueOf(page))));
        } else {
            player.sendMessage(Message.PAGE_HELP.toString()
                    .replace("{0}", String.valueOf(index))
                    .replace("{1}", String.valueOf(page))
            );
            player.sendMessage("");

            List<String> stringList = new ArrayList<>();

            nineHealthCommand.getCommands().forEach(command -> stringList.add("/ninehealth " + command.getUsage()));
            Collections.sort(stringList);

            for (int i = 0; i < stringList.size(); i++) {
                if (i >= 6) break;
                int number = i + ((index - 1) * 6);
                if (number >= stringList.size()) {
                    player.sendMessage("");
                } else {
                    String string = stringList.get(number);
                    player.sendMessage(Chat.color(Message.PAGE_FORMAT.toString()
                            .replace("{0}", String.valueOf(number + 1))
                            .replace("{1}", string)
                    ));
                }
            }

            player.sendMessage("");
            if ((index + 1) > page) {
                player.sendMessage(Chat.format(Message.PAGE_PREVIOUS.toString().replace("{0}", String.valueOf(index - 1))
                ));
            } else {
                player.sendMessage(Chat.format(Message.PAGE_NEXT.toString().replace("{0}", String.valueOf(index + 1))));
            }
        }
        return true;
    }

    @Override
    public List<String> getTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        return null;
    }
}
