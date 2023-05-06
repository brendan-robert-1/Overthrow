package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.*;
import com.mygdx.game.state.GameState;

public final class InGameEncounterScreen{

    public static void redirectNextNode(){
        GameState.getInstance().setCurrentFloor(GameState.getInstance().getCurrentFloor() + 1);
        ((Game) Gdx.app.getApplicationListener()).setScreen(new NextEncounterSelectionScreen());
    }
}
