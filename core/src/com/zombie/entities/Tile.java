package com.zombie.entities;

import com.zombie.gfx.Textures;
import com.zombie.map.GameMap;
import com.zombie.map.MapManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tile extends WorldObject {
    public int hScore;
    public int gScore;
    public int fScore;
    public Tile parent;
    public final Map<GameMap.DIRECTION, Tile> neighbours;
    public boolean explored = false;
    public boolean isVisible;
    private boolean walkable;

    private Wall leftWall;
    private Wall rightWall;

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
    ArrayList<Textures.TILE_OBJECTS> objectsOnTile;

    public Tile(int x, int y, int h) {
        super(x, y, MapManager.TILE_WIDTH, MapManager.TILE_HEIGHT);
        this.objectsOnTile = new ArrayList<>();
        this.isVisible = false;
        this.neighbours = new HashMap<>();
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

    public void addNeighbour(GameMap.DIRECTION direction, Tile tile) {
        if (tile != null) {
            neighbours.put(direction, tile);
        }
    }

    public int getMovementCost() {
        return movementCost;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public ArrayList<Textures.TILE_OBJECTS> getObjectsOnTile() {
        return objectsOnTile;
    }

    public void addObjectOnTile(Textures.TILE_OBJECTS object) {
        if (object.texture != null) {
            this.objectsOnTile.add(object);
            this.walkable = object.walkable;
            this.movementCost += object.movementCost;
        }
    }

    public void removeObjects() {
        this.walkable = true;
        this.movementCost = 0;
        this.objectsOnTile.clear();
    }

    public void addUnit(GameUnit unit) {
        this.unitOnTile = unit;
        this.hasUnit = true;
    }

    public void removeUnit() {
        this.unitOnTile = null;
        this.hasUnit = false;
    }

    public void addLeftWall() {
        leftWall = new Wall(this);
    }

    public void addRightWall() {
        rightWall = new Wall(this);
    }

    public Wall getLeftWall() {
        return leftWall;
    }

    public Wall getRightWall() {
        return rightWall;
    }

    public boolean hasLeftWall() {
        if (leftWall != null) {
            return true;
        }
        return false;
    }

    public boolean hasRightWall() {
        if (rightWall != null) {
            return true;
        }
        return false;
    }

    public ArrayList<Tile> getNeighbors() {
        ArrayList<Tile> tiles = new ArrayList<>();

        neighbours.values().forEach(value -> tiles.add(value));
        return tiles;
    }

    public GameMap.DIRECTION getNeighborDirection(Tile tile) {
        for (Map.Entry<GameMap.DIRECTION, Tile> entry : neighbours.entrySet()) {
            if (entry.getValue() == tile) {
                return entry.getKey();
            }
        }
        return null;
    }

    public Integer getPathCost() {
        return pathCost;
    }

    public boolean isPathBlocked(Tile goalTile) {
        GameMap.DIRECTION movingDirection = getNeighborDirection(goalTile);
        switch (movingDirection) {
            case south:
                if (this.hasLeftWall() || this.hasRightWall()
                        || neighbours.get(GameMap.DIRECTION.southwest).hasRightWall()
                        || neighbours.get(GameMap.DIRECTION.southeast).hasLeftWall()) {
                    return true;
                }
                break;
            case north:
                if (goalTile.hasRightWall() || goalTile.hasLeftWall()
                        || neighbours.get(GameMap.DIRECTION.northwest).hasRightWall()
                        || neighbours.get(GameMap.DIRECTION.northeast).hasLeftWall()) {
                    return true;
                }
                break;
            case east:
                if (goalTile.hasLeftWall() || this.hasRightWall()
                        || neighbours.get(GameMap.DIRECTION.northeast).hasRightWall()
                        || neighbours.get(GameMap.DIRECTION.northeast).hasLeftWall()) {
                    return true;
                }
                break;
            case west:
                if (goalTile.hasRightWall() || this.hasLeftWall()
                        || neighbours.get(GameMap.DIRECTION.northwest).hasRightWall()
                        || neighbours.get(GameMap.DIRECTION.west).hasRightWall()) {
                    return true;
                }
                break;
            case southeast:
                if (this.hasRightWall()) {
                    return true;
                }
                break;
            case southwest:
                if (this.hasLeftWall()) {
                    return true;
                }
                break;
            case northeast:
                if (goalTile.hasLeftWall()) {
                    return true;
                }
                break;
            case northwest:
                if (goalTile.hasRightWall()) {
                    return true;
                }
                break;
        }

        return false;
    }

    public void setPathCost(int pathCost) {
        this.pathCost = pathCost;
    }

    public void resetHeuristic() {
        explored = false;
        hScore = 0;
        fScore = 0;
        gScore = 0;
    }
}
