package com.zombie.map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zombie.entities.GameUnit;
import com.zombie.game.TextureManager;

import java.util.ArrayList;

public class Tile {
    public int hScore;
    public int gScore;
    public int fScore;
    public Tile parent;
    public final ArrayList<Tile> neighbours;
    public final int x;
    public final int y;
    public final int h;
    public boolean explored = false;
    public boolean isVisible;

    String textureFileName;

    int height;
    int width;

    private boolean walkable;

    public boolean hasUnit() {
        return hasUnit;
    }

    private boolean hasUnit;
    private int timeUnitCost;

    public GameUnit getUnitOnTile() {
        return unitOnTile;
    }

    GameUnit unitOnTile;
    ArrayList<Object> objectsOnTile;

    public Tile(int x, int y, int h) {
        this.isVisible = false;
        this.neighbours = new ArrayList<>();
        this.walkable = true;
        this.x = x;
        this.y = y;
        this.h = h;
        calculateTimeUnitCost();
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public boolean getVisible() {
        return isVisible;
    }

    public void addNeighbour(Tile tile) {
        if (tile != null) {
            neighbours.add(tile);
        }
    }

    public void render(SpriteBatch batch, int screenX, int screenY) {
        batch.draw(TextureManager.getAsset(textureFileName), screenX, screenY, this.width, this.height);
    }

    public void calculateTimeUnitCost() {
        this.timeUnitCost = 0;
    }

    public void setTexture(TextureManager.TILES tile) {
        this.width = tile.texture.getWidth();
        this.height = tile.texture.getHeight();
        this.textureFileName = tile.texture.getName();
        this.timeUnitCost = tile.cost;
        this.walkable = tile.walkable;
    }

    public int getTimeUnitCost() {
        return timeUnitCost;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

    public Object getObjectsOnTile() {
        return objectsOnTile;
    }

    public void addObjectOnTile(Object o) {
        this.objectsOnTile.add(o);
    }

    public void addUnit(GameUnit unit) {
        this.unitOnTile = unit;
        this.hasUnit = true;
    }

    public void removeUnit() {
        this.unitOnTile = null;
        this.hasUnit = false;
    }

    public ArrayList<Tile> getNeighbors(Tile currentTile) {
        return neighbours;
    }
}
