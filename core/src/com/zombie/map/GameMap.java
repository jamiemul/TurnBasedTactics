package com.zombie.map;

import com.zombie.entities.GameUnit;
import com.zombie.game.TextureManager;

import java.util.ArrayList;
import java.util.Random;

public class GameMap {
    private static ArrayList<Tile> tiles;
    private static Tile[][] tiles2D;
    static short width;
    static short length;
    ArrayList<GameUnit> units;

    public enum MAP_TYPE {
        village, forest, roadside, city, fields
    }

    public GameMap(short w, short l) {
        width = w;
        length = l;
        tiles2D = new Tile[w][l];
        tiles = new ArrayList<>();
        units = new ArrayList<>();
        generateMap();
        addUnits();
    }

    public void addUnits() {

    }

    public void generateMap() {
        for (int w = 0; w < width; w++) {
            for (int l = 0; l < length; l++) {
                {
                    Tile tile = new Tile(w, l, 0);
                    tiles2D[w][l] = tile;
                    tiles.add(tile);
                }
            }
        }

        for (int w = 0; w < width; w++) {
            for (int l = 0; l < length; l++) {
                {
                    tiles2D[w][l].setTexture(TextureManager.getTile());
                    addNeighbours(w, l);

                }
            }
        }
    }

    private void addNeighbours(int w, int l) {
        //Add neighbours for pathfinding.
        if (w < width - 1) {
            tiles2D[w][l].addNeighbour(tiles2D[w + 1][l]);
            if (l < length - 1) {
                tiles2D[w][l].addNeighbour(tiles2D[w + 1][l + 1]);
            }
            if (l > 0) {
                tiles2D[w][l].addNeighbour(tiles2D[w + 1][l - 1]);
            }
        }

        if (w > 0) {
            tiles2D[w][l].addNeighbour(tiles2D[w - 1][l]);
            if (l < length - 1) {
                tiles2D[w][l].addNeighbour(tiles2D[w - 1][l + 1]);
            }
            if (l > 0) {
                tiles2D[w][l].addNeighbour(tiles2D[w - 1][l - 1]);
            }
        }
        if (l > 0) {
            tiles2D[w][l].addNeighbour(tiles2D[w][l - 1]);
        }
        if (l < length - 1) {
            tiles2D[w][l].addNeighbour(tiles2D[w][l + 1]);
        }
    }

    public static ArrayList<Tile> getTiles() {
        return tiles;
    }

    public Tile[][] getTiles2D() {
        return tiles2D;
    }

    public static short getWidth() {
        return width;
    }

    public static short getLength() {
        return length;
    }

}