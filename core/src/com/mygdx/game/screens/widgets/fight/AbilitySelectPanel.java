package com.mygdx.game.screens.widgets.fight;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.Assets;
import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.screens.widgets.AbilitySprite;
import com.mygdx.game.screens.widgets.Team;
import com.mygdx.game.state.Character;
import com.mygdx.game.state.GameState;

import java.util.Objects;

public class AbilitySelectPanel extends Table {

    private DragAndDrop dragAndDrop;
    public AbilitySelectPanel(){
        this.setVisible(false);
    }



    public void populateAbilities(Character activeCharacter, DragAndDrop dragAndDrop, EnemyTeam enemyTeam, Team team) {
        this.dragAndDrop = dragAndDrop;
        dragAndDrop.setDragTime(100);
        dragAndDrop.setKeepWithinStage(false);
        this.getChildren().forEach(Actor::remove);
        Ability ability1 = activeCharacter.getFirstBasicAbility();
        Ability ability2 = activeCharacter.getSecondBasicAbility();
        Ability ult = activeCharacter.getUltimateAbility();

        Image ability1Image = new AbilitySprite(activeCharacter.getFirstBasicAbility().abilityType());
        ability1Image.setScaling(Scaling.fit);
        ability1Image.setSize(40,40);

        Image ability2Image = new AbilitySprite(activeCharacter.getSecondBasicAbility().abilityType());
        ability2Image.setScaling(Scaling.fit);
        ability2Image.setSize(40,40);
        ability2Image.setSize(40,40);

        Image ultImage = new AbilitySprite(activeCharacter.getUltimateAbility().abilityType());
        ultImage.setScaling(Scaling.fit);
        ultImage.setSize(40,40);
        ultImage.setSize(40,40);


        Image background = new Image(Assets.skin().getPatch("inventory-background"));
        background.setSize(52,52);
        Stack ability1Stack = new Stack();
        ability1Stack.add(background);
        ability1Stack.add(ability1Image);
        dragAndDrop.addSource(new AbilityDragSource(ability1Stack,ability1, dragAndDrop, activeCharacter ));

        background = new Image(Assets.skin().getPatch("inventory-background"));
        background.setSize(52,52);
        Stack ability2Stack = new Stack();
        ability2Stack.add(background);
        ability2Stack.add(ability2Image);
        dragAndDrop.addSource(new AbilityDragSource(ability2Stack,ability2, dragAndDrop, activeCharacter ));

        background = new Image(Assets.skin().getPatch("inventory-background"));
        background.setSize(52,52);
        Stack ultStack = new Stack();
        ultStack.add(background);
        ultStack.add(ultImage);
        dragAndDrop.addSource(new AbilityDragSource(ultStack,ult, dragAndDrop, activeCharacter ));
        
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
