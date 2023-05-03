package com.mygdx.game.encounters.state.items;

public enum ItemType {
    HIDE_SHIELD,
    LEATHER_PANTS,
    MINER_HAT,
    LEATHERWORKER_GLOVES,
    SNUG_SANDALS,
    OVERSIZED_BOOTS,
    MINOR_HEALTH_POT,
    MINOR_POISON_RESIST_POT,
    RUSTY_DAGGER,
    FRESHMAN_SPELLCRAFT_NOTEBOOK,
    TATTERED_SLINGSHOT,
    CLOUDED_POISON_RESIST_OPAL,
    CLOUDED_FIRE_DAMAGE_RUBY,
    CLOUDED_POISON_DAMAGE_JADE,
    CLOUDED_SPEED_CRYSTAL;

    public static ItemType from(String itemTypeStr) {
        return ItemType.valueOf(itemTypeStr);
    }
}
