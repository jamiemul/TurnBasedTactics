package com.zombie.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class TextureManager extends AssetManager {

    public static class TEXTURE<E extends Enum<E>> {
        final String textureName;
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
                System.out.println(e);
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

    public final static Map<String, Texture> textureMapsList = new HashMap<>();
    public final static Map<String, SpriteSheet> spriteSheetMapList = new HashMap<>();

    public static void addAssets(ArrayList<String> values) {
        for (String asset : values) {
            textureMapsList.put(asset, new Texture(Gdx.files.internal(asset)));
        }
    }

    public static void createSpriteSheet(String asset, int cols, int rows, int width, int height) {
        TextureRegion t = new TextureRegion(getAsset(asset), width, height);
        spriteSheetMapList.put(asset, new SpriteSheet(t, height, width, cols, rows));
    }

    public static Texture getAsset(String name) {
        return textureMapsList.get(name);
    }

    public static Textures.TILE_OBJECTS getTileObject() {

        List<Textures.TILE_OBJECTS> tiles = Arrays.stream(Textures.TILE_OBJECTS.values())
                .flatMap(x -> Collections.nCopies(x.weighting, Textures.TILE_OBJECTS.valueOf(x.name()))
                        .stream()).collect(Collectors.toList());

        Random rand = new Random();

        return tiles.get(rand.nextInt(tiles.size()));
    }
}
