package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.CharacterSpriteFetcher;
import com.mygdx.game.screens.encounterscreens.combat.CombatProcessor;
import com.mygdx.game.encounters.fights.Fight;
import com.mygdx.game.screens.widgets.AbilitiesPanel;
import com.mygdx.game.screens.widgets.AbilityButton;
import com.mygdx.game.screens.widgets.InspectBox;
import com.mygdx.game.state.EnemySlots;
import com.mygdx.game.state.GameState;
import com.mygdx.game.state.Character;

public class FightScreen extends InGameEncounterScreen {
    private GameState gameState = GameState.getInstance();
    private CombatProcessor combatProcessor;
    private Stage stage = StageManager.getInstance().getStage();
    private EnemySlots enemySlots;

    @Override
    public void show() {
        Fight fight = (Fight) gameState.getCurrentNode();
        enemySlots = fight.startingUnits();
        combatProcessor = new CombatProcessor(fight);

        Character activeCharacter = combatProcessor.calculateActiveTurn();
        if(enemySlots.contains(activeCharacter)){
            System.out.println("Taking enemy turn...");
        } else {
            displayPlayerPrompt(activeCharacter);
        }

       // populateTurnQueue();
        populateEnemyTeam();
        stage.addActor(new AbilitiesPanel(activeCharacter));
      //  combatProcessor.processCombat(fight);
    }


    private void displayPlayerPrompt(Character activeCharacter){


    }


    private void populateEnemyTeam(){
        Table entireScreen = new Table();
        entireScreen.setFillParent(true);
        Table table = new Table(Assets.skin());
        Fight fightNode = (Fight) gameState.getCurrentNode();
        Character firstEnemy = fightNode.startingUnits().firstCharacter();
        Character secondEnemy = fightNode.startingUnits().secondCharacter();
        Character thirdEnemy = fightNode.startingUnits().thirdCharacter();
        Character fourthEnemy = fightNode.startingUnits().fourthCharacter();
        table.add(addEnemyPanel(firstEnemy)).expand();
        table.add(addEnemyPanel( secondEnemy)).expand();
        table.add( addEnemyPanel( thirdEnemy)).expand();
        table.add(addEnemyPanel( fourthEnemy)).expand();
        entireScreen.add(table).expand().bottom().right().padBottom(150);
        table.pack();
        stage.addActor(entireScreen);
    }


    private Table addEnemyPanel(Character character) {
        if(character == null){
            return emptyEnemyPanel();
        }
        Table characterPanel = new Table(Assets.skin());
        characterPanel.add(new Label("hp: " + character.getHp(), Assets.skin(), "title")).expandX();
        characterPanel.row();
        characterPanel.add(new Image(Assets.skin().getRegion("enemy-placeholder-medium"))).expand();
        InspectBox characterInspectBox = new InspectBox(character.getName(),
                "hp: " + character.getHp() + "\n" +
                        "armor: " + character.getHp() + "\n"+
                        "magic resistance: " + character.getHp() + "\n"+
                        "physical damage: " + character.getHp() + "\n"+
                        "magic damage: " + character.getHp() + "\n"+
                        "speed: " + character.getHp() + "\n"
        );
        characterPanel.addListener(new RightClickInspectListener(stage, characterInspectBox));
        characterPanel.row();

        // characterPanel.add(abilityPanels);
        characterPanel.defaults().expandX();
        return characterPanel;
    }

    private Table emptyEnemyPanel() {
        Table empty = new Table(Assets.skin());
        empty.defaults().expand().fill();
        empty.add(new Label("    ", Assets.skin()));
        return empty;
    }
}
