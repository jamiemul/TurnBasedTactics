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

    public void addUnit(GameUnit unit) {
        this.unitOnTile = unit;
        this.hasUnit = true;
    }

    public void removeUnit() {
        this.unitOnTile = null;
        this.hasUnit = false;
    }

    public void addLeftWall() {
        leftWall = new Wall(this, this.neighbours.get(GameMap.DIRECTION.southwest), true);
    }

    public void addRightWall() {
        rightWall = new Wall(this, this.neighbours.get(GameMap.DIRECTION.southeast), true);
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

    public boolean isPathBlocked(Tile tile) {
        if (leftWall != null) {
            if (GameMap.DIRECTION.southwest == getNeighborDirection(tile) ||
                    GameMap.DIRECTION.south == getNeighborDirection(tile) ||
                    GameMap.DIRECTION.west == getNeighborDirection(tile)) {
                return true;
            }
        }

        if (rightWall != null) {
            if (GameMap.DIRECTION.southeast == getNeighborDirection(tile) ||
                    GameMap.DIRECTION.south == getNeighborDirection(tile) ||
                    GameMap.DIRECTION.east == getNeighborDirection(tile)) {
                return true;
            }
        }

        if (tile.leftWall != null) {
            if (GameMap.DIRECTION.northeast == getNeighborDirection(tile) ||
                    GameMap.DIRECTION.north == getNeighborDirection(tile) ||
                    GameMap.DIRECTION.east == getNeighborDirection(tile)) {
                return true;
            }
        }

        if (tile.rightWall != null) {
            if (GameMap.DIRECTION.northwest == getNeighborDirection(tile) ||
                    GameMap.DIRECTION.north == getNeighborDirection(tile) ||
                    GameMap.DIRECTION.west == getNeighborDirection(tile)) {
                return true;
            }
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


    public Wall getLeftWall() {
        return leftWall;
    }

    public void setLeftWall(Wall leftWall) {
        this.leftWall = leftWall;
    }

    public Wall getRightWall() {
        return rightWall;
    }

    public void setRightWall(Wall rightWall) {
        this.rightWall = rightWall;
    }
}
