package com.zombie.entities;

import com.zombie.game.TextureManager;
import com.zombie.map.GameMap;

public class UnitManager {
    GameMap gameMap;
    TextureManager textureManager;

    UnitManager(GameMap map, TextureManager textureManager) {
        this.gameMap = map;
        this.textureManager = textureManager;

    }

}
