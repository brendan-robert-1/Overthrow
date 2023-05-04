package com.mygdx.game.encounters.fights;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.screens.state.BaseStats;
import com.mygdx.game.screens.state.Character;
import com.mygdx.game.screens.state.gear.EquippedGear;
import com.mygdx.game.character.plaguedoctor.Bloodlet;
import com.mygdx.game.character.plaguedoctor.Miasma;
import com.mygdx.game.character.plaguedoctor.TossConcoction;
import com.mygdx.game.encounters.OverthrowActs;
import com.mygdx.game.screens.state.EnemySlots;
import com.mygdx.game.screens.state.Character;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaxMan extends Fight{
    public TaxMan(){
        super(NodeType.BOSS_FIGHT, "Boss");
    }
    @Override
    public List<OverthrowActs.ActType> actTypeEncounterable() {
        return Arrays.asList(OverthrowActs.ActType.FARMS);
    }

    private static final int TAX_MAN_STARTING_HP = 20;
    private static final Ability TAX_MAN_STARTING_BASIC_ABILITY_1 = new TossConcoction();
    private static final Ability TAX_MAN_STARTING_BASIC_ABILITY_2 = new Bloodlet();
    private static final Ability TAX_MAN_STARTING_ULT_ABILITY = new Miasma();

    private static final int TAX_COLLECTOR_STARTING_HP = 23;
    private static final Ability TAX_COLLECTOR_STARTING_BASIC_ABILITY_1 = new TossConcoction();
    private static final Ability TAX_COLLECTOR_STARTING_BASIC_ABILITY_2 = new Bloodlet();
    private static final Ability TAX_COLLECTOR_STARTING_ULT_ABILITY = new Miasma();

    @Override
    public EnemySlots startingUnits() {
        return new EnemySlots(
                generateTaxMan(),
                generateTaxCollector(),
                generateTaxCollector(),
                null
        );
    }

    private Character generateTaxMan(){
        return new Character(
                "Tax Man",
                Character.CharacterType.TAX_MAN,
                TAX_MAN_STARTING_HP,
                new EquippedGear(),
                TAX_MAN_STARTING_BASIC_ABILITY_1,
                TAX_MAN_STARTING_BASIC_ABILITY_2,
                TAX_MAN_STARTING_ULT_ABILITY,
                new ArrayList<>(),
                taxmanBaseStats(),
                0
        );
    }




    private Character generateTaxCollector(){
        return new Character(
                "Tax Collector",
                Character.CharacterType.TAX_COLLECTOR,
                TAX_COLLECTOR_STARTING_HP,
                new EquippedGear(),
                TAX_COLLECTOR_STARTING_BASIC_ABILITY_1,
                TAX_COLLECTOR_STARTING_BASIC_ABILITY_2,
                TAX_COLLECTOR_STARTING_ULT_ABILITY,
                new ArrayList<>(),
                collectorBaseStats(),
                0
        );
    }



    private BaseStats collectorBaseStats() {
        return new BaseStats(
                20,
                20,
                20,
                20,
                20
        );
    }


    private BaseStats taxmanBaseStats() {
        return new BaseStats(
                20,
                20,
                20,
                20,
                20
        );
    }




    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TaxMan");
        return sb.toString();
    }
}
