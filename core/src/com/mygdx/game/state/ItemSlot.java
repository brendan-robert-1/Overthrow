package com.mygdx.game.state;

public record ItemSlot(
        String name,
        String description,
        boolean isStackable,
        int quantity
) {

}