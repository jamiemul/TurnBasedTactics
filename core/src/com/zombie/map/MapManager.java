package com.zombie.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zombie.entities.GameUnit;
import com.zombie.entities.Tile;
import com.zombie.entities.Wall;
import com.zombie.gfx.TextureManager;
import com.zombie.gfx.Textures;

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
    final static short MAP_WIDTH = 40;

    Texture selectedTileTexture;
    Texture highlightedTileTexture;
    Texture exploredTileTexture;
    static PathFinder pathFinder;
    public static ArrayList<Tile> path;
    Tile[][] tiles;
    OrthographicCamera camera;
    private BitmapFont font;
    private UnitManager unitManager;

    public MapManager(OrthographicCamera camera) {
        font = new BitmapFont();
        this.loadTextures();
        addUiTextures();
        gameMap = new GameMap(MAP_WIDTH, MAP_LENGTH);
        this.camera = camera;

        this.setCameraStart();
        this.tiles = gameMap.getTiles2D();
        pathFinder = new PathFinder(gameMap.getTiles());
        path = new ArrayList<>();
        unitManager = new UnitManager(this.tiles, pathFinder);
        unitManager.addUnits();

    }

    private void addUiTextures() {
        // ASSIGN UI TEXTURES
        this.highlightedTileTexture = TextureManager.getAsset(Textures.UI_TILES.highlightTile.texture.getName());
        this.selectedTileTexture = TextureManager.getAsset(Textures.UI_TILES.selectedTile.texture.getName());
        this.exploredTileTexture = TextureManager.getAsset(Textures.UI_TILES.exploredTile.texture.getName());
    }

    public void loadTextures() {
        // LOAD ALL TEXTURES
        ArrayList<String> textures = new ArrayList<>();
        textures.addAll(Arrays.stream(Textures.UI_TILES.values()).map(t -> t.texture.getName()).collect(Collectors.toList()));
        textures.addAll(Arrays.stream(Textures.TILE_OBJECTS.values()).map(t -> t.texture.getName()).collect(Collectors.toList()));
        textures.addAll(Arrays.stream(Textures.UNITS.values()).map(t -> t.texture.getName()).collect(Collectors.toList()));
        textures.addAll(Arrays.stream(Textures.TILES.values()).map(t -> t.texture.getName()).collect(Collectors.toList()));
        TextureManager.addAssets(textures);
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
            t.resetHeuristic();
        }
    }

    public void update(SpriteBatch batch) {
        unitManager.update();

        renderTiles(batch);

        if (HIGHLIGHTED_TILE != null) {
            renderHighlightTile(batch, this.highlightedTileTexture, HIGHLIGHTED_TILE.getScreenX(), HIGHLIGHTED_TILE.getScreenY());
        }

        if (SELECTED_TILE != null) {
            renderHighlightTile(batch, this.selectedTileTexture, SELECTED_TILE.getScreenX(), SELECTED_TILE.getScreenY());
        }

        renderTileObjects(batch);

        if (END_TILE != null && path != null && path.size() > 0) {
            if (path.get(0).getPathCost() != null)
                font.draw(batch, path.get(0).getPathCost().toString(), END_TILE.getScreenX() + 24, END_TILE.getScreenY() + 24);
            renderHighlightTile(batch, this.selectedTileTexture, END_TILE.getScreenX(), END_TILE.getScreenY());
        }
    }

    private void renderTiles(SpriteBatch batch) {
        for (int y = 0; y < MAP_LENGTH; y++) {
            for (int x = 0; x < MAP_WIDTH; x++) {
                Tile tile = tiles[x][y];

                tile.render(batch, tile.getScreenX(), tile.getScreenY() - TILE_HEIGHT);

                if (path != null && path.contains(tile)) {
                    batch.draw(this.highlightedTileTexture, tile.getScreenX(), tile.getScreenY(), TILE_WIDTH, TILE_HEIGHT);
                }
            }
        }
    }

    private void renderTileObjects(SpriteBatch batch) {
        for (int y = 0; y < MAP_LENGTH; y++) {
            for (int x = 0; x < MAP_WIDTH; x++) {
                Tile tile = tiles[x][y];

                for (Textures.TILE_OBJECTS object : tile.getObjectsOnTile()) {
                    if (object.texture != null) {
                        object.render(batch, tile.getScreenX(), tile.getScreenY());
                    }
                }

                if (tile.hasUnit()) {
                    GameUnit unit = tile.getUnitOnTile();
                    unit.render(batch);
                }
                Wall leftWall = tile.getLeftWall();
                Wall rightWall = tile.getRightWall();

                if (leftWall != null) {
                    leftWall.render(batch);
                }
                if (rightWall != null) {
                    rightWall.render(batch);
                }
            }
        }
    }

    public void renderHighlightTile(SpriteBatch batch, Texture texture, int x, int y) {
        batch.draw(texture, x, y, TILE_WIDTH, TILE_HEIGHT);
    }

}
