package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Assets;
import com.mygdx.game.OverthrowScreenAdapter;
import com.mygdx.game.encounters.EnemyCharacter;
import com.mygdx.game.encounters.fights.Fight;
import com.mygdx.game.state.Character;
import com.mygdx.game.state.GameState;

public class FightScreen extends InGameEncounterScreen {
    private GameState gameState = GameState.getInstance();

    @Override
    public void show() {
        // stage.setDebugAll(true);
        populateEnemyTeam();
    }

    private void populateEnemyTeam(){
        Table table = new Table(Assets.skin());
        Fight fightNode = (Fight) gameState.getCurrentNode();
        EnemyCharacter firstEnemy = fightNode.startingUnits().firstCharacter();
        EnemyCharacter secondEnemy = fightNode.startingUnits().secondCharacter();
        EnemyCharacter thirdEnemy = fightNode.startingUnits().thirdCharacter();
        EnemyCharacter fourthEnemy = fightNode.startingUnits().fourthCharacter();
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


    private void addEnemyPanel(Table mainTable, EnemyCharacter character) {
        Table table = new Table(Assets.skin());
        if(character == null){
            Label label = new Label("Empty slot.", Assets.skin());
            table.add(label);
            mainTable.add(table);
            return;
        }
        Label label = new Label(character.characterType().toString(), Assets.skin());
        Label hp = new Label("HP: " + character.hp(), Assets.skin());
        TextButton equippedGear = new TextButton("Gear", Assets.skin());
        TextButton ability1 = new TextButton(character.firstBasicAbility().name(), Assets.skin());
        TextButton ability2 = new TextButton(character.secondBasicAbility().name(), Assets.skin());
        TextButton ultimate = new TextButton(character.ultimateAbility().name(), Assets.skin());
        table.add(label);
        table.row();
        table.add(hp);
        table.row();
        table.add(ability1);
        table.row();
        table.add(ability2);
        table.row();
        table.add(ultimate);
        table.row();
        table.add(equippedGear);
        mainTable.add(table);
    }
}
