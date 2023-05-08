package com.mygdx.game.character.abilities;
import com.mygdx.game.state.Character;
public interface Ability{
    public enum AbilityType {
        MIASMA,
        TOSS_CONCOCTION,
        BLOODLET
    }
    String name();
    String description();
    boolean offensiveTargetable();
    boolean selfTargetable();
    void execute(Character target, Character source);
    AbilityType abilityType();
}

