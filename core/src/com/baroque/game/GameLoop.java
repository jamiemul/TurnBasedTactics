package com.baroque.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;


public class GameLoop extends ApplicationAdapter {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    public static boolean CAMERA_ACTIVE = true;
    public static final int WIDTH = 320 * 4;
    public static final int HEIGHT = 180 * 4;

    InputManager input;
    GameController gameController;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH / 2.f, HEIGHT / 2.f);
        gameController = new GameController(camera, batch);
        input = new InputManager(camera, gameController);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        if (CAMERA_ACTIVE) camera.update();
        gameController.update();
        batch.setProjectionMatrix(camera.combined);
        batch.enableBlending();
        batch.begin();
        input.update();
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}