package com.zombie.map;

import com.zombie.entities.Tile;
import com.zombie.gfx.TextureManager;
import com.zombie.gfx.Textures;

import java.util.ArrayList;

public class GameMap {
    private static ArrayList<Tile> tiles;
    private static Tile[][] tiles2D;
    static short mapWidth;
    static short mapLength;

    public enum DIRECTION {
        north, west, south, east, northeast, southeast, northwest, southwest
    }

    public enum MAP_TYPE {
        village, forest, roadside, city, fields
    }

    public GameMap(short w, short l) {

        mapWidth = w;
        mapLength = l;
        tiles2D = new Tile[w][l];
        tiles = new ArrayList<>();
        generateMap();
        addTileObjects();
        addBuilding(5, 15, 10, 5);
    }

    private void addTileObjects() {
        for (int w = 0; w < mapWidth; w++) {
            for (int l = 0; l < mapLength; l++) {
                Textures.TILE_OBJECTS object = TextureManager.getTileObject();
                if (object.validTiles.contains(tiles2D[w][l].texture.getObject())) {
                    tiles2D[w][l].addObjectOnTile(object);
                }
            }
        }
    }

    public void generateMap() {
        for (int w = 0; w < mapWidth; w++) {
            for (int l = 0; l < mapLength; l++) {
                {
                    Tile tile = new Tile(w, l, 0);
                    tiles2D[w][l] = tile;
                    tiles.add(tile);
                }
            }
        }
        /// ADD BASE TILES
        for (int w = 0; w < mapWidth; w++) {
            for (int l = 0; l < mapLength; l++) {
                {
                    tiles2D[w][l].setTexture(Textures.TILES.grass.texture);
                    addNeighbours(w, l);
                }
            }
        }

        addRoad("");
    }

    private void addRoad(String direction) {
        int start = (mapWidth / 2) - 2;
        int roadWidth = 5;

        for (int w = start; w < start + roadWidth; w++) {
            for (int l = 0; l < mapLength; l++) {
                if (w == start + 2) {
                    tiles2D[w][l].setTexture(Textures.TILES.roadLines.texture);
                } else {
                    tiles2D[w][l].setTexture(Textures.TILES.road.texture);
                }
            }
        }
    }

    private void addNeighbours(int w, int l) {
        //Add neighbours for pathfinding.
        if (w < mapWidth - 1)
            tiles2D[w][l].addNeighbour(DIRECTION.southeast, tiles2D[w + 1][l]);
        if (w < mapWidth - 1 && l < mapLength - 1)
            tiles2D[w][l].addNeighbour(DIRECTION.south, tiles2D[w + 1][l + 1]);
        if (l < mapLength - 1)
            tiles2D[w][l].addNeighbour(DIRECTION.southwest, tiles2D[w][l + 1]);
        if (l > 0)
            tiles2D[w][l].addNeighbour(DIRECTION.northeast, tiles2D[w][l - 1]);
        if (l > 0 && w > 0)
            tiles2D[w][l].addNeighbour(DIRECTION.north, tiles2D[w - 1][l - 1]);
        if (w > 0)
            tiles2D[w][l].addNeighbour(DIRECTION.northwest, tiles2D[w - 1][l]);
        if (l > 0 && w < mapWidth - 1)
            tiles2D[w][l].addNeighbour(DIRECTION.east, tiles2D[w + 1][l - 1]);
        if (w > 0 && l < mapLength - 1)
            tiles2D[w][l].addNeighbour(DIRECTION.west, tiles2D[w - 1][l + 1]);
    }

    private void addBuilding(int startX, int startY, int width, int height) {
        //northWall;
        for (int x = startX; x < startX + width; x++) {
            tiles2D[x][startY].addLeftWall();
            tiles2D[x][startY].getLeftWall().setTexture(Textures.TILES.leftWall.texture);
        }
    }

    public static ArrayList<Tile> getTiles() {
        return tiles;
    }

    public Tile[][] getTiles2D() {
        return tiles2D;
    }

    public static short getWidth() {
        return mapWidth;
    }

    public static short getLength() {
        return mapLength;
    }

}