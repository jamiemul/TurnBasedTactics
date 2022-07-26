package com.zombie.entities;

public class Attributes {

    private Integer dexterity;
    private Integer strength;
    private Integer reactions;
    private Integer melee;
    private Integer firearms;
    private Integer bravery;

    public Attributes(Builder builder) {
        this.dexterity = builder.dexterity;
        this.strength = builder.strength;
        this.reactions = builder.reactions;
        this.melee = builder.melee;
        this.firearms = builder.firearms;
        this.bravery = builder.bravery;
    }

    public static final class Builder  {
        public Integer dexterity;
        public Integer strength;
        public Integer reactions;
        public Integer melee;
        public Integer firearms;
        public Integer bravery;

        public Attributes build() {
            return new Attributes(this);
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

}
