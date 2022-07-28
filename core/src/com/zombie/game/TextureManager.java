package com.zombie.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class TextureManager extends AssetManager {

    public static class TEXTURE<E extends Enum<E>> {
        private final String textureName;
        int width;
        int height;
        Object obj;

        TEXTURE(String textureName) {
            this.textureName = textureName;
            this.getImageSize();
        }

        public TEXTURE(String textureName, Object obj) {
            this.obj = obj;
            this.textureName = textureName;
            this.getImageSize();
        }

        public void getImageSize() {
            try {
                File file = new File("assets/" + textureName);
                BufferedImage img = ImageIO.read(file);
                width = img.getWidth();
                height = img.getHeight();
            } catch (IOException e) {

            }
        }

        public Object getObject() {
            return this.obj;
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
        selectedTile("tile-select.png"),
        highlightTile("tile-high.png"),
        explored("tile-path.png");
        public final TEXTURE<UI_TILES> texture;

        UI_TILES(String textureName) {
            texture = new TEXTURE(textureName);
        }
    }

    public enum TILE_OBJECTS {
        grassTrunk("grass-trunk.png", true, 3, 1, List.of((TILES.grass))),
        grass1YF("grass-1y-fl.png", true, 0, 5, List.of((TILES.grass))),
        grass9YF("grass-9y-fl.png", true, 0, 5, List.of((TILES.grass))),
        grassBush("grass-bush.png", true, 3, 5, List.of((TILES.grass))),
        grassLeaves("grass-leaves.png", true, 0, 20, List.of((TILES.grass))),
        treeDead("tree-dead.png", false, 0, 1, List.of((TILES.grass))),
        treeOak("tree-oak.png", false, 0, 1, List.of((TILES.grass))),
        tree1("tree1.png", false, 0, 1, List.of((TILES.grass))),
        tree2("tree2.png", false, 0, 1, List.of((TILES.grass)));

        public final TEXTURE<TILE_OBJECTS> texture;
        public final Boolean walkable;
        public final int movementCost;
        public int weighting;
        public final List<TILES> validTiles;

        TILE_OBJECTS(String textureName, Boolean walkable, int movementCost, int weighting, List<TILES> validTiles) {
            this.walkable = walkable;
            this.movementCost = movementCost;
            this.weighting = weighting;
            this.validTiles = validTiles;
            texture = new TEXTURE(textureName);
        }

        public void setWeighting(int weighting) {
            this.weighting = weighting;
        }

    }

    public enum TILES {
        grass("tile_grass.png", true, 0),
        road("tile_road.png", true, 0),
        roadLines("tile_road_lines_l.png", true, 0);

        public final TEXTURE<TILES> texture;
        public final Boolean walkable;
        public final int cost;

        TILES(String textureName, Boolean walkable, int cost) {
            this.walkable = walkable;
            this.cost = cost;
            texture = new TEXTURE(textureName, this);
        }
    }

    public enum UNITS {
        zombie("zombie.png"),
        human("deckard.png");
        public final TEXTURE<UNITS> texture;

        UNITS(String textureName) {
            texture = new TEXTURE(textureName, this.getClass());
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

    public static TILE_OBJECTS getTileObject() {

        List<TILE_OBJECTS> tiles = Arrays.stream(TextureManager.TILE_OBJECTS.values())
                .flatMap(x -> Collections.nCopies(x.weighting, TILE_OBJECTS.valueOf(x.name()))
                        .stream()).collect(Collectors.toList());

        Random rand = new Random();

        return tiles.get(rand.nextInt(tiles.size()));
    }
}
