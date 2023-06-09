package com.mygdx.game.character.knight;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.state.Stats;
import com.mygdx.game.state.Character;

public class KnightGenerator {
    private static final int STARTING_HP = 74;
    private static final Ability STARTING_BASIC_ABILITY_1 = new Joust();
    private static final Ability STARTING_BASIC_ABILITY_2 = new ArmorUp();
    private static final Ability STARTING_ULT_ABILITY = new Challenge();

    public static Character generate(){
        return new Character(
                "Knight",
                Character.CharacterType.KNIGHT,
                STARTING_HP,
                100,
                STARTING_BASIC_ABILITY_1,
                STARTING_BASIC_ABILITY_2,
                STARTING_ULT_ABILITY,
                baseStats(), 0
        );
    }

    private static Stats baseStats(){
        return new Stats(
                1,
                1,
                0,
                0,
                15
        );
    }
}
