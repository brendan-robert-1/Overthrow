package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.state.GameState;
import com.mygdx.game.state.ItemSlot;

import java.util.stream.Stream;

public class GameStateScreen extends OverthrowScreenAdapter {
    private AssetManager assetManager;

    private Skin skin;
    private GameState gameState;

    public GameStateScreen(AssetManager assetManager, GameState gameState) {
        this.assetManager = assetManager;
        this.gameState = gameState;
        skin = assetManager.get(Assets.SKIN);
    }
    @Override
    public void show() {
        viewport = new ExtendViewport(1280, 720);
        stage = new Stage(viewport);
        Table characters = new Table();
        stage.addActor(characters);
        characters.setFillParent(true);
        populateCharacterList(characters);
        Gdx.input.setInputProcessor(stage);
    }

    private void populateCharacterList(Table table){
        addButton(table, "Coin: " + gameState.coin());
        addButton(table, "Inventory");
        for(Integer inventorySlot : gameState.inventory().getInventoryMap().keySet()){
            ItemSlot itemSlot = gameState.inventory().getInventoryMap().get(inventorySlot);
            addButton(table, itemSlot.name() + ": " + itemSlot.quantity());
        }
        addButton(table, "Options",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Options has been clicked.");
                ((Game) Gdx.app.getApplicationListener()).setScreen(new OptionsScreen(assetManager));
            }
        });
        addButton(table, "Quit to main menu",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Quit to main menu.");
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen(assetManager));
            }
        });
        table.row();

    }

    private void addButton(Table table, String name, ClickListener... listener){
        TextButton button = new TextButton(name, skin);
        Stream.of(listener).forEach(button::addListener);
        table.add(button).fillX().padBottom(10);
        table.row();
    }
}

