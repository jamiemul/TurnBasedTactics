package com.zombie.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public  class Texture <E extends Enum<E>>{
    private final String textureName;
    int width;
    int height;
    Object obj;

    Texture(String textureName) {
        this.textureName = textureName;
        this.getImageSize();
    }

    public Texture(String textureName, Object obj) {
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