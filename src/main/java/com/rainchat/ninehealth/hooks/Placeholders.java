package com.rainchat.ninehealth.hooks;


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
        return "StefTheDev";
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
        return "villages";
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
        return "5.5";
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

        /*
        There is a weird bug with PAPI where, under certain circumstances, it seems to fail to see cached values and
        queries the database. This is usually fine for something like chat, but it quickly becomes a massive issue for
        tablists, scoreboards and other places that might display several placeholders simultaneously and update them
        every tick, quickly racking up hundreds or thousands of queries per tick, on top of the normal runtime accesses
        that EliteMobs does. At scale, this causes large issues. Hence, at least for now, PAPI does not have access to the
        databases for safety purposes. All of the queries should be in memory regardless.
         */
/*
        switch (identifier) {



            case "get_region":
                Village village = villageManager.getVillage(player.getLocation().getChunk());
                if (village != null){
                    return (villageManager.getVillage(player.getLocation().getChunk()).getName() + "");
                } else {
                    return ("пустошь");
                }
        }
*/
        // We return null if an invalid placeholder (f.e. %someplugin_placeholder3%)
        // was provided
        return null;
    }






}
