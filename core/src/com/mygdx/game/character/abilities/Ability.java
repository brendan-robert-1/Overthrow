package com.mygdx.game.character.abilities;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;
import com.mygdx.game.screens.widgets.fight.FightNode;
import com.mygdx.game.state.Character;
public interface Ability{
    public enum AbilityType {
        MIASMA,
        TOSS_CONCOCTION,
        BLOODLET,
        JOUST,
        ARMOR_UP,
        CHALLENGE, HOMESTEADER_DOG_BITE, HOMESTEADER_NOURISH, HOMESTEADER_PITCHFORK_STAB, HOMESTEADER_DOG_GROWL,
        STOOL_BASH,
        SMOOCH,
        ROB,
        BOTTLE_STAB
    }

    public enum DamageType{
        PHYSICAL, MAGICAL, TRUE, HEAL, NONE
    }
    String name();
    String description();
    boolean offensiveTargetable();
    boolean friendlyTargetable();
    boolean selfTargetable();
    void execute(CharacterPanel target, CharacterPanel source, FightNode fight);

    AbilityType abilityType();
    DamageType damageType();
}

