package com.rainchat.ninehealth.resource.commandsHealth;


import com.rainchat.ninehealth.NineHealth;
import com.rainchat.ninehealth.resource.commandsHealth.subcommands.HelpCommand;
import com.rainchat.ninehealth.utilitis.global.Chat;
import com.rainchat.ninehealth.utilitis.global.Command;
import com.rainchat.ninehealth.utilitis.global.Message;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;

public class NineHealthCommand extends Command implements TabCompleter {

    private final NineHealth nineHealth;
    private Set<Command> commands;

    public NineHealthCommand(NineHealth nineHealth) {
        super("ninehealth", "");
        this.nineHealth = nineHealth;
        this.commands = new HashSet<>();
    }

    public boolean run(Player player, String[] args) {
        if(args.length > 0) {
            for(Command command : commands) {
                if(args[0].equalsIgnoreCase(command.toString())) {
                    if(player.hasPermission(toString() + "." + command.toString())) {
                        command.run(player, args);
                    } else {
                        player.sendMessage(Chat.format(Message.NO_COMMAND_PERMISSION.toString()));
                    }
                    break;
                }
            }
        } else {
            return new HelpCommand(this).run(player, args);
        }
        return true;
    }

    @Override
    public List<String> getTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        return null;
    }

    public void initialise(Command... commands) {
        this.commands.addAll(Arrays.asList(commands));
    }

    public Set<Command> getCommands() {
        return Collections.unmodifiableSet(commands);
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        List<String> list = new ArrayList<>();
        if(strings.length == 1) {
            List<String> finalList = list;
            commands.forEach(subCommand -> finalList.add(subCommand.toString()));
            list = finalList;
        } else {
            for (Command command1: commands) {
                if (command1.toString().equals(strings[0])) {
                    list = command1.getTabComplete(commandSender,command,s,strings);
                }
            }
        }
        return list;
    }
}
