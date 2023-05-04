package com.mygdx.game.character.knight;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.state.BaseStats;
import com.mygdx.game.state.gear.EquippedGear;
import com.mygdx.game.character.plaguedoctor.Bloodlet;
import com.mygdx.game.character.plaguedoctor.Miasma;
import com.mygdx.game.character.plaguedoctor.TossConcoction;
import com.mygdx.game.state.Character;

import java.util.ArrayList;

public class KnightGenerator {
    private static final int STARTING_HP = 100;
    private static final Ability STARTING_BASIC_ABILITY_1 = new TossConcoction();
    private static final Ability STARTING_BASIC_ABILITY_2 = new Bloodlet();
    private static final Ability STARTING_ULT_ABILITY = new Miasma();

    public static Character generateStartingPlagueDoctor(){
        return new Character(
                "Knight",
                Character.CharacterType.KNIGHT,
                STARTING_HP,
                new EquippedGear(),
                STARTING_BASIC_ABILITY_1,
                STARTING_BASIC_ABILITY_2,
                STARTING_ULT_ABILITY,
                new ArrayList<>(),
                baseStats(), 0
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
