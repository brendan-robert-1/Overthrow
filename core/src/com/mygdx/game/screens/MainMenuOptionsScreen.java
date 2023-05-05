package com.mygdx.game.screens;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;
import com.mygdx.game.OverthrowScreenAdapter;
import com.mygdx.game.screens.encounterscreens.StageManager;

public class MainMenuOptionsScreen extends OverthrowScreenAdapter {
    @Override
    public void show() {
        Table characters = new Table();
        stage.addActor(characters);
        characters.setFillParent(true);
        populateCharacterList(characters);
        Gdx.input.setInputProcessor(stage);
    }

    private void populateCharacterList(Table characters){
        addButton(characters, "Options");
        characters.row();
        addButton(characters, "Game Settings",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Game Settings has been selected.");
            }
        });
        addButton(characters, "Input Settings",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Input Settings has been selected.");
            }
        });
        addButton(characters, "Credits",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Credits has been selected.");
            }
        });

        addButton(characters, "Back to main menu...",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Back to main menu.");
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
            }
        });
    }

    private void addButton(Table table, String name, ClickListener listener){
        TextButton button = new TextButton(name, Assets.skin());
        button.addListener(listener);
        table.add(button).fillX().padBottom(10);
        table.row();
    }

    private void addButton(Table table, String name){
        TextButton button = new TextButton(name, Assets.skin());
        table.add(button).fillX().padBottom(10);
        table.row();
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
        StageManager.getInstance().getViewport().update(width, height);
    }
}
