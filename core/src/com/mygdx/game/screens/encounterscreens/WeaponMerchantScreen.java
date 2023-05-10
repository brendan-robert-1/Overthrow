package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.widgets.EntireInGameScreenTable;
import com.mygdx.game.state.GameState;

public class WeaponMerchantScreen extends ScreenAdapter {
    private GameState gameState = GameState.getInstance();
    private Stage stage;
    private Viewport viewport;



    @Override
    public void show() {

    }

    private Table market(){
        Table table = new Table();
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

            }
        });
        table.add(proceed).padTop(25);
        table.row();
        return table;
    }
}

