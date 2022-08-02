package com.zombie.map;

import com.zombie.entities.AIUnit;
import com.zombie.entities.PlayerUnit;
import com.zombie.game.GameController;
import com.zombie.gfx.TextureManager;
import com.zombie.map.GameMap;
import com.zombie.entities.Tile;
import com.zombie.map.PathFinder;

import java.util.ArrayList;

public class UnitManager {
    ArrayList<PlayerUnit> playerUnits;
    ArrayList<AIUnit> enemyUnits;
    Tile[][] tiles2D;
    PathFinder pathFinder;

    public UnitManager(Tile[][] tiles2D, PathFinder pathFinder) {
        this.pathFinder = pathFinder;
        this.tiles2D = tiles2D;
        playerUnits = new ArrayList<>();
        enemyUnits = new ArrayList<>();

    }

    public void addUnits() {
        for (int i = 0; i < 5; i++) {
            enemyUnits.add(new AIUnit(TextureManager.UNITS.zombie.texture, tiles2D[12 + i][0]));
        }

        playerUnits.add(new PlayerUnit(TextureManager.UNITS.human.texture));
        playerUnits.get(0).updateTileAndSetPosition(tiles2D[GameMap.getWidth() / 2][GameMap.getLength() - 1]);
    }

    public void update() {
        for (PlayerUnit unit : playerUnits) {
            unit.update();
        }

        for (AIUnit unit : enemyUnits) {
            unit.update();
        }

        if (GameController.currentTurn == GameController.TURN.ENEMY) {
            refreshEnemyUnits();
            for (AIUnit unit : enemyUnits) {
                unit.setGoals();
                unit.setPath(pathFinder.findPath(unit.getCurrentTile(), tiles2D[0][0]));
                unit.move();
            }
            GameController.endEnemyTurn();
            refreshPlayerUnits();
        }
    }

    public void refreshPlayerUnits() {
        for (PlayerUnit unit : playerUnits) {
            unit.refreshAvailableTimeUnits();
        }
    }

    public void refreshEnemyUnits() {
        for (AIUnit unit : enemyUnits) {
            unit.refresh();
        }
    }
}
