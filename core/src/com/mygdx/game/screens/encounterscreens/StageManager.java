package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class StageManager {
    private static StageManager stageManager;
    public Viewport viewport = new ScreenViewport();
    private Stage stage;
    private StageManager(){
        stage = new Stage();
    }

    public static StageManager getInstance(){
        if(stageManager == null){
            stageManager = new StageManager();
        }return stageManager;
    }

    public Stage getStage(){
        return stage;
    }

    public Viewport getViewport(){
        return viewport;
    }

}
