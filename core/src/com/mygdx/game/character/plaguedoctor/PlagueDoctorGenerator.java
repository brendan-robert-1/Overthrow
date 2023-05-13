package com.mygdx.game.character.plaguedoctor;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.state.Stats;
import com.mygdx.game.state.Character;

public class PlagueDoctorGenerator {
    private static final int STARTING_HP = 100;
    private static final Ability STARTING_BASIC_ABILITY_1 = new TossConcoction();
    private static final Ability STARTING_BASIC_ABILITY_2 = new Bloodlet();
    private static final Ability STARTING_ULT_ABILITY = new Miasma();

    public static Character generate(){
        return new Character(
                "Plague Doctor",
                Character.CharacterType.PLAGUE_DOCTOR,
                STARTING_HP,
                STARTING_HP,
                STARTING_BASIC_ABILITY_1,
                STARTING_BASIC_ABILITY_2,
                STARTING_ULT_ABILITY,
                baseStats(),
                0
        );
    }

    private static Stats baseStats(){
        return new Stats(
                0,
                0,
                0,
                0,
                17
        );
    }
}
