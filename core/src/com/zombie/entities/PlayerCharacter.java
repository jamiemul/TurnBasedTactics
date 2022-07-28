package com.zombie.entities;

import com.zombie.game.TextureManager;

public class PlayerCharacter extends GameUnit {
    private String name;
    private int timeUnits;

    public PlayerCharacter(TextureManager.TEXTURE texture) {
        super(texture);
        this.name = name;
        this.timeUnits = 50;
    }

    public PlayerCharacter(TextureManager.TEXTURE texture, Boolean leader, String name, Attributes attributes) {
        super(texture);
        this.name = name;
        this.timeUnits = 50;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getTimeUnits() {
        return timeUnits;
    }
}

