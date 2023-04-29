package com.mygdx.game.items;

public class ButcherKnife extends StackableItem{
    public static final String name = "Butcher Knife";
    public static final String description = "Can be used for chopping meat.";



    @Override
    public String name() {
        return ButcherKnife.name;
    }



    @Override
    public String description() {
        return ButcherKnife.description;
    }
}

