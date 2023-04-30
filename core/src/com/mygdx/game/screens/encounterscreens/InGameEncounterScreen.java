package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Assets;
import com.mygdx.game.OverthrowScreenAdapter;
import com.mygdx.game.screens.InGameOptionsScreen;
import com.mygdx.game.screens.NextEncounterSelectionScreen;
import com.mygdx.game.state.Character;
import com.mygdx.game.state.GameState;

public abstract class InGameEncounterScreen extends OverthrowScreenAdapter {

    private GameState gameState;

    public InGameEncounterScreen(GameState gameState){
        this.gameState = gameState;
        stage.addListener(escapeKeyboardListener());
        populateInGameEncounterScreen();
        populateTeam();
    }

    private void populateTeam(){
        Table table = new Table(Assets.skin());
        table.bottom().left();
        table.padLeft(200);
        table.padBottom(300);
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

    private void addCharacterPanel(Table mainTable, Character character) {
        Table table = new Table(Assets.skin());
        if(character == null){
            Label label = new Label("Empty slot.", Assets.skin());
            table.add(label).pad(20);
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


    private InputListener escapeKeyboardListener (){
        return new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode == Input.Keys.ESCAPE){
                    openOptions();
                }
                return super.keyDown(event, Input.Keys.ESCAPE);
            }
        };
    }

    public void populateInGameEncounterScreen(){
        populateInventory();
        populateTopLeftBar();
        populateFloorCount();
        populateTopRightBar();
    }
    private void populateFloorCount(){
        Table table = new Table(Assets.skin());
        table.top();
        table.padTop(20);
        Label floorLabel = new Label("Floor: " + gameState.currentFloor(), Assets.skin());
        table.add(floorLabel);
        table.setFillParent(true);
        stage.addActor(table);
    }

    private void populateTopLeftBar(){
        Table statusTable = new Table(Assets.skin());
        TextButton options=  new TextButton("Options", Assets.skin());
        options.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                openOptions();
            }
        });
        statusTable.add(options);
        statusTable.top().left();
        statusTable.padLeft(25);
        statusTable.padRight(25);
        statusTable.padTop(25);
        statusTable.setHeight(60);
        statusTable.setFillParent(true);
        stage.addActor(statusTable);
    }
    private void populateTopRightBar(){
        Table statusTable = new Table(Assets.skin());
        TextButton map = new TextButton("Map", Assets.skin());
        map.padLeft(7);
        map.padRight(7);
        map.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Map button pressed.");
            }
        });
        statusTable.add(map).padRight(10);
        TextButton coins = new TextButton("Coins: " + gameState.coin(), Assets.skin());
        coins.setDisabled(true);
        statusTable.add(coins);
        statusTable.top().right();
        statusTable.padLeft(25);
        statusTable.padRight(25);
        statusTable.padTop(25);
        statusTable.setFillParent(true);
        stage.addActor(statusTable);
    }

    private void populateInventory() {
        Table table = new Table(Assets.skin());
        table.bottom().right();
        table.padLeft(25);
        table.padRight(25);
        table.padTop(25);
        table.setHeight(60);
        table.padBottom(25);
        TextButton inventory = new TextButton("Inventory", Assets.skin());
        table.add(inventory).padRight(10);
        TextButton gear = new TextButton("Gear", Assets.skin());
        table.add(gear);
        table.row();
        table.setFillParent(true);
        stage.addActor(table);
    }

    private void openOptions(){
        ((Game) Gdx.app.getApplicationListener()).setScreen(new InGameOptionsScreen(gameState));
    }



    protected GameState getGameState() {
        return gameState;
    }

    protected void redirectNextNode(){
        gameState = gameState.withCurrentFloor(gameState.currentFloor() + 1);
        ((Game) Gdx.app.getApplicationListener()).setScreen(new NextEncounterSelectionScreen(gameState));
    }
}
