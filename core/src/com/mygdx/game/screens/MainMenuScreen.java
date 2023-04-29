package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Assets;
import com.mygdx.game.OverthrowScreenAdapter;

public class MainMenuScreen extends OverthrowScreenAdapter {

    @Override
    public void show() {
        Table table = new Table();
        stage.addActor(table);
        table.setFillParent(true); //Table is maxium size of stage
        addMainMenuButtons(table);
        Gdx.input.setInputProcessor(stage); //stage being used for button listening
    }

    private void addMainMenuButtons(Table table) {
        addButton(table, "New Run", new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("New run has been clicked... Good Luck!");
                ((Game) Gdx.app.getApplicationListener()).setScreen(new NewRunScreen());
            }
        });
        addButton(table, "Unlocks",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Unlocks has been clicked.");
            }
        });
        addButton(table, "Options",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Options has been clicked.");
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuOptionsScreen());
            }
        });
        addButton(table, "Match History",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Match History has been clicked.");
            }
        });
        addButton(table, "Quit",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Quit has been clicked. Thanks for playing!");
                Gdx.app.exit();
            }
        });
    }

    private void addButton(Table table, String name, ClickListener listener){
        TextButton button = new TextButton(name, Assets.skin());
        button.addListener(listener);
        table.add(button).fillX().padBottom(10);
        table.row();
    }

    @Override
    public void resize(int width, int height){
        viewport.update(width, height);
    }
}
