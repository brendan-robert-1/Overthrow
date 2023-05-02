package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Assets;
import com.mygdx.game.OverthrowScreenAdapter;
import com.mygdx.game.state.GameState;

public class AbilityTrainerScreen extends InGameEncounterScreen {
    private GameState gameState = GameState.getInstance();


    @Override
    public void show() {
        populateChestContents();
    }



    private void populateChestContents() {
        Table table = new Table(Assets.skin());
        table.bottom().right();
        table.padRight(200);
        table.padBottom(300);

        Label contents = new Label("Chest contains: Loot!", Assets.skin());
        table.add(contents);
        TextButton proceed = new TextButton("Next Encounter ->", Assets.skin());
        proceed.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Proceeding to next encounter...");
                redirectNextNode();
            }
        });
        table.add(proceed).padTop(25);
        populateEncounter(table);
    }
}
