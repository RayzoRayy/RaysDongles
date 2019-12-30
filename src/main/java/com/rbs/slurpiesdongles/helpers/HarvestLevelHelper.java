package com.rbs.slurpiesdongles.helpers;


public enum HarvestLevelHelper {
    DIAMOND("diamond", 3),
    GOLD("gold", 0),
    IRON("iron", 2),
    STONE("stone", 1),
    WOOD("wood", 0);



    public final int harvestLevel;
    public final String name;

    HarvestLevelHelper( String name, int harvestLevel) {
        this.harvestLevel = harvestLevel;
        this.name = name();
    }
}
