package com.zombie.entities;

import com.badlogic.gdx.graphics.Texture;

public class PlayerCharacter extends GameUnit {
    private String name;

    public PlayerCharacter(Texture texture, Boolean leader, String name, Attributes attributes) {
        super();
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

