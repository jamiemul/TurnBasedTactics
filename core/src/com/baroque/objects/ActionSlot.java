package com.baroque.objects;

import java.util.List;

public class ActionSlot {
    public List<GameUnit.UnitType> unitCosts;
    public Resources resourceCost;
    private RewardFunction rewardFunction;
    private String name;

    public ActionSlot(String name) {
        this.name = name;
    }

    public ActionSlot(List<GameUnit.UnitType> units, Resources resourceCost) {
        this.resourceCost = resourceCost;
        this.unitCosts = units;
    }

    public void setReward(RewardFunction rewardFunction) {
        this.rewardFunction = rewardFunction;
    }

    public void performReward(Player player) {
        if (rewardFunction != null) {
            rewardFunction.applyReward(player);
        }
    }
}
