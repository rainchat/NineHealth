package com.rainchat.ninehealth.utilitis.global;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Message {
    HELP("Messages.help", "Type &b/villages help [page] &7to look at the help pages."),
    MENU_INFORMATION_TITLE("Menu.information.title", "&e&lInformation"),
    MENU_INFORMATION_LORE("Menu.information.lore", Arrays.asList(
            "&7&o{description}",
            "",
            "&7Owner: &b{owner}",
            "&7Level: &a{level}",
            "&7Members: &e{members}",
            "&7Claims: &c{claims}"
    )),
    NO_COMMAND_PERMISSION("Messages.no-command-permission", "You do not have permissions for that command."),
    NO_PERMISSION("Messages.no-permission", "You do not have permissions for &b{0}&7."),
    PAGE_FORMAT("Messages.page-format", "&b{0}. &7{1}"),
    PAGE_HELP("Messages.page-help", "&e&lHelp: &7[{0}/{1}]"),
    PAGE_LIMIT("Messages.page-limit", "There are only &b{0} &7help pages."),
    PAGE_NEXT("Messages.page-next", "Type &b/villages help {0} &7for the next page."),
    PAGE_PREVIOUS("Messages.page-previous", "Type &b/villages help {0} &7for the previous page."),
    PREFIX("Messages.prefix", "&e&lVillages: &7"),
    REQUEST_NULL("Messages.request-null", "You do not have any pending requests.");

    private String path, def;
    private List<String> list;
    private static FileConfiguration configuration;

    Message(String path, String def) {
        this.path = path;
        this.def = def;
    }

    Message(String path, List<String> list) {
        this.path = path;
        this.list = list;
    }

    public String getDef() {
        return configuration.getString(path, def);
    }

    @Override
    public String toString() {
        return Chat.color(configuration.getString(path, def));
    }

    public List<String> toList() {
        return configuration.getStringList(path);
    }

    public static void setConfiguration(FileConfiguration configuration) {
        Message.configuration = configuration;
    }

    public String getPath() {
        return path;
    }

    public List<String> getList() {
        return list;
    }
}
