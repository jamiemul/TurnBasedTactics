package com.zombie.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zombie.entities.GameUnit;
import com.zombie.game.TextureManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MapManager {

    public static final int TILE_WIDTH = 64;
    public static final int TILE_HEIGHT = 32;
    public static Tile SELECTED_TILE = null;
    public static Tile HIGHLIGHTED_TILE = null;
    public static Tile END_TILE;
    public static GameUnit SELECTED_UNIT;
    public static GameMap gameMap;
    final static short MAP_LENGTH = 40;
    final static short MAP_WIDTH = 30;

    Texture selectedTileTexture;
    Texture highlightedTileTexture;
    static PathFinder pathFinder;
    public static ArrayList<Tile> path;
    Tile[][] tiles;
    OrthographicCamera camera;
    private BitmapFont font;

    public MapManager(OrthographicCamera camera) {
        font = new BitmapFont();
        gameMap = new GameMap(MAP_WIDTH, MAP_LENGTH);
        this.camera = camera;
        this.loadTextures();
        this.setCameraStart();
        this.tiles = gameMap.getTiles2D();
        pathFinder = new PathFinder(gameMap.getTiles());
        path = new ArrayList<>();
    }

    public void loadTextures() {
        // LOAD ALL TEXTURES
        ArrayList<String> textures = new ArrayList<>();
        textures.addAll(Arrays.stream(TextureManager.UI_TILES.values()).map(t -> t.texture.getName()).collect(Collectors.toList()));
        textures.addAll(Arrays.stream(TextureManager.TILE_OBJECTS.values()).map(t -> t.texture.getName()).collect(Collectors.toList()));
        textures.addAll(Arrays.stream(TextureManager.UNITS.values()).map(t -> t.texture.getName()).collect(Collectors.toList()));
        textures.addAll(Arrays.stream(TextureManager.TILES.values()).map(t -> t.texture.getName()).collect(Collectors.toList()));
        TextureManager.addAssets(textures);
        // ASSIGN UI TEXTURES
        this.highlightedTileTexture = TextureManager.getAsset(TextureManager.UI_TILES.highlightTile.texture.getName());
        this.selectedTileTexture = TextureManager.getAsset(TextureManager.UI_TILES.selectedTile.texture.getName());
    }

    public void setCameraStart() {
        camera.position.x = -(MAP_LENGTH * (TILE_HEIGHT)) + MAP_LENGTH * 5;
        camera.position.y = -MAP_LENGTH * (TILE_HEIGHT / 2.f);
    }

    public static void createPath() {
        path = pathFinder.findPath(SELECTED_TILE, END_TILE);

    }

    public static void clearExplored() {
        for (Tile t : gameMap.getTiles()) {
            t.explored = false;
        }
    }

    public void renderMap(SpriteBatch batch) {
        int selectedX = 0;
        int selectedY = 0;
        int highlightedX = 0;
        int highlightedY = 0;

        for (int x = 0; x < MAP_WIDTH; x++) {
            for (int y = 0; y < MAP_LENGTH; y++) {
                Tile tile = tiles[x][y];

                tile.render(batch, tile.screenX, tile.screenY - TILE_HEIGHT);

                for (TextureManager.TILE_OBJECTS object : tile.objectsOnTile) {
                    if (object.texture != null) {
                        batch.draw(TextureManager.getAsset(object.texture.getName()), tile.screenX, tile.screenY, object.texture.getWidth(), object.texture.getHeight());
                    }
                }

                if (path != null && path.contains(tile)) {
                    batch.draw(this.highlightedTileTexture, tile.screenX, tile.screenY, TILE_WIDTH, TILE_HEIGHT);
                }

                if (tile.hasUnit()) {
                    GameUnit unit = tile.getUnitOnTile();
                    unit.render(batch);
                }

                if (tile == HIGHLIGHTED_TILE) {
                    highlightedX = tile.screenX;
                    highlightedY = tile.screenY;
                }

                if (tile == SELECTED_TILE) {
                    selectedX = tile.screenX;
                    selectedY = tile.screenY;
                }
            }
        }

        if (END_TILE != null && path != null) {
            if (path.get(0).getPathCost() != null)
                font.draw(batch, path.get(0).getPathCost().toString(), END_TILE.screenX + 32, END_TILE.screenY + 32);
        }

        renderOverTile(batch, this.highlightedTileTexture, highlightedX, highlightedY);

        if (SELECTED_TILE != null) {
            renderOverTile(batch, this.selectedTileTexture, selectedX, selectedY);
        }

    }

    public void renderOverTile(SpriteBatch batch, Texture texture, int x, int y) {
        batch.draw(texture, x, y, TILE_WIDTH, TILE_HEIGHT);
    }

}
