package com.mygdx.game.encounters.fights.farms.boss;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.screens.encounterscreens.combat.CombatRewards;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;
import com.mygdx.game.screens.widgets.fight.EnemyTeam;
import com.mygdx.game.screens.widgets.fight.FightNode;
import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.state.Stats;
import com.mygdx.game.state.Character;
import com.mygdx.game.character.plaguedoctor.Bloodlet;
import com.mygdx.game.character.plaguedoctor.Miasma;
import com.mygdx.game.character.plaguedoctor.TossConcoction;
import com.mygdx.game.encounters.OverthrowActs;
import com.mygdx.game.state.items.InventoryItemFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DebtCollector extends FightNode {
    public DebtCollector(){
        super(NodeType.BOSS_FIGHT, "Boss");
    }
    @Override
    public List<OverthrowActs.ActType> actTypeEncounterable() {
        return Arrays.asList(OverthrowActs.ActType.FARMS);
    }


    @Override
    public String ambientSounds() {
        return null;
    }
    @Override
    public String backgroundAsset() {
        return "BOSS_FIGHT";
    }
    private static final int DEBT_COLLECTOR_STARTING_HP = 100;
    private static final Ability DEBT_COLLECTOR_STARTING_BASIC_ABILITY_1 = new TossConcoction();
    private static final Ability DEBT_COLLECTOR_STARTING_BASIC_ABILITY_2 = new Bloodlet();
    private static final Ability DEBT_COLLECTOR_STARTING_ULT_ABILITY = new Miasma();

    /*
    Quant is the small helper of the tax collector who is holding a giant abacus
     */
    private static final int QUANT_STARTING_HP = 23;
    private static final Ability QUANT_STARTING_BASIC_ABILITY_1 = new TossConcoction();
    private static final Ability QUANT_STARTING_BASIC_ABILITY_2 = new Bloodlet();
    private static final Ability QUANT_STARTING_ULT_ABILITY = new Miasma();

    @Override
    public EnemyTeam startingUnits() {
        return new EnemyTeam(
                new CharacterPanel(generateDebtCollector()),
                new CharacterPanel(generateQuant()),
                null,
                null,
                getCombatRewards()
        );
    }

    private Character generateDebtCollector(){
        return new Character(
                "Debt Collector",
                Character.CharacterType.DEBT_COLLECTOR,
                DEBT_COLLECTOR_STARTING_HP,
                DEBT_COLLECTOR_STARTING_HP, DEBT_COLLECTOR_STARTING_BASIC_ABILITY_1,
                DEBT_COLLECTOR_STARTING_BASIC_ABILITY_2, DEBT_COLLECTOR_STARTING_ULT_ABILITY,
                taxmanBaseStats(),
                0
        );
    }



    public CombatRewards getCombatRewards() {
        CombatRewards combatRewards = new CombatRewards();
        combatRewards.setCoins(12);
        List<InventoryItem> rewards = new ArrayList<>();
        rewards.add(InventoryItemFactory.getInstance().of(InventoryItem.ItemTypeId.SNUG_SANDALS));
        rewards.add(InventoryItemFactory.getInstance().of(InventoryItem.ItemTypeId.RUSTY_DAGGER));
        rewards.add(InventoryItemFactory.getInstance().of(InventoryItem.ItemTypeId.HIDE_SHIELD));
        combatRewards.setItemRewards(rewards);
        return combatRewards;
    }

    private Character generateQuant(){
        return new Character(
                "Quant",
                Character.CharacterType.QUANT, QUANT_STARTING_HP, QUANT_STARTING_HP, QUANT_STARTING_BASIC_ABILITY_1, QUANT_STARTING_BASIC_ABILITY_2, QUANT_STARTING_ULT_ABILITY,
                collectorBaseStats(),
                0
        );
    }



    private Stats collectorBaseStats() {
        return new Stats(
                5,
                1,
                1,
                1,
                13
        );
    }


    private Stats taxmanBaseStats() {
        return new Stats(
                1,
                20,
                20,
                20,
                22
        );
    }




    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TaxMan");
        return sb.toString();
    }
}
