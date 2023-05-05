package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.screens.encounterscreens.StageManager;

public abstract class OverthrowScreenAdapter extends ScreenAdapter {


    public Stage stage = StageManager.getInstance().getStage();

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f,.1f, .15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        StageManager.getInstance().getStage().act();
        StageManager.getInstance().getStage().draw();
    }

    @Override
    public void resize(int width, int height) {
        StageManager.getInstance().getViewport().update(width, height);
    }
}
