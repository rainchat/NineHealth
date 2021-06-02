package com.rainchat.ninehealth.utilitis.global;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Message {
    HELP("Messages.help", "Напишите &b/villages help [page] &7чтобы увидеть страницу помощи."),
    RELOAD("Messages.reload", "Конфиги успешно перезагружены."),
    MENU_INFORMATION_LORE("Messages.information.lore", Arrays.asList(
            "&e&lИнформация о вас",
            "&7Здоровье: &b%ninehealth_health%",
            "&7Жизни: &a%ninehealth_lives%",
            "&7Очки: &e%ninehealth_point%",
            "&7Стоимость покупки здоровья: &c%ninehealth_buy%"
    )),
    NO_COMMAND_PERMISSION("Messages.no-command-permission", "&7У вас нет прав на эту команду."),
    COMMAND_FORMAT("Messages.no-command-format", "&7Используйте &e%args_0%."),
    PAGE_FORMAT("Messages.page-format", "&b{0}. &7{1}"),
    PAGE_HELP("Messages.page-help", "&e&lПомощь: &7[{0}/{1}]"),
    PAGE_LIMIT("Messages.page-limit", "&7Существует только &b{0} &7страниц помощи."),
    PAGE_NEXT("Messages.page-next", "&7Напишите &b/villages help {0} &7страница вперед."),
    PAGE_PREVIOUS("Messages.page-previous", "Напишите &b/villages help {0} &7страница назад."),
    PREFIX("Messages.prefix", "&3&lHealthRain: &7"),
    BUY_LIVES("Messages.buy-lives", "&7Вы купили жизнь и потратили на это &a%args_0% &7очков."),
    BUY_LIVES_NO_POINTS("Messages.buy-lives-no-points", "&7У вас недостаточно очков, чтобы купить жизнь (&cтребуется %args_0%&7)."),
    SET_HEALTH("Messages.set-health", "&7Здоровье игрока &e%args_0% &7установлено на значение &e%args_1%."),
    SET_LIVES("Messages.set-lives", "&7Жизни игрока &7%args_0% &7установлены на значения &e%args_1%."),
    SET_POINTS("Messages.set-points", "&7Очки игрока &e%args_0% &7установлены на значение &e%args_1%."),
    ADD_HEALTH("Messages.add-health", "&7Успешно добавлено здоровье &e%args_0% &7игроку &e%args_1%."),
    ADD_LIVES("Messages.add-lives", "&7Успешно добавлены жизни &e%args_0% &7игроку &e%args_1%."),
    ADD_POINTS("Messages.add-points", "&7Успешно добавлены очки &e%args_0% &7игроку &e%args_1%."),
    ACHIEVEMENT_GET("Messages.achievement-get","&7Вы получили ачивку и заработали &a%args_0% &7очков"),
    DEATH("Messages.death","&7Вы умерли, у вас осталось &c%args_0% &7жизни"),
    DEATH_LAST("Messages.death_last","&7У вас закончились все жизни, теперь вы не можите играть на сервере"),
    TOTEM_USE("Messages.totem-use","&7За использование тотема у вас снято &c%args_0% &7очков и &c%args_1% &7жизни");

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

    public static String convertList(List<String> list) {
        String message = "";
        for (String line : list) {
            message += line + "\n";
        }
        return message;
    }

    public static void setConfiguration(FileConfiguration configuration) {
        Message.configuration = configuration;
    }

    public String getDef() {
        return configuration.getString(path, def);
    }

    @Override
    public String toString() {
        String message;
        boolean isList = isList();
        boolean exists = exists();
        if (isList) {
            if (exists) {
                message = convertList(configuration.getStringList(path));
            } else {
                message = convertList(getDefaultListMessage());
            }
        } else {
            if (exists) {
                message = configuration.getString(path);
            } else {
                message = getDefaultMessage();
            }
        }

        return Color.parseHexString(message);
    }

    public String getMessage() {
        return Color.parseHexString(configuration.getString(path, def));
    }

    private boolean exists() {
        return configuration.contains(path);
    }

    private boolean isList() {
        if (configuration.contains(path)) {
            return !configuration.getStringList(path).isEmpty();
        } else {
            return def == null;
        }
    }

    public List<String> toList() {
        return configuration.getStringList(path);
    }

    public String getPath() {
        return path;
    }

    public List<String> getList() {
        return list;
    }

    private String getDefaultMessage() {
        return def;
    }

    private List<String> getDefaultListMessage() {
        return list;
    }
}