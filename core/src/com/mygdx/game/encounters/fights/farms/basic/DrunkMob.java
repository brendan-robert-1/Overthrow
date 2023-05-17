package com.mygdx.game.encounters.fights.farms.basic;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.character.enemies.drunkmob.BottleStab;
import com.mygdx.game.character.enemies.drunkmob.Smooch;
import com.mygdx.game.character.enemies.drunkmob.StoolBash;
import com.mygdx.game.encounters.OverthrowActs;
import com.mygdx.game.screens.encounterscreens.combat.CombatRewards;
import com.mygdx.game.screens.widgets.CombatRewardsWindow;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;
import com.mygdx.game.screens.widgets.fight.EnemyTeam;
import com.mygdx.game.screens.widgets.fight.FightNode;
import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.state.Character;
import com.mygdx.game.state.Stats;
import com.mygdx.game.state.items.InventoryItemFactory;

import java.util.ArrayList;
import java.util.List;

public class DrunkMob extends FightNode {

    private static final int FAT_STARTING_HP = 30 ;
    private static final Ability FAT_STARTING_BASIC_ABILITY_1 = new StoolBash();
    private static final Ability FAT_STARTING_BASIC_ABILITY_2 = new StoolBash();
    private static final Ability FAT_STARTING_ULT_ABILITY = new StoolBash() ;

    private static final int TALL_STARTING_HP = 19;
    private static final Ability TALL_STARTING_BASIC_ABILITY_1 = new BottleStab();
    private static final Ability TALL_STARTING_BASIC_ABILITY_2 = new BottleStab();
    private static final Ability TALL_STARTING_ULT_ABILITY = new BottleStab();

    private static final int WOMAN_STARTING_HP = 18;
    private static final Ability WOMAN_STARTING_BASIC_ABILITY_1 = new Smooch();
    private static final Ability WOMAN_STARTING_BASIC_ABILITY_2 = new Smooch();
    private static final Ability WOMAN_STARTING_ULT_ABILITY = new Smooch();

    private static final int SHORT_STARTING_HP = 22 ;
    private static final Ability SHORT_STARTING_BASIC_ABILITY_2 = new StoolBash();
    private static final Ability SHORT_STARTING_BASIC_ABILITY_1 = new StoolBash();
    private static final Ability SHORT_STARTING_ULT_ABILITY = new StoolBash();



    public DrunkMob(){
        super(NodeType.BASIC_FIGHT, "Drunk Mob");
    }

    @Override
    public EnemyTeam startingUnits() {
        return new EnemyTeam(
                new CharacterPanel(generateDrunkFat()),
                new CharacterPanel(generateDrunkTall()),
                new CharacterPanel(generateWomanCleavage()),
                new CharacterPanel(generateDrunkPopeye()),
                getCombatRewards()
        );
    }



    private CombatRewards getCombatRewards() {
        CombatRewards combatRewards = new CombatRewards();
        List<InventoryItem> items = new ArrayList<>();
        InventoryItem redDress = InventoryItemFactory.getInstance().of(InventoryItem.ItemTypeId.RED_DRESS);
        InventoryItem tankard = InventoryItemFactory.getInstance().of(InventoryItem.ItemTypeId.TANKARD);
        InventoryItem brokenBottle = InventoryItemFactory.getInstance().of(InventoryItem.ItemTypeId.BROKEN_BOTTLE);
        items.add(redDress);
        items.add(tankard);
        items.add(brokenBottle);
        combatRewards.setCoins(11);
        combatRewards.setItemRewards(items);
        return combatRewards;
    }



    private Character generateDrunkFat() {
        return new Character(
                "Drunk Fat Mobster",
                Character.CharacterType.FAT_DRUNK_MOBSTER,
                FAT_STARTING_HP,
                FAT_STARTING_HP,
                FAT_STARTING_BASIC_ABILITY_1,
                FAT_STARTING_BASIC_ABILITY_2,
                FAT_STARTING_ULT_ABILITY,
                new Stats(),
                0
        );
    }

    private Character generateDrunkTall() {
        return new Character(
                "Drunk Tall Mobster",
                Character.CharacterType.TALL_DRUNK_MOBSTER,
                TALL_STARTING_HP,
                TALL_STARTING_HP,
                TALL_STARTING_BASIC_ABILITY_1,
                TALL_STARTING_BASIC_ABILITY_2,
                TALL_STARTING_ULT_ABILITY,
                new Stats(),
                0
        );
    }

    private Character generateWomanCleavage() {
        return new Character(
                "Drunk Woman Mobster",
                Character.CharacterType.WOMAN_DRUNK_MOBSTER,
                WOMAN_STARTING_HP,
                WOMAN_STARTING_HP,
                WOMAN_STARTING_BASIC_ABILITY_1,
                WOMAN_STARTING_BASIC_ABILITY_2,
                WOMAN_STARTING_ULT_ABILITY,
                new Stats(),
                0
        );
    }

    private Character generateDrunkPopeye() {
        return new Character(
                "Drunk Short Mobster",
                Character.CharacterType.SHORT_DRUNK_MOBSTER,
                SHORT_STARTING_HP,
                SHORT_STARTING_HP,
                SHORT_STARTING_BASIC_ABILITY_1,
                SHORT_STARTING_BASIC_ABILITY_2,
                SHORT_STARTING_ULT_ABILITY,
                new Stats(),
                0
        );
    }



    @Override
    public String backgroundAsset() {
        return null;
    }



    @Override
    public String ambientSounds() {
        return null;
    }



    @Override
    public List<OverthrowActs.ActType> actTypeEncounterable() {
        return null;
    }

}



