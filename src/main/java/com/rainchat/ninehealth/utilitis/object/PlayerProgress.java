package com.rainchat.ninehealth.utilitis.object;

import org.bukkit.entity.Player;

public class PlayerProgress {
    private final String uuid;
    private int health;
    private int lives;
    private int boughtLives;
    private int points;


    public PlayerProgress(Player player, int health, int lives, int values) {
        this.uuid = player.getUniqueId().toString();
        this.health = health;
        this.lives = lives;
        this.points = values;
    }

    public void addLives(int lives) {
        this.lives += lives;
    }

    public void addHealth(int health) {
        this.health += health;
    }

    public void addPoints(int values) {
        this.points += values;
    }

    public void addBoughtLives(int values) {
        this.boughtLives += values;
    }

    public String getUuid() {
        return uuid;
    }

    public int getHealth() {
        return health;
    }

    public int getLives() {
        return lives;
    }

    public int getPoints() {
        return points;
    }

    public int getBoughtLives() {
        return boughtLives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setBoughtLives(int values) {
        this.boughtLives += values;
    }

}
