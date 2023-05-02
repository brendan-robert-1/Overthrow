package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Assets;
import com.mygdx.game.OverthrowScreenAdapter;
import com.mygdx.game.state.GameState;

public class InGameOptionsScreen extends OverthrowScreenAdapter {

    private GameState gameState = GameState.getInstance();


    @Override
    public void show() {
        Table characters = new Table();
        stage.addActor(characters);
        characters.setFillParent(true);
        populateCharacterList(characters);
        stage.addListener(escapeKeyboardListener());
    }



    private void populateCharacterList(Table table) {
        addButton(table, "Game Settings", new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Game Settings has been selected.");
            }
        });
        addButton(table, "Input Settings", new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Input Settings has been selected.");
            }
        });
        addButton(table, "Back to game...", new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Back to game.");
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameStateScreen());
            }
        });
        addButton(table, "Quit to main menu...", new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Back to game.");
                ((Game) Gdx.app.getApplicationListener()).setScreen(new AreYouSureScreen());
            }
        });
    }

    private class AreYouSureScreen extends OverthrowScreenAdapter{
        @Override
        public void show() {
            Table table = new Table();
            stage.addActor(table);
            table.setFillParent(true);
            populateAreYouSure(table);
            stage.addListener(escapeKeyboardListener());
        }
        private void populateAreYouSure(Table table){
            addButton(table, "Return to game", new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println("Return to game.");
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new GameStateScreen());
                }
            });
            addButton(table, "Quit to main menu", new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println("Quit to main menu.");
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen()); //TODO add save game
                }
            });
        }
    }

    private InputListener escapeKeyboardListener (){
        return new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode == Input.Keys.ESCAPE){
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new GameStateScreen());
                }
                return super.keyDown(event, Input.Keys.ESCAPE);
            }
        };
    }



    private void addButton(Table table, String name, ClickListener listener) {
        TextButton button = new TextButton(name, Assets.skin());
        button.addListener(listener);
        table.add(button).fillX().padBottom(10);
        table.row();
    }

}
