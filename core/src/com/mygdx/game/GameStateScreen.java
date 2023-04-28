package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.state.Character;
import com.mygdx.game.state.GameState;
import com.mygdx.game.state.ItemSlot;
import java.util.stream.Stream;

public class GameStateScreen extends OverthrowScreenAdapter {

    private Skin skin;
    private GameState gameState;

    public GameStateScreen(GameState gameState) {
        this.gameState = gameState;
        skin = Assets.getAssetManager().get(Assets.SKIN);
    }
    @Override
    public void show() {
       // stage.setDebugAll(true);
        populateTopLeftBar();
        populateTopRightBar();
        populateInventory();
        populateTeam();
        populateEnemyTeam();
        Gdx.input.setInputProcessor(stage);
    }

    private void populateTopLeftBar(){
        Table statusTable = new Table(skin);
        statusTable.add(new TextButton("Options", skin));
        statusTable.top().left();
        statusTable.padLeft(20);
        statusTable.padRight(20);
        statusTable.padTop(10);
        statusTable.setHeight(60);
        statusTable.setFillParent(true);
        stage.addActor(statusTable);
    }
    private void populateTopRightBar(){
        Table statusTable = new Table(skin);
        statusTable.add(new TextButton("Coins: " + gameState.coin(), skin));
        statusTable.top().right();
        statusTable.padLeft(20);
        statusTable.padRight(20);
        statusTable.padTop(10);
        statusTable.setFillParent(true);
        stage.addActor(statusTable);
    }

    private void populateInventory(){
        Table table = new Table(skin);
        table.bottom().right();
        table.padLeft(20);
        table.padRight(20);
        table.padTop(10);
        table.setHeight(60);

        for(Integer inventorySlot : gameState.inventory().getInventoryMap().keySet()){
            ItemSlot itemSlot = gameState.inventory().getInventoryMap().get(inventorySlot);
            addButton(table, itemSlot.name() + ": " + itemSlot.quantity());
        }
        table.row();
        table.setFillParent(true);
        stage.addActor(table);
    }

    private void populateTeam(){
        Table table = new Table(skin);
        table.bottom().left();
        table.padLeft(400);
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

    private void populateEnemyTeam(){
        Table table = new Table(skin);
        table.bottom().right();
        table.padRight(400);
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
        Table table = new Table(skin);
        if(character == null){
            Label label = new Label("Empty slot.", skin);
            table.add(label);
            mainTable.add(table);
            return;
        }
        Label label = new Label(character.characterType().toString(), skin);
        Label hp = new Label("HP: " + character.hp(), skin);
        TextButton equippedGear = new TextButton("Gear", skin);
        TextButton ability1 = new TextButton(character.firstBasicAbility().name(), skin);
        TextButton ability2 = new TextButton(character.secondBasicAbility().name(), skin);
        TextButton ultimate = new TextButton(character.ultimateAbility().name(), skin);
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

    private void addButton(Table table, String name, ClickListener... listener){
        TextButton button = new TextButton(name, skin);
        Stream.of(listener).forEach(button::addListener);
        table.add(button).fillX().padBottom(10);
        table.row();
    }
}

