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

public class WeaponMerchantScreen extends InGameEncounterScreen {
    private GameState gameState = GameState.getInstance();


    @Override
    public void show() {
        Table market = new Table().right().padRight(200);
        populateMarket(market);
    }

    private void populateMarket(Table table){
        Label label = new Label("Market", Assets.skin());
        table.add(label);
        table.row();
        TextButton healthPot = new TextButton("Basic dagger", Assets.skin());
        table.add(healthPot).fillX();
        table.row();
        TextButton hideShield = new TextButton("Short sword", Assets.skin());
        table.add(hideShield).fillX();;
        table.row();
        TextButton bluntDagger = new TextButton("Hatchet", Assets.skin());
        table.add(bluntDagger).fillX();;
        table.row();
        TextButton leatherPants = new TextButton("Broken Bottle", Assets.skin());
        table.add(leatherPants).fillX();;
        table.row();
        TextButton proceed = new TextButton("Next Encounter ->", Assets.skin());
        proceed.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Proceeding to next encounter...");
                redirectNextNode();
            }
        });
        table.add(proceed).padTop(25);
        table.row();
        populateEncounter(table);
    }
}

