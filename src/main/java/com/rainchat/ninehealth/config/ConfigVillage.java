package com.rainchat.ninehealth.config;

import com.rainchat.ninehealth.managers.FileManager;
import com.rainchat.ninehealth.utilitis.global.MathUtil;
import com.rainchat.ninehealth.utilitis.object.MinAndMax;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ConfigVillage {

    public static int DEF_LIFE;
    public static int DEF_HEALTH;
    public static int DEF_VALUE;

    public static MinAndMax ACHIEVEMENT_GET;


    public static void setup() {
        FileConfiguration config = FileManager.Files.CONFIG.getFile();


        DEF_LIFE = config.getInt("Economy.claim-money-take", 9);
        DEF_HEALTH = config.getInt("Economy.create-money-take", 2);
        DEF_VALUE = config.getInt("Economy.claim-money-take", 0);

        ACHIEVEMENT_GET = getNumber(config);
    }

    public static MinAndMax getNumber(FileConfiguration config) {
        String number = config.getString("Economy.claim-money-take", "5");
        String[] numbers = number.split(":");
        if (numbers.length > 2) {
            return new MinAndMax(getNumb(numbers[0]),getNumb(numbers[1]));
        } else if (numbers.length == 1) {
            new MinAndMax(getNumb(number));
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
