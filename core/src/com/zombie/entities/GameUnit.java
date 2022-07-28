package com.zombie.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zombie.game.TextureManager;
import com.zombie.map.MapManager;
import com.zombie.map.Tile;

import java.util.ArrayList;

public class GameUnit {

    private Tile tile;
    private String textureFileName;
    private int timeUnits;
    private int width;
    private int height;
    boolean visible;
    private int x;
    private int y;

    public GameUnit() {
        visible = false;
        timeUnits = 0;
    }

    public GameUnit(TextureManager.TEXTURE texture) {
        visible = false;
        timeUnits = 0;
        setTexture(texture);
    }

    public GameUnit(TextureManager.TEXTURE texture, Tile tile) {
        visible = false;
        timeUnits = 0;
        setTexture(texture);
        setPos(tile);
    }

    public void setPos(Tile tile) {

        if (this.tile != null) {
            this.tile.removeUnit();
        }

        int width = MapManager.TILE_WIDTH;
        int height = MapManager.TILE_HEIGHT;
        this.x = ((tile.getX() - tile.getY()) * (width / 2)) + height / 2;
        this.y = -((tile.getY() + tile.getX()) * (height / 2)) + height / 2;
        this.tile = tile;
        this.tile.addUnit(this);
    }

    public void setTexture(TextureManager.TEXTURE texture) {
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.textureFileName = texture.getName();

    }

    public void moveTo(ArrayList<Tile> path) {
        this.setPos(path.get(path.size() - 1));
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

    public int getTimeUnits() {
        return timeUnits;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void render(SpriteBatch batch) {
        batch.draw(TextureManager.getAsset(textureFileName), x, y, width, height);
    }
}
