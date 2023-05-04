package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Assets;
import com.mygdx.game.combat.CombatProcessor;
import com.mygdx.game.encounters.fights.Fight;
import com.mygdx.game.state.GameState;
import com.mygdx.game.state.Character;

public class FightScreen extends InGameEncounterScreen {
    private GameState gameState = GameState.getInstance();
    private CombatProcessor combatProcessor;

    @Override
    public void show() {
        populateEnemyTeam();
        Fight fight = (Fight) gameState.getCurrentNode();
        combatProcessor = new CombatProcessor();
        combatProcessor.processCombat(fight);
    }



    private void populateEnemyTeam(){
        Table table = new Table(Assets.skin());
        Fight fightNode = (Fight) gameState.getCurrentNode();
        Character firstEnemy = fightNode.startingUnits().firstCharacter();
        Character secondEnemy = fightNode.startingUnits().secondCharacter();
        Character thirdEnemy = fightNode.startingUnits().thirdCharacter();
        Character fourthEnemy = fightNode.startingUnits().fourthCharacter();
        addEnemyPanel(table, firstEnemy);
        addEnemyPanel(table, secondEnemy);
        addEnemyPanel(table, thirdEnemy);
        addEnemyPanel(table, fourthEnemy);
        TextButton proceed = new TextButton("Next Encounter ->", Assets.skin());
        proceed.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Proceeding to next encounter...");
                redirectNextNode();
            }
        });
        table.add(proceed);
        table.row();
        populateEncounter(table);
    }


    private void addEnemyPanel(Table mainTable, Character character) {
        Table table = new Table(Assets.skin());
        if(character == null){
            Label label = new Label("Empty slot.", Assets.skin());
            table.add(label);
            mainTable.add(table);
            return;
        }
        Label label = new Label(character.getCharacterType().toString(), Assets.skin());
        Label hp = new Label("HP: " + character.getHp(), Assets.skin());
        table.add(label);
        table.row();
        table.add(hp);
        mainTable.add(table);
    }
}
