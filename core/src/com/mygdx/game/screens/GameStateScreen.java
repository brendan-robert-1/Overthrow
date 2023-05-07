package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.mygdx.game.screens.encounterscreens.EncounterScreenFactory;

public class GameStateScreen extends ScreenAdapter {

    @Override
    public void show() {
        populateNode();
    }

    private void populateNode(){
        ScreenAdapter nodeScreenAdapter = EncounterScreenFactory.getScreenFor();
        ((Game) Gdx.app.getApplicationListener()).setScreen(nodeScreenAdapter);

    }

}

