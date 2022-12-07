package com.zombie.entities;

import com.zombie.map.MapManager;

public class Wall extends WorldObject {
    Tile tile;
    int hitPoints;
    boolean climbable;
    boolean door;

    public Wall(Tile tile) {
        super(tile.x, tile.y, MapManager.TILE_WIDTH, 128, tile.getScreenX(), tile.getScreenY());
        this.tile = tile;
        this.climbable = false;
        this.door = false;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }
}
