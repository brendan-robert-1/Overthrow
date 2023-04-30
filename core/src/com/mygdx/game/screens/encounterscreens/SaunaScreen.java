package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Assets;
import com.mygdx.game.OverthrowScreenAdapter;
import com.mygdx.game.state.GameState;

public class SaunaScreen extends InGameEncounterScreen {
    public SaunaScreen(GameState gameState) {
        super(gameState);
    }

    @Override
    public void show() {
        // stage.setDebugAll(true);
        addSaunaOptions();
        Gdx.input.setInputProcessor(stage);
    }

    private void addSaunaOptions(){
        Table table = new Table(Assets.skin());
        table.bottom().right();
        table.padRight(200);
        table.padBottom(300);
        TextButton normal = new TextButton("Normal Heat", Assets.skin());
        normal.addListener(normalHeatClickListener());
        table.add(normal);
        TextButton addCoals = new TextButton("Add Coals", Assets.skin());
        addCoals.addListener(addCoalsClickListener());
        table.add(addCoals);
        table.setFillParent(true);
        stage.addActor(table);
    }

    private ClickListener normalHeatClickListener(){
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Sauna using normal heat");
                redirectNextNode();
            }
        };
    }

    private ClickListener addCoalsClickListener(){
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Sauna using normal heat");
                redirectNextNode();
            }
        };
    }
}
