package com.mygdx.game.screens.widgets.fight;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.Assets;
import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.screens.widgets.*;
import com.mygdx.game.state.Character;

public class AbilitySelectPanel extends Table {

    private HudTooltip hudTooltip;
    private DragAndDrop dragAndDrop;
    public AbilitySelectPanel(HudTooltip hudTooltip){
        this.setVisible(false);
        this.hudTooltip = hudTooltip;
    }



    public void populateAbilities(Character activeCharacter, DragAndDrop dragAndDrop, EnemyTeam enemyTeam, Team team) {
        this.clearChildren();
        this.dragAndDrop = dragAndDrop;
        dragAndDrop.setDragTime(100);
        dragAndDrop.setKeepWithinStage(false);
        this.getChildren().forEach(Actor::remove);
        Ability ability1 = activeCharacter.getFirstBasicAbility();
        Ability ability2 = activeCharacter.getSecondBasicAbility();
        Ability ult = activeCharacter.getUltimateAbility();

        Image ability1Decal = new AbilitySprite(activeCharacter.getFirstBasicAbility().abilityType());
        ability1Decal.setScaling(Scaling.fit);
        ability1Decal.setSize(40,40);

        Image ability2Decal = new AbilitySprite(activeCharacter.getSecondBasicAbility().abilityType());
        ability2Decal.setScaling(Scaling.fit);
        ability2Decal.setSize(40,40);
        ability2Decal.setSize(40,40);

        Image ultDecal = new AbilitySprite(activeCharacter.getUltimateAbility().abilityType());
        ultDecal.setScaling(Scaling.fit);
        ultDecal.setSize(40,40);
        ultDecal.setSize(40,40);




        AbilitySelect ability1Stack = new AbilitySelect(ability1Decal, ability1);
        dragAndDrop.addSource(new AbilityDragSource(ability1Stack,ability1, dragAndDrop, activeCharacter ));
        ability1Stack.addListener(new HudTooltipListener(hudTooltip));

        AbilitySelect ability2Stack = new AbilitySelect(ability2Decal, ability2);
        dragAndDrop.addSource(new AbilityDragSource(ability2Stack,ability2, dragAndDrop, activeCharacter ));
        ability2Stack.addListener(new HudTooltipListener(hudTooltip));

        AbilitySelect ultStack = new AbilitySelect(ultDecal, ult);
        dragAndDrop.addSource(new AbilityDragSource(ultStack,ult, dragAndDrop, activeCharacter ));
        ultStack.addListener(new HudTooltipListener(hudTooltip));
        
        setTargets(dragAndDrop, enemyTeam, team);

        this.add(ability1Stack);
        this.add(ability2Stack);
        this.add(ultStack);
        this.pack();
    }



    private void setTargets(DragAndDrop dragAndDrop, EnemyTeam enemyTeam, Team team) {
        for(Actor enemy :  enemyTeam.getChildren()){
            CharacterPanel enemyCharacterPanel = (CharacterPanel) enemy;
            AbilityDragTarget dragTarget = new AbilityDragTarget(enemy, enemyCharacterPanel.getCharacter());
            dragAndDrop.addTarget(dragTarget);
        }
        for(Actor friendly :  team.getChildren()){
            CharacterPanel friendlyCharacterPanel = (CharacterPanel) friendly;
            AbilityDragTarget dragTarget = new AbilityDragTarget(friendly, friendlyCharacterPanel.getCharacter());
            dragAndDrop.addTarget(dragTarget);
        }

    }
}
