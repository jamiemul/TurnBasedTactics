package com.baroque.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class InputManager implements InputProcessor {
    final OrthographicCamera camera;
    final int keyAmount = 7;
    final Input input = Gdx.input;
    final Vector3 mousePos = new Vector3();
    boolean leftButtonPressed;
    boolean rightButtonPressed;
    public GameController gameController;

    public InputManager(OrthographicCamera camera, GameController gameController) {
        this.camera = camera;
        input.setInputProcessor(this);
        this.gameController = gameController;
    }

    public void update() {
        leftButtonPressed = (input.isButtonJustPressed(Input.Buttons.LEFT));
        rightButtonPressed = (input.isButtonJustPressed(Input.Buttons.RIGHT));
        mapMove();
    }

    private void mapMove() {
        if (input.isKeyPressed(Input.Keys.LEFT) || input.isKeyPressed(Input.Keys.A)) {
            camera.translate(-keyAmount, 0, 0);
        }

        if (input.isKeyPressed(Input.Keys.RIGHT) || input.isKeyPressed(Input.Keys.D)) {
            camera.translate(keyAmount, 0, 0);
        }

        if (input.isKeyPressed(Input.Keys.UP) || input.isKeyPressed(Input.Keys.W)) {
            camera.translate(0, keyAmount, 0);
        }

        if (input.isKeyPressed(Input.Keys.DOWN) || input.isKeyPressed(Input.Keys.S)) {
            camera.translate(0, -keyAmount, 0);
        }
    }

    private void selectTile() {

        mousePos.x = input.getX();
        mousePos.y = input.getY();
        mousePos.z = 0;

        camera.unproject(mousePos);

    }

    public void zoomCamera(float zoom) {
        float increments = 0.05f;
        float minZoom = 1f;
        float maxZoom = 3f;
        float zoomAmount = zoom * increments;
        if ((camera.zoom + zoomAmount) < minZoom) {
            camera.zoom = minZoom;
        } else if ((camera.zoom + zoomAmount) > maxZoom) {
            camera.zoom = maxZoom;
        } else if (camera.zoom <= maxZoom && camera.zoom >= minZoom) {
            camera.zoom += zoom * increments;
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        zoomCamera(amountY);
        return false;
    }
}
