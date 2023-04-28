package com.mygdx.game.state;

import java.util.UUID;

public record GameState (
    UUID runSeed,
    CharacterSlots characterSlots,
    int coin,
    Inventory inventory
){}