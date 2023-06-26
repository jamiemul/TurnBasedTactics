package com.baroque.objects;

import com.badlogic.gdx.math.Vector2;

import java.util.List;

public class Building {
    private List<ActionSlot> actionSlots;
    private RewardFunction rewardFunction;
    private Player owner;
    public Resources buildCost;
    private boolean active;
    private Vector2 location;
    private String name;

    public Building(Boolean active, Vector2 location, String name) {
        this.active = active;
        this.location = location;
        this.name = name;
    }

    public Building(String name) {
        this.buildCost = new Resources();
        this.active = false;
        this.name = name;
    }

    public void setBuildCost(Resources buildCost) {
        this.buildCost = buildCost;
    }

    public List<ActionSlot> getActionSlots() {
        return actionSlots;
    }

    public Resources getBuildCost() {
        return buildCost;
    }

    public void addActionsSlot(ActionSlot slot) {
        actionSlots.add(slot);
    }

    public void setReward(RewardFunction rewardFunction) {
        this.rewardFunction = rewardFunction;
    }


    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Vector2 getLocation() {
        return location;
    }

    public void setLocation(Vector2 location) {
        this.location = location;
    }

    public void performReward(Player player) {
        if (rewardFunction != null) {
            rewardFunction.applyReward(player);
        }
    }

}
