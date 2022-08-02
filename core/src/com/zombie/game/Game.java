package com.zombie.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.zombie.map.MapManager;

public class Game extends ApplicationAdapter {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    public static boolean CAMERA_ACTIVE = true;
    MapManager renderer;
    public static final int WIDTH = 320 * 4;
    public static final int HEIGHT = 180 * 4;

    InputManager input;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH / 2.f, HEIGHT / 2.f);
        renderer = new MapManager(camera);
        input = new InputManager(camera);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        if (CAMERA_ACTIVE) camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        input.update();
        renderer.update(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
