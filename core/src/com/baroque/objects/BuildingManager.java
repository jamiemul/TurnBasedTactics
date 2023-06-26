package com.baroque.objects;

import com.badlogic.gdx.math.Vector2;
import com.baroque.game.PlayerManager;

import java.util.Arrays;

public class BuildingManager {
    PlayerManager playerManager;


    public Building palace = new Building(true, new Vector2(0, 0), "Palace");
    public Building forest = new Building(true, new Vector2(0, 1), "Forest");
    public Building quarry = new Building(true, new Vector2(0, 2), "Quarry");
    public Building farm = new Building(true, new Vector2(0, 3), "Farm");

    public Building market = new Building("Market");
    public Building printhouse = new Building("Printhouse");
    public Building tavern = new Building("Tavern");
    public Building apothecary = new Building("Apothecary");
    public Building church = new Building("Church");

    public Building townSquare = new Building("Town Square");
    public Building gallery = new Building("Gallery");
    public Building library = new Building("Library");
    public Building docks = new Building("Docks");
    public Building theatre = new Building("Theatre");

    public BuildingManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
        setResourceCostsAndRewards();
    }

    public void placeStarterBuildings() {
        palace.setActive(true);
    }

    public void setActionSlots() {
        //QUARRY
        ActionSlot stone1 = new ActionSlot("Produce stone");
        stone1.setReward(player -> player.resources.stone += 1);
        stone1.unitCosts.add(GameUnit.UnitType.WORKER);
        stone1.resourceCost.wood = 1;
        quarry.addActionsSlot(stone1);

        ActionSlot stone2 = new ActionSlot("Produce stone");
        stone2.setReward(player -> player.resources.stone += 1);
        stone2.unitCosts.addAll(Arrays.asList(GameUnit.UnitType.WORKER));
        stone2.resourceCost.wood = 1;
        quarry.addActionsSlot(stone2);

        ActionSlot stone3 = new ActionSlot("Produce stone");
        stone2.setReward(player -> {
            player.resources.stone += 2;
            player.decreaseHarmony();
        });
        stone3.unitCosts.add(GameUnit.UnitType.WORKER);
        stone3.unitCosts.add(GameUnit.UnitType.WORKER);
        stone3.unitCosts.add(GameUnit.UnitType.WORKER);
        stone3.resourceCost.wood = 1;
        quarry.addActionsSlot(stone3);

        //FOREST
        ActionSlot wood1 = new ActionSlot("Produce wood");
        wood1.setReward(player -> player.resources.wood += 1);
        wood1.unitCosts.add(GameUnit.UnitType.WORKER);
        wood1.resourceCost.wood = 1;
        forest.addActionsSlot(wood1);

        ActionSlot wood2 = new ActionSlot("Produce wood");
        wood2.setReward(player -> player.resources.wood += 1);
        wood2.unitCosts.add(GameUnit.UnitType.WORKER);
        wood2.unitCosts.add(GameUnit.UnitType.WORKER);
        wood2.resourceCost.wood = 1;
        forest.addActionsSlot(wood2);

        ActionSlot wood3 = new ActionSlot("Produce wood");
        wood2.setReward(player -> {
            player.resources.wood += 2;
            player.decreaseHarmony();
        });
        wood3.unitCosts.add(GameUnit.UnitType.WORKER);
        wood3.unitCosts.add(GameUnit.UnitType.WORKER);
        wood3.unitCosts.add(GameUnit.UnitType.WORKER);
        wood3.resourceCost.wood = 1;
        forest.addActionsSlot(wood3);

        //FARM
        ActionSlot wheat1 = new ActionSlot("Produce wheat");
        wheat1.setReward(player -> player.resources.wheat += 1);
        wheat1.unitCosts.add(GameUnit.UnitType.WORKER);
        wheat1.resourceCost.wheat = 1;
        farm.addActionsSlot(wheat1);

        ActionSlot wheat2 = new ActionSlot("Produce wheat");
        wheat2.setReward(player -> player.resources.wheat += 1);
        wheat2.unitCosts.add(GameUnit.UnitType.WORKER);
        wheat2.unitCosts.add(GameUnit.UnitType.WORKER);
        wheat2.resourceCost.wheat = 1;
        farm.addActionsSlot(wheat2);

        ActionSlot wheat3 = new ActionSlot("Produce wheat");
        wheat2.setReward(player -> {
            player.resources.wheat += 2;
            player.decreaseHarmony();
        });
        wheat3.unitCosts.add(GameUnit.UnitType.WORKER);
        wheat3.unitCosts.add(GameUnit.UnitType.WORKER);
        wheat3.unitCosts.add(GameUnit.UnitType.WORKER);
        wheat3.resourceCost.wheat = 1;
        farm.addActionsSlot(wheat3);

        //PALACE
        ActionSlot upgrade = new ActionSlot("Upgrade I");
        upgrade.setReward(player -> player.upgradeBuilding());
        upgrade.unitCosts.add(GameUnit.UnitType.WORKER);
        upgrade.resourceCost.gold = 2;
        palace.addActionsSlot(upgrade);

        ActionSlot upgrade2 = new ActionSlot("Upgrade II");
        upgrade2.setReward(player -> player.upgradeBuilding());
        upgrade2.unitCosts.add(GameUnit.UnitType.WORKER);
        upgrade2.unitCosts.add(GameUnit.UnitType.WORKER);
        upgrade2.resourceCost.gold = 2;
        palace.addActionsSlot(upgrade2);

        ActionSlot divine = new ActionSlot("Divine Right");
        divine.setReward(player -> player.units.add(new GameUnit(GameUnit.UnitType.NOBLE)));
        divine.unitCosts.add(GameUnit.UnitType.WORKER);
        divine.resourceCost.gold = 1;
        palace.addActionsSlot(divine);

        ActionSlot court = new ActionSlot("Kings Court");
        court.setReward(player -> player.drawFealtyCard());
        court.unitCosts.add(GameUnit.UnitType.WORKER);
        court.unitCosts.add(GameUnit.UnitType.NOBLE);
        court.resourceCost.gold = 1;
        palace.addActionsSlot(court);

        // PRINT HOUSE
        ActionSlot ink1 = new ActionSlot("Produce Ink");
        ink1.setReward(player -> player.resources.ink += 1);
        ink1.unitCosts.add(GameUnit.UnitType.WORKER);
        ink1.resourceCost.wood = 1;
        printhouse.addActionsSlot(ink1);

        ActionSlot ink2 = new ActionSlot("Produce Ink");
        ink2.setReward(player -> player.resources.ink += 1);
        ink2.unitCosts.add(GameUnit.UnitType.WORKER);
        ink2.unitCosts.add(GameUnit.UnitType.WORKER);
        ink2.resourceCost.wood = 1;
        printhouse.addActionsSlot(ink2);

        ActionSlot ink3 = new ActionSlot("Produce Ink");
        ink3.setReward(player -> {
            player.resources.ink += 2;
            player.decreaseHarmony();
        });
        ink3.unitCosts.add(GameUnit.UnitType.WORKER);
        ink3.unitCosts.add(GameUnit.UnitType.WORKER);
        ink3.unitCosts.add(GameUnit.UnitType.WORKER);
        ink3.resourceCost.wood = 1;
        printhouse.addActionsSlot(ink3);

        ActionSlot news = new ActionSlot("News Story");
        news.setReward(player -> {
            player.increaseHarmony();
            player.resources.gold += 1;
        });
        news.unitCosts.add(GameUnit.UnitType.WORKER);
        news.unitCosts.add(GameUnit.UnitType.WRITER);
        news.resourceCost.ink = 1;
        printhouse.addActionsSlot(news);

        // MARKET
        ActionSlot buy = new ActionSlot("Buy");
        buy.setReward(player -> player.buyResources());
        buy.unitCosts.add(GameUnit.UnitType.WORKER);
        buy.resourceCost.gold = 1;
        market.addActionsSlot(buy);

        ActionSlot trade = new ActionSlot("Trade");
        trade.setReward(player -> player.rollTradeDice());
        trade.unitCosts.add(GameUnit.UnitType.WORKER);
        trade.resourceCost.gold = 1;
        market.addActionsSlot(trade);

        ActionSlot sell = new ActionSlot("Sell");
        sell.setReward(player -> player.sellResources());
        sell.unitCosts.add(GameUnit.UnitType.WORKER);
        market.addActionsSlot(sell);

        ActionSlot blackMarket = new ActionSlot("Black Market");
        blackMarket.setReward(player -> {
            player.rollTradeDice();
            player.rollTradeDice();
            player.decreaseHarmony();
        });
        blackMarket.unitCosts.add(GameUnit.UnitType.WORKER);
        blackMarket.resourceCost.gold = 1;
        market.addActionsSlot(blackMarket);

        // CHURCH
        ActionSlot prayer1 = new ActionSlot("Prayer");
        prayer1.setReward(player -> player.resources.faith += 1);
        prayer1.unitCosts.add(GameUnit.UnitType.WORKER);
        church.addActionsSlot(prayer1);

        ActionSlot prayer2 = new ActionSlot("Prayer");
        prayer2.setReward(player -> player.resources.faith += 1);
        prayer2.unitCosts.add(GameUnit.UnitType.WORKER);
        prayer2.unitCosts.add(GameUnit.UnitType.WORKER);
        church.addActionsSlot(prayer2);

        ActionSlot ordain = new ActionSlot("Ordain");
        ordain.setReward(player -> player.units.add(new GameUnit(GameUnit.UnitType.PRIEST)));
        ordain.unitCosts.add(GameUnit.UnitType.WORKER);
        ordain.resourceCost.faith = 1;
        church.addActionsSlot(ordain);

        ActionSlot confession = new ActionSlot("Confession");
        confession.setReward(player -> player.increaseHarmony());
        confession.unitCosts.add(GameUnit.UnitType.WORKER);
        confession.unitCosts.add(GameUnit.UnitType.PRIEST);
        confession.resourceCost.faith = 1;
        church.addActionsSlot(confession);

        // TAVERN
        ActionSlot ale1 = new ActionSlot("Produce ale");
        ale1.setReward(player -> player.resources.ale += 1);
        ale1.unitCosts.add(GameUnit.UnitType.WORKER);
        ale1.resourceCost.wheat = 1;
        tavern.addActionsSlot(ale1);

        ActionSlot ale2 = new ActionSlot("Produce ale");
        ale2.setReward(player -> player.resources.ale += 1);
        ale2.unitCosts.add(GameUnit.UnitType.WORKER);
        ale2.unitCosts.add(GameUnit.UnitType.WORKER);
        ale2.resourceCost.wheat = 1;
        tavern.addActionsSlot(ale2);

        ActionSlot recruit = new ActionSlot("Recruit");
        recruit.setReward(player -> {
            player.units.add(new GameUnit(GameUnit.UnitType.WORKER));
        });
        recruit.unitCosts.add(GameUnit.UnitType.WORKER);
        recruit.resourceCost.ale = 1;
        tavern.addActionsSlot(recruit);

        ActionSlot fireside = new ActionSlot("Fireside Tales");
        fireside.setReward(player -> {
            player.increaseEnlightenment();
        });
        fireside.unitCosts.add(GameUnit.UnitType.WORKER);
        fireside.unitCosts.add(GameUnit.UnitType.WRITER);
        fireside.resourceCost.ale = 2;
        tavern.addActionsSlot(fireside);

        // APOTHECARY
        ActionSlot pot1 = new ActionSlot("Produce pot");
        pot1.setReward(player -> player.resources.pot += 1);
        pot1.unitCosts.add(GameUnit.UnitType.WORKER);
        pot1.resourceCost.gold = 1;
        apothecary.addActionsSlot(pot1);

        ActionSlot pot2 = new ActionSlot("Produce pot");
        pot2.setReward(player -> player.resources.pot += 1);
        pot2.unitCosts.add(GameUnit.UnitType.WORKER);
        pot2.unitCosts.add(GameUnit.UnitType.WORKER);
        pot2.resourceCost.gold = 1;
        apothecary.addActionsSlot(pot2);

        ActionSlot pot3 = new ActionSlot("Produce pot");
        pot3.setReward(player -> {
            player.resources.pot += 2;
            player.decreaseHarmony();
        });
        pot3.unitCosts.add(GameUnit.UnitType.WORKER);
        pot3.unitCosts.add(GameUnit.UnitType.WORKER);
        pot3.unitCosts.add(GameUnit.UnitType.WORKER);
        pot3.resourceCost.gold = 1;
        apothecary.addActionsSlot(pot3);

        ActionSlot pot4 = new ActionSlot("Produce pot");
        pot4.setReward(player -> {
            player.resources.pot += 2;
            player.decreaseHarmony();
        });
        pot4.unitCosts.add(GameUnit.UnitType.WORKER);
        pot4.unitCosts.add(GameUnit.UnitType.WORKER);
        pot4.unitCosts.add(GameUnit.UnitType.WORKER);
        pot4.unitCosts.add(GameUnit.UnitType.WORKER);
        pot4.resourceCost.gold = 1;
        apothecary.addActionsSlot(pot4);

        // GALLERY

        // THEATRE

        // LIBRARY

        // DOCKS
    }

    public void setResourceCostsAndRewards() {
        market.buildCost.wood = 2;
        market.setReward(player -> player.increaseIncome());

        printhouse.buildCost.wood = 1;
        printhouse.buildCost.stone = 1;
        printhouse.setReward(player -> player.increaseIncome());

        tavern.buildCost.wood = 1;
        tavern.buildCost.stone = 1;
        tavern.setReward(player -> player.increaseIncome());

        apothecary.buildCost.wood = 1;
        apothecary.buildCost.stone = 1;
        apothecary.setReward(player -> player.increaseIncome());

        church.buildCost.stone = 2;
        church.setReward(player -> player.units.add(new GameUnit(GameUnit.UnitType.PRIEST)));

        townSquare.buildCost.stone = 2;
        townSquare.buildCost.wood = 1;
        townSquare.setReward(player -> player.units.add(new GameUnit(GameUnit.UnitType.WORKER)));

        docks.buildCost.wood = 1;
        docks.buildCost.stone = 1;
        docks.buildCost.gold = 1;

        library.buildCost.wood = 1;
        library.buildCost.stone = 1;
        library.buildCost.ink = 1;
        library.setReward(player -> player.units.add(new GameUnit(GameUnit.UnitType.WRITER)));

        theatre.buildCost.wood = 1;
        theatre.buildCost.stone = 1;
        theatre.buildCost.ale = 1;
        theatre.setReward(player -> player.units.add(new GameUnit(GameUnit.UnitType.PERFORMER)));

        gallery.buildCost.wood = 2;
        gallery.buildCost.pot = 1;
        gallery.setReward(player -> player.units.add(new GameUnit(GameUnit.UnitType.ARTIST)));
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public Building getPalace() {
        return palace;
    }

    public Building getForest() {
        return forest;
    }

    public Building getQuarry() {
        return quarry;
    }

    public Building getFarm() {
        return farm;
    }

    public Building getMarket() {
        return market;
    }

    public Building getPrinthouse() {
        return printhouse;
    }

    public Building getTavern() {
        return tavern;
    }

    public Building getApothecary() {
        return apothecary;
    }

    public Building getChurch() {
        return church;
    }

    public Building getTownSquare() {
        return townSquare;
    }

    public Building getGallery() {
        return gallery;
    }

    public Building getLibrary() {
        return library;
    }

    public Building getDocks() {
        return docks;
    }

    public Building getTheatre() {
        return theatre;
    }
}
