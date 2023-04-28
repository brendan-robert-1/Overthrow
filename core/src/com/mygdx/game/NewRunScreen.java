package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.state.Character;
import com.mygdx.game.state.NewGameGenerator;

import java.util.stream.Stream;

public class NewRunScreen extends OverthrowScreenAdapter {
    private AssetManager assetManager;
    private Table characters;
    private Skin skin;

    public NewRunScreen(AssetManager assetManager) {
        this.assetManager = assetManager;
        skin = assetManager.get(Assets.SKIN);
    }
    @Override
    public void show() {
        viewport = new ExtendViewport(1280, 720);
        stage = new Stage(viewport);
        characters = new Table();
        stage.addActor(characters);
        characters.setFillParent(true);
        populateCharacterList(characters);
        Gdx.input.setInputProcessor(stage);
    }

    private void populateCharacterList(Table characters){
        addButton(characters, "Choose a Starting Character...");
        characters.row();
        addButton(characters, "Plague Doctor",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Knight has been selected.");
                ((Game) Gdx.app.getApplicationListener()).setScreen(
                        new GameStateScreen(assetManager, NewGameGenerator.generateNewGame(Character.CharacterType.PLAGUE_DOCTOR))
                );
            }
        });
        addButton(characters, "Knight",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Knight has been selected.");
                ((Game) Gdx.app.getApplicationListener()).setScreen(
                        new GameStateScreen(assetManager, NewGameGenerator.generateNewGame(Character.CharacterType.KNIGHT))
                );
            }
        });
        addButton(characters, "Inventor",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Inventor has been selected.");
                ((Game) Gdx.app.getApplicationListener()).setScreen(
                        new GameStateScreen(assetManager, NewGameGenerator.generateNewGame(Character.CharacterType.INVENTOR))
                );
            }
        });
        addButton(characters, "Leper",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Leper has been selected.");
                ((Game) Gdx.app.getApplicationListener()).setScreen(
                        new GameStateScreen(assetManager, NewGameGenerator.generateNewGame(Character.CharacterType.LEPER))
                );
            }
        });
        addButton(characters, "Back to main menu...",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Back to main menu.");
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen(assetManager));
            }
        });
    }

    private void addButton(Table table, String name, ClickListener... listener){
        TextButton button = new TextButton(name, skin);
        Stream.of(listener).forEach(button::addListener);
        table.add(button).fillX().padBottom(10);
        table.row();
    }

    private void addButton(Table table, String name){
        TextButton button = new TextButton(name, skin);
        button.setTouchable(Touchable.disabled);
        table.add(button).fillX().padBottom(10);
        table.row();
    }
}
