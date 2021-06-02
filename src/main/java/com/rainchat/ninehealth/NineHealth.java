package com.rainchat.ninehealth;

import com.rainchat.ninehealth.config.ConfigVillage;
import com.rainchat.ninehealth.hooks.Placeholders;
import com.rainchat.ninehealth.listeners.PlayerListeners;
import com.rainchat.ninehealth.managers.FileManager;
import com.rainchat.ninehealth.managers.PlayerManager;
import com.rainchat.ninehealth.resource.commandsHealth.NineHealthCommand;
import com.rainchat.ninehealth.resource.commandsHealth.subcommands.*;
import com.rainchat.ninehealth.utilitis.global.Message;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class NineHealth extends JavaPlugin {


    public static NineHealth instance;
    private static PlayerManager playerManager;
    private final FileManager fileManager = FileManager.getInstance();

    public static PlayerManager getPlayerManager() {
        return playerManager;
    }

    public static NineHealth getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        fileManager.logInfo(false)
                .registerCustomFilesFolder("/date")
                .setup(this);



        ConfigVillage.setup();

        playerManager = new PlayerManager(this);
        getServer().getPluginManager().registerEvents(new PlayerListeners(playerManager), this);
        registerMessages();

        NineHealthCommand villageCommand = new NineHealthCommand(this);
        villageCommand.initialise(
                new SetCommand(playerManager),
                new BuyCommand(playerManager),
                new AddCommand(playerManager),
                new ReloadCommand(playerManager),
                new InfoCommand(playerManager),
                new HelpCommand(villageCommand)
        );

        getLogger().info("Registered " + villageCommand.getCommands().size() + " sub-command(s).");
        Objects.requireNonNull(getCommand(villageCommand.toString())).setExecutor(villageCommand);

        if(getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            getLogger().info("Successfully hooked into PlaceholderAPI.");
            new Placeholders().register();
        }

        for (Player player: Bukkit.getOnlinePlayers()){
            playerManager.createProfile(player);
        }

    }

    @Override
    public void onDisable() {
        for (Player player: Bukkit.getOnlinePlayers()){
            playerManager.removeProfile(player);
        }
        // Plugin shutdown logic
    }

    private void registerMessages() {
        FileConfiguration yaml = FileManager.Files.MESSAGES.getFile();
        Message.setConfiguration(yaml);

        int index = 0;
        for (Message message : Message.values()) {
            if (message.getList() != null) {
                yaml.set(message.getPath(), message.getList());
            } else {
                index += 1;
                yaml.set(message.getPath(), message.getDef());
            }
        }
        fileManager.saveFile(FileManager.Files.MESSAGES);
        getLogger().info("Registered " + index + " message(s).");
    }
}
