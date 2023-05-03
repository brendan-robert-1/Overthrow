package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Assets;
import com.mygdx.game.OverthrowScreenAdapter;
import com.mygdx.game.screens.encounterscreens.CharacterSplash;
import com.mygdx.game.state.Character;
import com.mygdx.game.state.NewGameGenerator;

import java.util.stream.Stream;

public class NewRunScreen extends OverthrowScreenAdapter {

    @Override
    public void show() {
        TextureRegionDrawable trd = new TextureRegionDrawable(Assets.skin().getRegion("splash"));
        Table table = new Table();
        table.setBackground(trd);
        table.bottom().left();
        table.padLeft(70);
        table.padBottom(70);
        stage.addActor(table);
        table.setFillParent(true);
        populateCharacterList(table);
        Gdx.input.setInputProcessor(stage);
    }

    private void populateCharacterList(Table characters){
        Label label = new Label("Select a starting character", Assets.skin(), "title");
        characters.add(label).padBottom(20);
        characters.row();
        characters.row();
        addButton(characters, "Plague Doctor",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Knight has been selected.");
                CharacterSplash splash = new CharacterSplash(Character.CharacterType.PLAGUE_DOCTOR);
                NewGameGenerator.generateNewGame(Character.CharacterType.PLAGUE_DOCTOR);
                stage.addActor(splash);
                stage.addActor(proceedTable());
            }
        });
        addButton(characters, "Knight",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Knight has been selected.");
                CharacterSplash splash = new CharacterSplash(Character.CharacterType.KNIGHT);
                NewGameGenerator.generateNewGame(Character.CharacterType.KNIGHT);
                stage.addActor(splash);
                stage.addActor(proceedTable());
            }
        });
        addButton(characters, "Inventor",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Inventor has been selected.");
                CharacterSplash splash = new CharacterSplash(Character.CharacterType.INVENTOR);
                NewGameGenerator.generateNewGame(Character.CharacterType.INVENTOR);
                stage.addActor(splash);
                stage.addActor(proceedTable());
            }
        });
        addButton(characters, "Leper",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Leper has been selected.");
                CharacterSplash splash = new CharacterSplash(Character.CharacterType.LEPER);
                NewGameGenerator.generateNewGame(Character.CharacterType.LEPER);
                stage.addActor(splash);
                stage.addActor(proceedTable());
            }
        });
        addBackToMainMenu(characters);
    }

    private Table proceedTable(){
        Table proceedTable = new Table();
        proceedTable.bottom().right();
        proceedTable.padRight(70);
        proceedTable.padBottom(70);
        proceedTable.setFillParent(true);
        ProceedButton button = new ProceedButton();
        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(
                        new GameStateScreen( )
                );
            }
        });
        button.getLabel().setAlignment(Align.left);
        proceedTable.add(button);
        return proceedTable;
    }

    private void addBackToMainMenu(Table table){
        PixelProTextButton button = new PixelProTextButton("Back to main menu...", Assets.skin());
        button.getLabel().setAlignment(Align.left);
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
        PixelProTextButton button = new PixelProTextButton(name, Assets.skin());
        button.getLabel().setAlignment(Align.left);
        Stream.of(listener).forEach(button::addListener);
        table.add(button).fillX().padBottom(10);
        table.row();
    }
}
