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
import com.mygdx.game.state.GameState;
import com.mygdx.game.state.ItemSlot;

import java.util.stream.Stream;

public abstract class InGameEncounterScreen extends OverthrowScreenAdapter {

    private GameState gameState;

    public InGameEncounterScreen(GameState gameState){
        this.gameState = gameState;
        stage.addListener(escapeKeyboardListener());
        populateInGameEncounterScreen();
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
        populateTopRightBar();
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
        statusTable.add(map).padRight(25);
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

    private void populateInventory(){
        Table table = new Table(Assets.skin());
        table.bottom().right();
        table.padLeft(25);
        table.padRight(25);
        table.padTop(25);
        table.setHeight(60);
        table.padBottom(25);
        Label label = new Label("Inventory", Assets.skin());
        table.add(label);
        table.row();
        for(ItemSlot itemSlot : gameState.inventory().getInventoryMap()){
            addButton(table, itemSlot.name() + ": " + itemSlot.quantity());
        }
        table.row();
        table.setFillParent(true);
        stage.addActor(table);
    }

    private void addButton(Table table, String name, ClickListener... listener){
        TextButton button = new TextButton(name, Assets.skin());
        Stream.of(listener).forEach(button::addListener);
        table.add(button).fillX().padBottom(10);
        table.row();
    }

    private void openOptions(){
        ((Game) Gdx.app.getApplicationListener()).setScreen(new InGameOptionsScreen(gameState));
    }



    protected GameState getGameState() {
        return gameState;
    }

    protected void redirectNextNode(){
        ((Game) Gdx.app.getApplicationListener()).setScreen(new NextEncounterSelectionScreen(gameState));
    }
}
