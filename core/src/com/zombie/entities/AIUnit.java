package com.zombie.entities;

import com.zombie.gfx.TextureManager;

public class AIUnit extends GameUnit {

    public AIUnit(TextureManager.TEXTURE<TextureManager.UNITS> texture, Tile tile) {
        super(texture, tile);
    }
    public boolean moveCompleted;
    public enum AI_STATE {
        WANDER,
        FOLLOW,
        SEARCH
    }

    public AIUnit() {

    }

    @Override
    public void update() {

    }

    public void setGoals() {

    }

    public void refresh(){
        this.moveCompleted = false;
        refreshAvailableTimeUnits();
    }

}
