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
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;

public class SaunaScreen extends ScreenAdapter {
    private Stage stage;
    private Viewport viewport;
    @Override
    public void show() {
        stage = new Stage();
        viewport = new ScreenViewport();
        Table table = new Table(Assets.skin());
        table.right();
        table.padRight(200);
        Label title =  new Label("Relax at the sauna", Assets.skin());
        title.setAlignment(Align.center);
        table.add(title).colspan(2);
        table.row();
        TextButton normal = new TextButton("Normal Heat", Assets.skin());
        normal.addListener(normalHeatClickListener());
        table.add(normal).pad(10).fillX();
        TextButton addCoals = new TextButton("Add Coals", Assets.skin());
        addCoals.addListener(addCoalsClickListener());
        table.add(addCoals).pad(10).fillX();
        stage.addActor(table);
    }

    private ClickListener normalHeatClickListener(){
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Sauna using normal heat");
                InGameEncounterScreen.redirectNextNode();
            }
        };
    }

    private ClickListener addCoalsClickListener(){
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Sauna using normal heat");
                InGameEncounterScreen.redirectNextNode();
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
