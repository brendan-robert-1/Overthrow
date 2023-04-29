package com.mygdx.game.items;

public class Bandages extends StackableItem{
public static final String name = "Bandages";
public static final String description = "Removes 3 turns of Bleed from character.";



        @Override
        public String name() {
                return Bandages.name;
        }



        @Override
        public String description() {
                return Bandages.description;
        }
}
