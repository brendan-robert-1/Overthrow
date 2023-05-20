package com.mygdx.game.screens.widgets.fight;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.Assets;
import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.character.abilities.Barefists;
import com.mygdx.game.character.abilities.WeaponAbilityMapper;
import com.mygdx.game.screens.widgets.*;
import com.mygdx.game.screens.widgets.inventory.EquipSlots;
import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.screens.widgets.inventory.InventoryUi;
import com.mygdx.game.state.Character;

import java.util.List;
import java.util.Map;

public class AbilitySelectPanel extends Table {

    private HudTooltip hudTooltip;
    private DragAndDrop dragAndDrop;
    private CharacterPanel activeCharacterPanel;
    private EnemyTeam enemyTeam;
    public AbilitySelectPanel(HudTooltip hudTooltip){
        this.setVisible(false);
        this.hudTooltip = hudTooltip;
    }


    public void update() {
        this.clearChildren();
        if(dragAndDrop == null || activeCharacterPanel == null || enemyTeam == null){
            return;
        }else {
            populateAbilities(activeCharacterPanel, dragAndDrop, enemyTeam);
        }
        this.pack();
    }

    public void populateAbilities(CharacterPanel activeCharacterPanel, DragAndDrop dragAndDrop, EnemyTeam enemyTeam) {
        this.clearChildren();
        this.dragAndDrop = dragAndDrop;
        this.activeCharacterPanel = activeCharacterPanel;
        this.enemyTeam = enemyTeam;
        dragAndDrop.setDragTime(100);
        dragAndDrop.setKeepWithinStage(false);
        this.getChildren().forEach(Actor::remove);

        Map<InventoryItem.ItemTypeId, List<Ability>> abilitiesMap = WeaponAbilityMapper.getInstance().getItemToAbilities();

        EquipSlots equipSlots = activeCharacterPanel.getEquipSlots();
        InventoryItem weapon1 = equipSlots.getWeapon1Slot().getTopInventoryItem();
        InventoryItem weapon2 = equipSlots.getWeapon2Slot().getTopInventoryItem();

        //TODO refactor to one method
        if(weapon1 != null){
            List<Ability> weapon1Abilities = abilitiesMap.get(weapon1.getItemTypeId());
            if(weapon1Abilities != null){
                for(Ability ability : weapon1Abilities){
                    Image ability1Decal = new AbilitySprite(weapon1.getItemTypeId());
                    ability1Decal.setScaling(Scaling.fit);
                    ability1Decal.setSize(40,40);

                    AbilitySelect ability1Stack = new AbilitySelect(ability1Decal, ability);
                    dragAndDrop.addSource(new AbilityDragSource(ability1Stack,ability, dragAndDrop, activeCharacterPanel ));
                    ability1Stack.addListener(new HudTooltipListener());
                    this.add(ability1Stack);
                }
            }

        }
        if(weapon2 != null){
            List<Ability> weapon2Abilities = abilitiesMap.get(weapon2.getItemTypeId());
            if(weapon2Abilities != null){
                for(Ability ability : weapon2Abilities){
                    Image ability1Decal = new AbilitySprite(weapon2.getItemTypeId());
                    ability1Decal.setScaling(Scaling.fit);
                    ability1Decal.setSize(40,40);

                    AbilitySelect ability1Stack = new AbilitySelect(ability1Decal, ability);
                    dragAndDrop.addSource(new AbilityDragSource(ability1Stack,ability, dragAndDrop, activeCharacterPanel ));
                    ability1Stack.addListener(new HudTooltipListener());
                    this.add(ability1Stack);
                }
            }

        }
        if(weapon2 == null && weapon1 == null){
            Ability barefists = new Barefists();
            Image fistsDecal = new AbilitySprite(InventoryItem.ItemTypeId.BAREFISTS);//TODO make fist sprite
            fistsDecal.setScaling(Scaling.fit);
            fistsDecal.setSize(40,40);

            AbilitySelect abilityStack = new AbilitySelect(fistsDecal, barefists);
            dragAndDrop.addSource(new AbilityDragSource(abilityStack,barefists, dragAndDrop, activeCharacterPanel ));
            abilityStack.addListener(new HudTooltipListener());
            this.add(abilityStack);
        }
        setTargets(dragAndDrop, enemyTeam);
        this.pack();
    }



    private void setTargets(DragAndDrop dragAndDrop, EnemyTeam enemyTeam) {
        for(Actor enemy :  enemyTeam.getChildren()){
            CharacterPanel enemyCharacterPanel = (CharacterPanel) enemy;
            AbilityDragTarget dragTarget = new AbilityDragTarget(enemy, enemyCharacterPanel);
            dragAndDrop.addTarget(dragTarget);
        }
        for(Actor friendly :  Team.getInstance().getChildren()){
            CharacterPanel friendlyCharacterPanel = (CharacterPanel) friendly;
            AbilityDragTarget dragTarget = new AbilityDragTarget(friendly, friendlyCharacterPanel);
            dragAndDrop.addTarget(dragTarget);
        }

    }



}
