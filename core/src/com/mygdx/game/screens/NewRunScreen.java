package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Assets;
import com.mygdx.game.OverthrowScreenAdapter;
import com.mygdx.game.state.Character;
import com.mygdx.game.state.NewGameGenerator;

import java.util.stream.Stream;

public class NewRunScreen extends OverthrowScreenAdapter {
    @Override
    public void show() {
        Table characters = new Table();
        stage.addActor(characters);
        characters.setFillParent(true);
        populateCharacterList(characters);
        Gdx.input.setInputProcessor(stage);
    }

    private void populateCharacterList(Table characters){
        Label label = new Label("Select a starting character", Assets.skin());
        characters.add(label).padBottom(20);
        characters.row();
        characters.row();
        addButton(characters, "Plague Doctor",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Plague Doctor has been selected.");
                ((Game) Gdx.app.getApplicationListener()).setScreen(
                        new GameStateScreen( NewGameGenerator.generateNewGame(Character.CharacterType.PLAGUE_DOCTOR))
                );
            }
        });
        addButton(characters, "Knight",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Knight has been selected.");
                ((Game) Gdx.app.getApplicationListener()).setScreen(
                        new GameStateScreen( NewGameGenerator.generateNewGame(Character.CharacterType.KNIGHT))
                );
            }
        });
        addButton(characters, "Inventor",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Inventor has been selected.");
                ((Game) Gdx.app.getApplicationListener()).setScreen(
                        new GameStateScreen( NewGameGenerator.generateNewGame(Character.CharacterType.INVENTOR))
                );
            }
        });
        addButton(characters, "Leper",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Leper has been selected.");
                ((Game) Gdx.app.getApplicationListener()).setScreen(
                        new GameStateScreen( NewGameGenerator.generateNewGame(Character.CharacterType.LEPER))
                );
            }
        });
        addBackToMainMenu(characters);

    }

    private void addBackToMainMenu(Table table){
        TextButton button = new TextButton("Back to main menu...", Assets.skin());
        button.addListener(
                new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        System.out.println("Back to main menu.");
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
                    }
                }
        );
        table.add(button).fillX().padBottom(10).padTop(20);
        table.row();
    }

    private void addButton(Table table, String name, ClickListener... listener){
        TextButton button = new TextButton(name, Assets.skin());
        Stream.of(listener).forEach(button::addListener);
        table.add(button).fillX().padBottom(10);
        table.row();
    }

    private void addButton(Table table, String name){
        TextButton button = new TextButton(name, Assets.skin());
        button.setTouchable(Touchable.disabled);
        table.add(button).fillX().padBottom(10);
        table.row();
    }
}
