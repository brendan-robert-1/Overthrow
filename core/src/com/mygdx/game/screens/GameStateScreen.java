package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.OverthrowScreenAdapter;
import com.mygdx.game.screens.encounterscreens.InGameEncounterScreen;
import com.mygdx.game.screens.encounterscreens.EncounterScreenFactory;
import com.mygdx.game.state.GameState;

public class GameStateScreen extends InGameEncounterScreen {
    public GameStateScreen(GameState gameState) {
        super(gameState);

    }

    @Override
    public void show() {
        populateNode();
    }

    private void populateNode(){
        OverthrowScreenAdapter nodeScreenAdapter = EncounterScreenFactory.getScreenFor(super.getGameState());
        ((Game) Gdx.app.getApplicationListener()).setScreen(nodeScreenAdapter);

    }

}

