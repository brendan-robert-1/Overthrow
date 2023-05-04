package com.mygdx.game.character.plaguedoctor;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.screens.state.BaseStats;
import com.mygdx.game.screens.state.gear.EquippedGear;
import com.mygdx.game.screens.state.Character;

import java.util.ArrayList;

public class PlagueDoctorGenerator {
    private static final int STARTING_HP = 100;
    private static final Ability STARTING_BASIC_ABILITY_1 = new TossConcoction();
    private static final Ability STARTING_BASIC_ABILITY_2 = new Bloodlet();
    private static final Ability STARTING_ULT_ABILITY = new Miasma();

    public static Character generateStartingPlagueDoctor(){
        return new Character(
                "Plague Doctor",
                Character.CharacterType.PLAGUE_DOCTOR,
                STARTING_HP,
                new EquippedGear(),
                STARTING_BASIC_ABILITY_1,
                STARTING_BASIC_ABILITY_2,
                STARTING_ULT_ABILITY,
                new ArrayList<>(),
                baseStats(),
                0
        );
    }

    private static BaseStats baseStats(){
        return new BaseStats(
                50,
                50,
                50,
                50,
                50
        );
    }
}
