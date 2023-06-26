package com.baroque.gfx;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.List;
import java.util.Map;

public class SpriteSheet {
    TextureRegion spriteSheet;
    List<String> regionNames;
    Map<String, Vector2> regions;
    int height;
    int width;
    int cols;
    int rows;

    public SpriteSheet(TextureRegion spriteSheet, int height, int width, int cols, int rows) {
        this.spriteSheet = spriteSheet;
        this.height = height;
        this.width = width;
        this.cols = cols;
        this.rows = rows;
        createRegions();
    }

    public SpriteSheet(TextureRegion spriteSheet, int height, int width, int cols, int rows, List<String> names) {
        this.regionNames = names;
        this.spriteSheet = spriteSheet;
        this.height = height;
        this.width = width;
        this.cols = cols;
        this.rows = rows;
        createRegions();
    }

    public void createRegions() {
        int name = 0;
        for (int x = 0; x < this.cols; x++) {
            for (int y = 0; y < this.rows; y++) {
                String id;
                if (regionNames.size() > name) {
                    id = String.valueOf(name);
                } else {
                    id = regionNames.get(name);
                }
                regions.put(regionNames.get(name), new Vector2(x * width, y * height));
                name++;
            }
        }
    }

    public void setRegion(String name) {
        Vector2 region = regions.get(name);
        spriteSheet.setRegion(region.x, region.y, width, height);
    }

}
