package com.rainchat.ninehealth.api.placeholders;

import com.rainchat.ninehealth.api.BaseReplacements;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class ArgsReplacements extends BaseReplacements<Player> {

    public List<String> args;


    public ArgsReplacements(String... strings) {
        super("args_");
        this.args = Arrays.asList(strings);
    }

    public static boolean isNumeric(String strNum) {
        int d = 0;
        if (strNum == null) {
            return false;
        }
        try {
            d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @Override
    public Class<Player> forClass() {
        return Player.class;
    }

    @Override
    public String getReplacement(String base, String fullKey) {

        if (isNumeric(base)) {
            int number = Integer.parseInt(base);
            if (number <= args.size()) {
                return args.get(number);
            }
        }

        return "";

    }

}