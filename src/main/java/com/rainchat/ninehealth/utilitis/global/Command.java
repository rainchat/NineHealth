package com.rainchat.ninehealth.utilitis.global;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class Command implements CommandExecutor {

    private final String name, usage;

    public Command(String name, String usage) {
        this.name = name;
        this.usage = usage;
    }

    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if(command.getLabel().equalsIgnoreCase(name)) {
            return run((Player) commandSender, strings);
        }
        return false;
    }

    public abstract boolean run(Player player, String[] args);

    public String getUsage() {
        return usage;
    }

    public abstract List<String> getTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings);

    @Override
    public String toString() {
        return name;
    }
}

