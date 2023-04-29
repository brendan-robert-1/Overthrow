package com.mygdx.game.items;

import com.mygdx.game.state.ItemSlot;

public abstract class StackableItem {
    public abstract String name();
    public abstract String description();
    public ItemSlot one(){
        return new ItemSlot(
                name(),
                description(),
                true,
                1
        );
    }
    public ItemSlot of(int amount){
        return new ItemSlot(
                name(),
                description(),
                true,
                amount
        );
    }
}
