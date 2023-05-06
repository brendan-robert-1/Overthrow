package com.mygdx.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.widgets.PixelProTextButton;

public class InGameOptionsScreen extends ScreenAdapter {

    private Stage stage;
    private Viewport viewport;
    @Override
    public void show() {
        stage = new Stage();
        viewport = new ScreenViewport();
        Table table = new Table();
        stage.addActor(table);
        table.setFillParent(true); //Table is maximum size of stage
        addMainMenuButtons(table);
        stage.addListener(escapeKeyboardListener());
        Gdx.input.setInputProcessor(stage); //stage being used for button listening
    }


    private void addMainMenuButtons(Table table) {
        addButton(table, "Game Options", new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("New run has been clicked... Good Luck!");

            }
        });
        addButton(table, "Video Settings",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Unlocks has been clicked.");
            }
        });
        addButton(table, "Audio Settings",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Options has been clicked.");
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuOptionsScreen());
            }
        });
        addButton(table, "Back to game",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameStateScreen());
            }
        });
        addButton(table, "Quit to main menu",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new AreYouSureScreen());
            }
        });
    }

    private class AreYouSureScreen extends ScreenAdapter{
        @Override
        public void show() {
            Table table = new Table();
            stage.addActor(table);
            table.setFillParent(true);
            populateAreYouSure(table);
            stage.addListener(escapeKeyboardListener());
            Gdx.input.setInputProcessor(stage);
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
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
                }
            });
        }
    }

    private void addButton(Table table, String name, ClickListener listener){
        PixelProTextButton button = new PixelProTextButton(name, Assets.skin());
        button.getLabel().setAlignment(Align.left);
        button.addListener(listener);
        table.add(button).fillX().padBottom(10);
        table.row();
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

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f,.1f, .15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}