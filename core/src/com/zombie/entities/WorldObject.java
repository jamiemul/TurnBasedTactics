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
    public TextureManager.TEXTURE texture;

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

    public WorldObject(int x, int y, int tileWidth, int tileHeight, TextureManager.TEXTURE texture) {
        this.x = x;
        this.y = y;
        setTexture(texture);
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

    public void setTexture(TextureManager.TEXTURE texture) {
        this.texture = texture;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.textureFileName = texture.getName();
    }

    public void render(SpriteBatch batch) {
        batch.draw(TextureManager.getAsset(textureFileName), this.screenX, this.screenY, this.width, this.height);
    }
}
