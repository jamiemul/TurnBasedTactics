package com.zombie.entities;

import com.zombie.game.TextureManager;
import com.zombie.map.Tile;

public class GameUnit {

    Tile tile;
    String textureFileName;

    int width;
    int height;
    boolean visible;

    public GameUnit() {
        visible = false;
    }

    public GameUnit(TextureManager.UNITS textureName, Tile tile) {
        visible = false;
        setTexture(textureName);
        setPos(tile);
    }

    public void setPos(Tile tile) {
        if (this.tile != null) {
            this.tile.removeUnit();
        }

        this.tile = tile;
        this.tile.addUnit(this);
    }

    public void setTexture(TextureManager.UNITS value) {
        this.width = value.texture.getWidth();
        this.height = value.texture.getHeight();
        this.textureFileName = value.texture.getName();
    }

    public Tile getPos() {
        return tile;
    }

    public String getTextureFileName() {
        return textureFileName;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
