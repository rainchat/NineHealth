package com.rainchat.ninehealth.hooks;


import com.rainchat.ninehealth.NineHealth;
import com.rainchat.ninehealth.config.ConfigVillage;
import com.rainchat.ninehealth.utilitis.object.PlayerProgress;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class Placeholders extends PlaceholderExpansion {

    /**
     * Because this is an internal class,
     * you must override this method to let PlaceholderAPI know to not unregister your expansion class when
     * PlaceholderAPI is reloaded
     *
     * @return true to persist through reloads
     */
    @Override
    public boolean persist() {
        return true;
    }

    /**
     * Because this is a internal class, this check is not needed
     * and we can simply return {@code true}
     *
     * @return Always true since it's an internal class.
     */
    @Override
    public boolean canRegister() {
        return true;
    }

    /**
     * The name of the person who created this expansion should go here.
     * <br>For convienience do we return the author from the plugin.yml
     *
     * @return The name of the author as a String.
     */
    @Override
    public String getAuthor() {
        return "rain_chat";
    }

    /**
     * The placeholder identifier should go here.
     * <br>This is what tells PlaceholderAPI to call our onRequest
     * method to obtain a value if a placeholder starts with our
     * identifier.
     * <br>This must be unique and can not contain % or _
     *
     * @return The identifier in {@code %<identifier>_<value>%} as String.
     */
    @Override
    public String getIdentifier() {
        return "ninehealth";
    }

    /**
     * This is the version of the expansion.
     * <br>You don't have to use numbers, since it is set as a String.
     * <p>
     * For convienience do we return the version from the plugin.yml
     *
     * @return The version as a String.
     */
    @Override
    public String getVersion() {
        return "1.0";
    }

    /**
     * This is the method called when a placeholder with our identifier
     * is found and needs a value.
     * <br>We specify the value identifier in this method.
     * <br>Since version 2.9.1 can you use OfflinePlayers in your requests.
     *
     * @param player     A {@link org.bukkit.OfflinePlayer Player}.
     * @param identifier A String containing the identifier/value.
     * @return possibly-null String of the requested identifier.
     */
    @Override
    public String onPlaceholderRequest(Player player, String identifier) {

        if (player == null)
            return "Only online players!";

        PlayerProgress playerProgress = NineHealth.getPlayerManager().getProfile(player);


        if (identifier.equalsIgnoreCase("health")){
            return String.valueOf(playerProgress.getHealth());
        }
        if (identifier.equalsIgnoreCase("point")){
            return String.valueOf(playerProgress.getPoints());
        }
        if (identifier.equalsIgnoreCase("lives")){
            return String.valueOf(playerProgress.getLives());
        }
        if (identifier.equalsIgnoreCase("buy")){
            int cost = playerProgress.getBoughtLives()* ConfigVillage.LIVES_COST_UPGRADE + ConfigVillage.LIVES_COST;
            return String.valueOf(cost);
        }


        return null;
    }






}
