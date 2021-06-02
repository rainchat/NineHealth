package com.rainchat.ninehealth.config;

import com.rainchat.ninehealth.managers.FileManager;
import com.rainchat.ninehealth.utilitis.global.MathUtil;
import com.rainchat.ninehealth.utilitis.object.MinAndMax;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ConfigVillage {

    public static int DEF_LIFE;
    public static int DEF_HEALTH;
    public static int DEF_VALUE;

    public static int LIVES_COST;
    public static int LIVES_COST_UPGRADE;

    public static MinAndMax ACHIEVEMENT_GET;
    public static MinAndMax TOTEM_USE;


    public static void setup() {
        FileConfiguration config = FileManager.Files.CONFIG.getFile();


        DEF_LIFE = config.getInt("Settings.default-lives", 9);
        DEF_HEALTH = config.getInt("Settings.default-health", 2);
        DEF_VALUE = config.getInt("Settings.default-point", 0);

        LIVES_COST = config.getInt("Settings.lives-cost", 350);
        LIVES_COST_UPGRADE = config.getInt("Settings.lives-cost-upgrade", 150);

        ACHIEVEMENT_GET = getNumber(config, "Settings.achievement-get");
        TOTEM_USE = getNumber(config, "Settings.totem-use");
    }

    public static MinAndMax getNumber(FileConfiguration config,String path) {
        String number = config.getString(path, "5");
        String[] numbers = number.split(":");
        if (numbers.length >= 2) {
            return new MinAndMax(getNumb(numbers[0]),getNumb(numbers[1]));
        } else if (numbers.length == 1) {
            return new MinAndMax(getNumb(number));
        }

        return null;
    }

    private static int getNumb(String s) {
        if (MathUtil.isInt(s)) {
            return Integer.parseInt(s);
        }
        return 5;
    }


}
