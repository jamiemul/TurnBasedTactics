package com.baroque.objects;

public class GameUnit {
    public enum UnitType {
        WORKER, PRIEST, ARTIST, WRITER, PERFORMER, NOBLE
    }

    UnitType unitType;
    boolean available = true;
    ActionSlot slotLocation;

    public GameUnit(UnitType type) {
        this.unitType = type;
        this.available = true;
    }

    public void setLocation(ActionSlot slot) {
        available = false;
        this.slotLocation = slot;
    }

    public UnitType getType() {
        return unitType;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public void recall() {
        this.available = true;
        this.slotLocation = null;
    }
}

