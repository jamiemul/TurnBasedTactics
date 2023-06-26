package com.baroque.objects;

import com.baroque.game.PlayerManager;

import java.util.ArrayList;
import java.util.List;

public class Player {

    public final static int MAX_INCOME = 6;
    public final static int MIN_INCOME = -3;
    public final static int MAX_TRACK = 6;
    public final static int STARTER_WORKERS = 4;
    public static int MAX_FEALTY_CARDS = 5;

    public PlayerManager.PLAYER_COLOR player_color;

    List<GameUnit> units;
    int actions;
    String playerName;
    int playerNum;
    int income;
    int harmony;
    int piety;
    int enlightenment;
    int fealty;
    int artisanship;

    ArrayList<Card> cards;
    Resources resources;

    public Player(String name, int num) {
        this.playerNum = num;
        this.playerName = name;
        for (int i = 0; i < STARTER_WORKERS; i++) {
            this.units.add(new GameUnit(GameUnit.UnitType.WORKER));
        }
        this.units.add(new GameUnit(GameUnit.UnitType.WORKER));
        this.income = 0;
        this.harmony = 4;
        this.resources.gold = 1;
        this.piety = 0;
        this.enlightenment = 0;
        this.fealty = 0;
        this.artisanship = 0;
        this.actions = 2;
        resources = new Resources();
    }

    public int getPiety() {
        return piety;
    }

    public void increasePiety() {
        if (this.piety < MAX_TRACK) {
            this.piety += 1;
        }
    }

    public int getEnlightenment() {
        return enlightenment;
    }

    public void increaseEnlightenment() {
        if (this.enlightenment < MAX_TRACK) {
            this.enlightenment += 1;
            this.actions = PlayerManager.ENLIGHTENMENT_ACTIONS.get(this.enlightenment);
        }
    }

    public int getFealty() {
        return fealty;
    }

    public void increaseFealty() {
        if (this.enlightenment < MAX_TRACK) {
            this.fealty += 1;
        }
    }

    public int getArtisanship() {
        return artisanship;
    }

    public void increaseArtisanship() {
        if (this.artisanship < MAX_TRACK) {
            this.artisanship += 1;
            this.income += 1;
        }
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }


    public int getIncome() {
        return income;
    }

    public void increaseIncome() {
        if (this.income < MAX_INCOME) {
            this.income += 1;
        }
    }

    public void decreaseIncome() {
        if (this.income > MIN_INCOME) {
            this.income -= 1;
        }
    }

    public int getHarmony() {
        return harmony;
    }

    public void increaseHarmony() {
        if (this.harmony < MAX_TRACK) {
            this.harmony += 1;
        }
    }

    public void decreaseHarmony() {
        if (this.harmony > 0 && this.piety < this.harmony) {
            this.harmony -= 1;
        }
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public void rollTradeDice() {

    }

    public void buyResources() {

    }

    public void sellResources() {

    }

    public void upgradeBuilding() {

    }

    public void drawFealtyCard() {

    }
}

