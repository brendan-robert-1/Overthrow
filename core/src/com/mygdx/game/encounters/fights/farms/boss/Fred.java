package com.mygdx.game.encounters.fights.farms.boss;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.screens.encounterscreens.combat.CombatRewards;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;
import com.mygdx.game.screens.widgets.fight.EnemyTeam;
import com.mygdx.game.screens.widgets.fight.FightNode;
import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.state.Stats;
import com.mygdx.game.state.gear.EquippedGear;
import com.mygdx.game.character.plaguedoctor.Bloodlet;
import com.mygdx.game.character.plaguedoctor.Miasma;
import com.mygdx.game.character.plaguedoctor.TossConcoction;
import com.mygdx.game.encounters.OverthrowActs;
import com.mygdx.game.state.Character;
import com.mygdx.game.state.items.InventoryItemFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fred extends FightNode {
    public Fred(){
        super(NodeType.BOSS_FIGHT, "Boss");
    }

    @Override
    public String ambientSounds() {
        return null;
    }
    @Override
    public List<OverthrowActs.ActType> actTypeEncounterable() {
        return Arrays.asList(OverthrowActs.ActType.FARMS);
    }

    @Override
    public String backgroundAsset() {
        return "farms-fire";
    }


    private static final int FRED_STARTING_HP = 20;
    private static final Ability FRED_STARTING_BASIC_ABILITY_1 = new TossConcoction();
    private static final Ability FRED_STARTING_BASIC_ABILITY_2 = new Bloodlet();
    private static final Ability FRED_STARTING_ULT_ABILITY = new Miasma();

    @Override
    public EnemyTeam startingUnits() {
        return new EnemyTeam(
                new CharacterPanel(generateFred()),
                null,
                null,
                null,
                getCombatRewards()
        );
    }

    private Character generateFred(){
        return new Character(
                "Fred",
                Character.CharacterType.FRED,
                FRED_STARTING_HP,
                FRED_STARTING_HP,
                FRED_STARTING_BASIC_ABILITY_1,
                FRED_STARTING_BASIC_ABILITY_2,
                FRED_STARTING_ULT_ABILITY,
                baseStats(),
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

    private Stats baseStats() {
        return new Stats(
                50,
                50,
                50,
                50,
                50
        );
    }



    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Fred");
        return sb.toString();
    }
}
