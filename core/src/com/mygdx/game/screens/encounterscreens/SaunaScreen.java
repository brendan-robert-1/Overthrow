package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Assets;
import com.mygdx.game.OverthrowScreenAdapter;
import com.mygdx.game.state.GameState;

public class SaunaScreen extends InGameEncounterScreen {
      @Override
    public void show() {
        addSaunaOptions();
    }

    private void addSaunaOptions(){
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
        populateEncounter(table);
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
