package com.mygdx.game.character.abilities;
import com.mygdx.game.state.Character;
public interface Ability{
    String name();
    String description();
    boolean offensiveTargetable();
    boolean selfTargetable();
    void execute(Character target);
}
