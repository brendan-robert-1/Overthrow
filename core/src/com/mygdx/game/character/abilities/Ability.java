package com.mygdx.game.character.abilities;

public interface Ability{
    String name();
    String description();
    boolean offensiveTargetable();
    boolean selfTargetable();
}
