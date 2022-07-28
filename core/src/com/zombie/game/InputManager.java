package com.zombie.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.zombie.entities.GameUnit;
import com.zombie.entities.PlayerCharacter;
import com.zombie.map.GameMap;
import com.zombie.map.MapManager;
import com.zombie.map.Tile;

public class InputManager implements InputProcessor {
    final OrthographicCamera camera;
    final int keyAmount = 7;
    final Input input = Gdx.input;
    final Vector3 mousePos = new Vector3();
    final int tileW = MapManager.TILE_WIDTH;
    final int tileH = MapManager.TILE_HEIGHT;
    boolean leftButtonPressed;
    boolean rightButtonPressed;
    public static INPUT_STATE inputState = INPUT_STATE.map_mode;
    public static SELECT_STATE selectState = SELECT_STATE.pathNotSelected;

    public enum INPUT_STATE {
        menu, inventory, map_mode, map_animation;
    }

    public enum SELECT_STATE {
        pathNotSelected, pathSelected;
    }

    public InputManager(OrthographicCamera camera) {
        this.camera = camera;
        input.setInputProcessor(this);
    }

    public void update() {
        leftButtonPressed = (input.isButtonJustPressed(Input.Buttons.LEFT));
        rightButtonPressed = (input.isButtonJustPressed(Input.Buttons.RIGHT));

        switch (inputState) {
            case map_mode:
                mapMode();
                break;
            case inventory:
            case menu:
                break;
        }
    }

    private void mapMode() {
        if (input.isKeyPressed(Input.Keys.LEFT)) {
            camera.translate(-keyAmount, 0, 0);
        }

        if (input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.translate(keyAmount, 0, 0);
        }

        if (input.isKeyPressed(Input.Keys.UP)) {
            camera.translate(0, keyAmount, 0);
        }

        if (input.isKeyPressed(Input.Keys.DOWN)) {
            camera.translate(0, -keyAmount, 0);
        }

        if (input.isKeyPressed(Input.Keys.SPACE)) {
            //END TURN
        }

        this.selectTile();
    }

    private void selectTile() {

        mousePos.x = input.getX();
        mousePos.y = input.getY();
        mousePos.z = 0;

        camera.unproject(mousePos);

        float offsetX = ((mousePos.x - tileW / 2.f) / tileW);
        float offsetY = ((((mousePos.y - tileH) * -1)) / tileH); //FLIP yPOS as Tile map is only in the -Y axis

        int tileX = (int) ((offsetX + offsetY));
        int tileY = (int) ((offsetY - offsetX));
        for (Tile tile : GameMap.getTiles()) {
            if (tileX >= 0 && tileY >= 0 && tileX < GameMap.getWidth() && tileY < GameMap.getLength()) {
                if (tile.getX() == tileX && tile.getY() == tileY) {

                    if (leftButtonPressed && tile.hasUnit()) {
                        if (MapManager.SELECTED_TILE != tile) {
                            MapManager.SELECTED_TILE = tile;
                            MapManager.SELECTED_UNIT = tile.getUnitOnTile();
                        } else {
                            resetPath();
                        }
                    }

                    if (leftButtonPressed && selectState == SELECT_STATE.pathNotSelected && MapManager.END_TILE != null) {
                        selectState = SELECT_STATE.pathSelected;
                    } else if (leftButtonPressed && selectState == SELECT_STATE.pathSelected) {
                        selectState = SELECT_STATE.pathNotSelected;
                        MapManager.END_TILE = null;
                    }

                    if (MapManager.SELECTED_TILE != tile && MapManager.SELECTED_TILE != null && !tile.hasUnit() && selectState == SELECT_STATE.pathNotSelected) {
                        GameUnit unit = MapManager.SELECTED_TILE.getUnitOnTile();
                        if (unit.getClass() == PlayerCharacter.class) {
                            MapManager.END_TILE = tile;
                            MapManager.clearExplored();
                            MapManager.createPath();
                        }
                    }
                }
            }
        }
    }

    private void resetPath() {
        MapManager.SELECTED_TILE = null;
        MapManager.SELECTED_UNIT = null;
        MapManager.END_TILE = null;
        if (MapManager.path != null) {
            MapManager.path.clear();
            MapManager.clearExplored();
        }
        selectState = SELECT_STATE.pathNotSelected;
    }

    public void zoomCamera(float zoom) {
        float increments = 0.05f;
        float minZoom = 1f;
        float maxZoom = 5.5f;
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
