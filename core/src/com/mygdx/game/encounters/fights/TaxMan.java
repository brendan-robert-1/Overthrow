package com.mygdx.game.encounters.fights;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;
import com.mygdx.game.screens.widgets.fight.EnemyTeam;
import com.mygdx.game.screens.widgets.fight.FightNode;
import com.mygdx.game.state.Stats;
import com.mygdx.game.state.Character;
import com.mygdx.game.state.gear.EquippedGear;
import com.mygdx.game.character.plaguedoctor.Bloodlet;
import com.mygdx.game.character.plaguedoctor.Miasma;
import com.mygdx.game.character.plaguedoctor.TossConcoction;
import com.mygdx.game.encounters.OverthrowActs;

import java.util.Arrays;
import java.util.List;

public class TaxMan extends FightNode {
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
    public EnemyTeam startingUnits() {
        return new EnemyTeam(
                new CharacterPanel(generateTaxMan()),
                new CharacterPanel(generateTaxCollector()),
                new CharacterPanel(generateTaxCollector()),
                null
        );
    }

    private Character generateTaxMan(){
        return new Character(
                "Tax Man",
                Character.CharacterType.TAX_MAN,
                TAX_MAN_STARTING_HP,
                TAX_MAN_STARTING_BASIC_ABILITY_1,
                TAX_MAN_STARTING_BASIC_ABILITY_2,
                TAX_MAN_STARTING_ULT_ABILITY,
                taxmanBaseStats(),
                0
        );
    }




    private Character generateTaxCollector(){
        return new Character(
                "Tax Collector",
                Character.CharacterType.TAX_COLLECTOR,
                TAX_COLLECTOR_STARTING_HP,
                TAX_COLLECTOR_STARTING_BASIC_ABILITY_1,
                TAX_COLLECTOR_STARTING_BASIC_ABILITY_2,
                TAX_COLLECTOR_STARTING_ULT_ABILITY,
                collectorBaseStats(),
                0
        );
    }



    private Stats collectorBaseStats() {
        return new Stats(
                20,
                20,
                20,
                20,
                20
        );
    }


    private Stats taxmanBaseStats() {
        return new Stats(
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
