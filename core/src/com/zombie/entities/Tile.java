package com.zombie.entities;

import com.zombie.gfx.TextureManager;
import com.zombie.map.MapManager;

import java.util.ArrayList;

public class Tile extends WorldObject {
    public int hScore;
    public int gScore;
    public int fScore;
    public Tile parent;
    public final ArrayList<Tile> neighbours;
    public boolean explored = false;
    public boolean isVisible;
    public TextureManager.TEXTURE baseTile;
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
        super(x, y, MapManager.TILE_WIDTH, MapManager.TILE_HEIGHT);
        this.objectsOnTile = new ArrayList<>();
        this.isVisible = false;
        this.neighbours = new ArrayList<>();
        this.walkable = true;
        this.screenX = ((x - y) * (this.width / 2));
        this.screenY = -((y + x) * (this.height / 2));
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


    public void setBaseTile(TextureManager.TEXTURE texture) {
        this.baseTile = texture;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.textureFileName = texture.getName();
    }

    public int getMovementCost() {
        return movementCost;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public ArrayList<TextureManager.TILE_OBJECTS> getObjectsOnTile() {
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
