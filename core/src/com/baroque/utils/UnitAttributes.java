package com.baroque.utils;

public class UnitAttributes {

    private Integer dexterity;
    private Integer strength;
    private Integer reactions;
    private Integer melee;
    private Integer firearms;
    private Integer bravery;
    private Integer timeUnits;
    private Integer hitPoints;
    private Integer morale;

    public UnitAttributes() {
        this.dexterity = 0;
        this.strength = 0;
        this.reactions = 0;
        this.melee = 0;
        this.firearms = 0;
        this.bravery = 0;
        this.timeUnits = 50;
        this.hitPoints = 50;
        this.morale = 100;
    }

    public UnitAttributes(Builder builder) {
        this.dexterity = builder.dexterity;
        this.strength = builder.strength;
        this.reactions = builder.reactions;
        this.melee = builder.melee;
        this.firearms = builder.firearms;
        this.bravery = builder.bravery;
        this.timeUnits = builder.timeUnits;
        this.hitPoints = builder.hitPoints;
        this.morale = builder.morale;
    }

    public static final class Builder {
        public Integer dexterity;
        public Integer strength;
        public Integer reactions;
        public Integer melee;
        public Integer firearms;
        public Integer bravery;
        public Integer timeUnits;
        public Integer hitPoints;
        public Integer morale;

        public UnitAttributes build() {
            return new UnitAttributes(this);
        }

        public void dexterity(int dexterity) {
            this.dexterity = dexterity;
        }

        public void strength(Integer strength) {
            this.strength = strength;
        }

        public void reactions(Integer reactions) {
            this.reactions = reactions;
        }

        public void melee(Integer melee) {
            this.melee = melee;
        }

        public void firearms(Integer firearms) {
            this.firearms = firearms;
        }

        public void bravery(Integer bravery) {
            this.bravery = bravery;
        }

        public void timeUnits(Integer timeunits) {
            this.timeUnits = timeUnits;
        }

        public void hitPoints(Integer hitPoints) {
            this.hitPoints = hitPoints;
        }

        public void morale(Integer morale) {
            this.morale = morale;
        }

    }

    public Integer getDexterity() {
        return dexterity;
    }

    public void setDexterity(Integer dexterity) {
        this.dexterity = dexterity;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getReactions() {
        return reactions;
    }

    public void setReactions(Integer reactions) {
        this.reactions = reactions;
    }

    public Integer getMelee() {
        return melee;
    }

    public void setMelee(Integer melee) {
        this.melee = melee;
    }

    public Integer getFirearms() {
        return firearms;
    }

    public void setFirearms(Integer firearms) {
        this.firearms = firearms;
    }

    public Integer getBravery() {
        return bravery;
    }

    public void setBravery(Integer bravery) {
        this.bravery = bravery;
    }

    public Integer getTimeUnits() {
        return timeUnits;
    }

    public void setTimeUnits(Integer timeUnits) {
        this.timeUnits = timeUnits;
    }

    public Integer getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(Integer hitPoints) {
        this.hitPoints = hitPoints;
    }

    public Integer getMorale() {
        return morale;
    }

    public void setMorale(Integer morale) {
        this.morale = morale;
    }
}


