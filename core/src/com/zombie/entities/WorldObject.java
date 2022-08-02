package com.zombie.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zombie.gfx.TextureManager;

public class WorldObject {
    int x;
    int y;
    int height;
    int width;
    int screenX;
    int screenY;
    String textureFileName;

    public WorldObject(int x, int y, int tileWidth, int tileHeight, int screenX, int screenY) {
        this.x = x;
        this.y = y;
        this.height = tileWidth;
        this.width = tileHeight;
        this.screenX = screenX;
        this.screenY = screenY;
    }

    public WorldObject(int x, int y, int tileWidth, int tileHeight) {
        this.x = x;
        this.y = y;
        this.height = tileHeight;
        this.width = tileWidth;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public void render(SpriteBatch batch, int screenX, int screenY) {
        batch.draw(TextureManager.getAsset(textureFileName), screenX, screenY, this.width, this.height);
    }
}
