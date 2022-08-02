package com.zombie.game;

public class GameController {

    public static TURN currentTurn = TURN.PLAYER;

    public enum TURN {
        PLAYER, ENEMY
    }

    GameController() {

    }

    public static void endTurn() {
        currentTurn = TURN.ENEMY;
        InputManager.inputState = InputManager.INPUT_STATE.paused;
    }

    public static void endEnemyTurn() {
        currentTurn = TURN.PLAYER;
        InputManager.inputState = InputManager.INPUT_STATE.map_mode;
    }


}
