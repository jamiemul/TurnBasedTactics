package com.zombie.entities;

import com.zombie.map.MapManager;

public class Wall extends WorldObject {
    Tile tile;
    Tile connectingTile;
    int hitPoints;
    public boolean leftWall;
    boolean climbable;

    public Wall(Tile tile, Tile connectingTile, boolean leftWall) {
        super(tile.x, tile.y, MapManager.TILE_WIDTH, MapManager.TILE_HEIGHT, tile.getScreenX(), tile.getScreenY());
        this.leftWall = leftWall;
        this.tile = tile;
        this.connectingTile = connectingTile;
        this.climbable = false;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public Tile getConnectingTile() {
        return connectingTile;
    }

    public void setConnectingTile(Tile connectingTile) {
        this.connectingTile = connectingTile;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public boolean isLeftWall() {
        return leftWall;
    }

    public void setLeftWall(boolean leftWall) {
        this.leftWall = leftWall;
    }
}
