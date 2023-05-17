package com.mygdx.game.encounters.fights.farms.basic;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.character.enemies.SewerRatBite;
import com.mygdx.game.character.enemies.SewerRatScratch;
import com.mygdx.game.encounters.OverthrowActs;
import com.mygdx.game.screens.encounterscreens.combat.CombatRewards;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;
import com.mygdx.game.screens.widgets.fight.EnemyTeam;
import com.mygdx.game.screens.widgets.fight.FightNode;
import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.state.Character;
import com.mygdx.game.state.Stats;
import com.mygdx.game.state.items.InventoryItemFactory;

import java.util.ArrayList;
import java.util.List;

public class SewerRats extends FightNode {

    private static final int HP = 25;
    private static final Ability ABILITY_1 = new SewerRatScratch();
    private static final Ability ABILITY_2 = new SewerRatBite();
    public SewerRats(){
        super(NodeType.BASIC_FIGHT, "Sewer Rats");
    }
    @Override
    public EnemyTeam startingUnits() {
        return new EnemyTeam(
                new CharacterPanel(sewerRat()),
                new CharacterPanel(sewerRat()),
                null,
                null,
                combatRewards()
        );
    }

    private Character sewerRat(){
        return new Character(
                "Sewer Rat",
                Character.CharacterType.SEWER_RAT,
                HP,
                HP,
                ABILITY_1,
                ABILITY_1,
                ABILITY_2,
                new Stats(2,1,0,0,19),
                0
        );
    }

    private CombatRewards combatRewards(){
        CombatRewards combatRewards = new CombatRewards();
        InventoryItem snugSandals = InventoryItemFactory.getInstance().of(InventoryItem.ItemTypeId.SNUG_SANDALS);
        InventoryItem notebook = InventoryItemFactory.getInstance().of(InventoryItem.ItemTypeId.FRESHMAN_SPELLCRAFT_NOTEBOOK);
        InventoryItem ratTail = InventoryItemFactory.getInstance().of(InventoryItem.ItemTypeId.RAT_TAIL);
        List<InventoryItem> items = new ArrayList<>();
        items.add(snugSandals);
        items.add(notebook);
        items.add(ratTail);
        combatRewards.setItemRewards(items);
        combatRewards.setCoins(13);
        return combatRewards;
    }

    @Override
    public String backgroundAsset() {
        return "town";
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
