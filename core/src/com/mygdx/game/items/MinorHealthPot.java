package com.mygdx.game.items;

import com.mygdx.game.state.ItemSlot;

public class MinorHealthPot extends StackableItem{
    public static final String name = "Minor Health Potion";
    public static final String description = "Heals a small amount of hp to one character.";



    @Override
    public String name() {
        return MinorHealthPot.name;
    }



    @Override
    public String description() {
        return MinorHealthPot.description;
    }
}
