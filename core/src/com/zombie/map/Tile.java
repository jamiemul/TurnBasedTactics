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

    public final int screenX;
    public final int screenY;
    public final int h;
    public boolean explored = false;
    public boolean isVisible;
    public TextureManager.TEXTURE baseTile;

    String textureFileName;

    int height;
    int width;

    private boolean walkable;

    public boolean hasUnit() {
        return hasUnit;
    }

    private boolean hasUnit;
    private int movementCost;
    private Integer pathCost;

    public GameUnit getUnitOnTile() {
        return unitOnTile;
    }

    GameUnit unitOnTile;
    ArrayList<TextureManager.TILE_OBJECTS> objectsOnTile;

    public Tile(int x, int y, int h) {
        this.objectsOnTile = new ArrayList<>();
        this.isVisible = false;
        this.neighbours = new ArrayList<>();
        this.walkable = true;
        this.x = x;
        this.y = y;
        this.h = h;
        this.width = MapManager.TILE_WIDTH;
        this.height = MapManager.TILE_HEIGHT;
        this.screenX = ((x - y) * (width / 2));
        this.screenY = -((y + x) * (height / 2));
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

    public void setBaseTile(TextureManager.TEXTURE texture) {
        this.baseTile = texture;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.textureFileName = texture.getName();
    }


    private void setTextureData(TextureManager.TEXTURE texture) {

    }

    public int getMovementCost() {
        return movementCost;
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

    public Object getObjectsOnTile() {
        return objectsOnTile;
    }

    public void addObjectOnTile(TextureManager.TILE_OBJECTS object) {
        if (object.texture != null) {
            this.objectsOnTile.add(object);
            this.walkable = object.walkable;
            this.movementCost += object.movementCost;
        }
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

    public Integer getPathCost() {
        return pathCost;
    }

    public void setPathCost(int pathCost) {
        this.pathCost = pathCost;
    }
}
