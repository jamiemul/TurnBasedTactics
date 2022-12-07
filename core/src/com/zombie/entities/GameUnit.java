package com.zombie.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.zombie.game.InputManager;
import com.zombie.gfx.TextureManager;
import com.zombie.map.GameMap;
import com.zombie.map.MapManager;
import com.zombie.utils.Traits;
import com.zombie.utils.UnitAttributes;

import java.util.ArrayList;

public class GameUnit {
    public int sightDistance;
    public GameMap.DIRECTION directionFacing;
    private UnitAttributes unitAttributes;
    private Tile currentTile;
    private String name;
    private Tile goalTile;
    private int pathWalked;
    private boolean unitMoving;
    private ArrayList<Tile> path;
    private ArrayList<Traits> traits;
    private String textureFileName;
    private int availableTimeUnits;
    private int textureWidth;
    private int textureHeight;
    private Vector2 moveDirection;
    private int heightOffset;
    boolean visible = false;
    private float screenX;
    private float screenY;

    public GameUnit() {
        setAttributes();
    }

    public GameUnit(TextureManager.TEXTURE texture) {
        setAttributes();
        setTexture(texture);
    }

    public GameUnit(TextureManager.TEXTURE texture, Tile tile) {
        setAttributes();
        setTexture(texture);
        updateTileAndSetPosition(tile);
    }

    private void setAttributes() {
        visible = false;
        unitAttributes = new UnitAttributes();
        availableTimeUnits = unitAttributes.getTimeUnits();
    }

    public void updateTileAndSetPosition(Tile tile) {
        this.updateTile(tile);
        this.setPosition(currentTile);
    }

    public void updateTile(Tile tile) {
        if (this.currentTile != null) {
            this.currentTile.removeUnit();
        }
        this.currentTile = tile;
        this.currentTile.addUnit(this);
    }

    public void setPosition(Tile tile) {
        this.screenX = ((tile.getX() - tile.getY()) * (MapManager.TILE_WIDTH / 2));
        this.screenY = -((tile.getY() + tile.getX()) * (MapManager.TILE_HEIGHT / 2));
    }

    public void setTexture(TextureManager.TEXTURE texture) {
        this.textureWidth = texture.getWidth();
        this.textureHeight = texture.getHeight();
        this.heightOffset = MapManager.TILE_HEIGHT / 2;
        this.textureFileName = texture.getName();
    }

    public void setSpriteSheet(TextureManager.TEXTURE texture) {
        this.textureWidth = texture.getWidth();
        this.textureHeight = texture.getHeight();
        this.heightOffset = MapManager.TILE_HEIGHT / 2;
        this.textureFileName = texture.getName();
    }

    public void update() {
        if (unitMoving) {
            float movementMultiplier = 1.5f;
            float x = goalTile.getScreenX() - this.screenX;
            float y = goalTile.getScreenY() - this.screenY;
            moveDirection = new Vector2(x, y).nor();
            Vector2 v1 = new Vector2(this.screenX, this.screenY);
            Vector2 v2 = new Vector2(goalTile.getScreenX(), goalTile.getScreenY());
            double distance = v1.dst(v2);
            this.screenX += moveDirection.x * movementMultiplier;
            this.screenY += moveDirection.y * movementMultiplier;

            if (distance < MapManager.TILE_WIDTH / 2) {
                this.updateTile(goalTile);
            }


            if (path.size() > 0 && distance < 2) {
                if (goalTile == path.get(path.size() - 1)) { //GOAL REACHED
                    this.updateTileAndSetPosition(goalTile);
                    pathWalked = 0;
                    availableTimeUnits -= path.get(0).getPathCost();
                    path.clear();
                    InputManager.inputState = InputManager.INPUT_STATE.map_mode;
                    unitMoving = false;
                } else {
                    pathWalked += 1;
                    this.updateTileAndSetPosition(goalTile);
                    goalTile = path.get(pathWalked);
                }
            }

        }
    }


    public void render(SpriteBatch batch) {
        batch.draw(TextureManager.getAsset(textureFileName), screenX, screenY + heightOffset, textureWidth, textureHeight);
    }

    public void move() {
        this.goalTile = path.get(0);
        this.unitMoving = true;
    }

    public void setPath(ArrayList<Tile> path) {
        this.path = new ArrayList<>(path.size());
        for (Tile tile : path) {
            this.path.add(tile);
        }
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public int getAvailableTimeUnits() {
        return availableTimeUnits;
    }

    public void refreshAvailableTimeUnits() {
        availableTimeUnits = unitAttributes.getTimeUnits();
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public UnitAttributes getAttributes() {
        return unitAttributes;
    }

    public void setAttributes(UnitAttributes unitAttributes) {
        this.unitAttributes = unitAttributes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
