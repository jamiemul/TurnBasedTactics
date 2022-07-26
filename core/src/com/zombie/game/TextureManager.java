package com.zombie.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

import java.util.*;
import java.util.stream.Collectors;

public class TextureManager extends AssetManager {

    public static class TEXTURE<E extends Enum<E>> {
        private final String textureName;
        final int width;
        final int height;

        TEXTURE(String textureName, int width, int height) {
            this.textureName = textureName;
            this.width = width;
            this.height = height;
        }

        public String getName() {
            return textureName;
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }
    }

    public enum UI_TILES {
        selectedTile("tile_high.png", 64, 32), highlightTile("tile_over.png", 64, 32), explored("tile_explored.png", 64, 32);
        public final TEXTURE<UI_TILES> texture;

        UI_TILES(String textureName, int width, int height) {
            texture = new TEXTURE(textureName, width, height);
        }
    }

    public enum TILES {
        trunk1("grassTrunk.png", 64, 128, true, 1),
        grass1("grass1.png", 64, 128, true, 0),
        grass2("grass2.png", 64, 128, true, 0),
        grass3("grass3.png", 64, 128, true, 0),
        grass4("grass4.png", 64, 128, true, 0),
        grass5("grass5.png", 64, 128, true, 0),
        grass6("grass6.png", 64, 128, true, 0),
        tree1("tree1.png", 64, 128, false, 5),
        tree2("tree2.png", 64, 128, false, 5),
        tree3("tree3.png", 64, 128, false, 5),
        tree4("tree4.png", 64, 128, false, 5);

        public final TEXTURE<TILES> texture;
        public final Boolean walkable;
        public final int cost;

        TILES(String textureName, int width, int height, Boolean walkable, int cost) {
            this.walkable = walkable;
            this.cost = cost;
            texture = new TEXTURE(textureName, width, height);
        }
    }

    public enum ROADS {
        trunk1("grassTrunk.png", 64, 128, true, 1);

        public final TEXTURE<ROADS> texture;
        public final Boolean walkable;
        public final int cost;

        ROADS(String textureName, int width, int height, Boolean walkable, int cost) {
            this.walkable = walkable;
            this.cost = cost;
            texture = new TEXTURE(textureName, width, height);
        }
    }

    public enum UNITS {
        zombie("deadTree.png", 64, 64);

        public final TEXTURE<UNITS> texture;

        UNITS(String textureName, int width, int height) {
            texture = new TEXTURE(textureName, width, height);
        }
    }

    public final static Map<String, Texture> textureMapsList = new HashMap<>();

    public static void addAssets(ArrayList<String> values) {
        for (String asset : values) {
            textureMapsList.put(asset, new Texture(Gdx.files.internal(asset)));
        }
    }

    public static Texture getAsset(String name) {
        return textureMapsList.get(name);
    }


    public static TILES getTile() {
        List<TILES> trees = Arrays.stream(TextureManager.TILES.values()).collect(Collectors.toList());
        Random rand = new Random();
        return trees.get(rand.nextInt(trees.size()));
    }
}
