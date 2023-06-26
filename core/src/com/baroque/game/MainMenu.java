package com.baroque.game;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MainMenu implements Scene {
    private Table table;
    private Skin skin;

    public MainMenu(Table table, Skin skin) {
        this.table = table;
        this.skin = skin;
    }

    public void create() {
        TextButton button = new TextButton("Start", skin);
        table.add(button).pad(10f);
        // Add more UI elements and set up event listeners as needed
    }

    public void update() {
        // Update the menu scene logic, if required
    }

    public void dispose() {
        // Dispose any resources used by the menu scene, if needed
    }

}
