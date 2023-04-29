package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.state.GameState;

public class NextEncounterSelectionScreen extends InGameEncounterScreen{
    public NextEncounterSelectionScreen(GameState gameState) {
        super(gameState);
    }

    @Override
    public void show() {
        Table market = new Table();
        stage.addActor(market);
        market.setFillParent(true);
        populateInGameEncounterScreen();
        Gdx.input.setInputProcessor(stage);
    }
}
