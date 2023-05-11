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
        CHALLENGE,
        QUESTION_MARK
    }
    String name();
    String description();
    boolean offensiveTargetable();
    boolean friendlyTargetable();
    boolean selfTargetable();
    void execute(CharacterPanel target, CharacterPanel source, FightNode fight);

    AbilityType abilityType();
}

