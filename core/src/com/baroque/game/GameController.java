package com.baroque.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.baroque.objects.BuildingManager;

public class GameController {


    SceneManager sceneManager;
    PlayerManager playerManager;
    BuildingManager buildingManager;

    public enum GAME_STATE {
        lobby, paused, menu, inventory, map_mode, map_animation;
    }

    GameController(OrthographicCamera camera, SpriteBatch batch) {
        sceneManager = new SceneManager(camera, batch);
        playerManager = new PlayerManager();
    }

    public void update() {
        sceneManager.update();
        buildingManager.update();
    }

    public void startGame() {
        buildingManager = new BuildingManager(playerManager);
        buildingManager.placeStarterBuildings();
    }

}
