package com.baroque.objects;

public class Resources {
    int wheat;
    int intrigue;
    int faith;
    int knowledge;
    int ink;
    int ale;
    int gold;
    int pot;
    int wood;
    int stone;
    int influence;

    public boolean hasEnough(Resources cost) {
        return stone >= cost.stone &&
                wood >= cost.wood &&
                gold >= cost.gold &&
                wheat >= cost.wheat &&
                intrigue >= cost.intrigue &&
                ale >= cost.ale &&
                ink >= cost.ink &&
                knowledge >= cost.knowledge &&
                pot >= cost.pot &&
                faith >= cost.faith &&
                influence >= cost.influence;
    }

    public Resources() {
        wheat = 0;
        intrigue = 0;
        faith = 0;
        knowledge = 0;
        ink = 0;
        ale = 0;
        gold = 0;
        pot = 0;
        wood = 0;
        stone = 0;
        influence = 0;
    }

}
