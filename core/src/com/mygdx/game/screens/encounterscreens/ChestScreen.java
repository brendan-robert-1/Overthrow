package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.widgets.EntireInGameScreenTable;

import javax.swing.text.View;

public class ChestScreen extends ScreenAdapter {
    private Stage stage;
    private Viewport viewport;
    @Override
    public void show() {
       stage = new Stage();
       viewport = new ScreenViewport();
       Table entireScreen = new EntireInGameScreenTable();
       stage.addActor(entireScreen);
       Gdx.input.setInputProcessor(stage);
    }

    private void populateChestContents(){
        Table table = new Table(Assets.skin());
        table.bottom().right();
        table.padRight(200);
        table.padBottom(300);

        Label contents = new Label("Chest contains: Loot!", Assets.skin());
        table.add(contents);
        TextButton proceed = new TextButton("Next Encounter ->", Assets.skin());
        proceed.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Proceeding to next encounter...");
            }
        });
        table.add(proceed).padTop(25);
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
