package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.Assets;
import com.mygdx.game.OverthrowScreenAdapter;
import com.mygdx.game.encounters.EnemyCharacter;
import com.mygdx.game.encounters.fights.Fight;
import com.mygdx.game.state.Character;
import com.mygdx.game.state.GameState;

public class FightScreen extends InGameEncounterScreen {
    private GameState gameState;

    public FightScreen(GameState gameState){
        super(gameState);
        this.gameState = gameState;
    }
    @Override
    public void show() {
        // stage.setDebugAll(true);
        populateTeam();
        populateEnemyTeam();

        Gdx.input.setInputProcessor(stage);
    }

    private void populateTeam(){
        Table table = new Table(Assets.skin());
        table.bottom().left();
        table.padLeft(400);
        table.padBottom(300);
        table.setDebug(true);
        Character firstCharacter = gameState.characterSlots().firstCharacter();
        Character secondCharacter = gameState.characterSlots().secondCharacter();
        Character thirdCharacter = gameState.characterSlots().thirdCharacter();
        Character fourthCharacter = gameState.characterSlots().fourthCharacter();
        addCharacterPanel(table, firstCharacter);
        addCharacterPanel(table, secondCharacter);
        addCharacterPanel(table, thirdCharacter);
        addCharacterPanel(table, fourthCharacter);
        table.row();
        table.setFillParent(true);
        stage.addActor(table);
    }

    private void populateEnemyTeam(){
        Table table = new Table(Assets.skin());
        table.bottom().right();
        table.padRight(400);
        table.padBottom(300);
       // table.setDebug(true);
        Fight fightNode = (Fight) gameState.currentNode();
        EnemyCharacter firstEnemy = fightNode.startingUnits().firstCharacter();
        EnemyCharacter secondEnemy = fightNode.startingUnits().secondCharacter();
        EnemyCharacter thirdEnemy = fightNode.startingUnits().thirdCharacter();
        EnemyCharacter fourthEnemy = fightNode.startingUnits().fourthCharacter();
        addEnemyPanel(table, firstEnemy);
        addEnemyPanel(table, secondEnemy);
        addEnemyPanel(table, thirdEnemy);
        addEnemyPanel(table, fourthEnemy);
        table.row();
        table.setFillParent(true);
        stage.addActor(table);
    }


    private void addCharacterPanel(Table mainTable, Character character) {
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
