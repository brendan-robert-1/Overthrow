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

public class GemMerchantScreen extends InGameEncounterScreen {
    private GameState gameState;
    public GemMerchantScreen(GameState gameState){
        super(gameState);
        this.gameState = gameState;
    }

    @Override
    public void show() {
        Table market = new Table().right().padRight(200);
        stage.addActor(market);
        market.setFillParent(true);
        populateMarket(market);
        Gdx.input.setInputProcessor(stage);
    }

    private void populateMarket(Table table){
        Label label = new Label("Market", Assets.skin());
        table.add(label);
        table.row();
        TextButton healthPot = new TextButton("Poison Resist Opal", Assets.skin());
        table.add(healthPot).fillX();
        table.row();
        TextButton hideShield = new TextButton("Poison Damage Sapphire", Assets.skin());
        table.add(hideShield).fillX();;
        table.row();
        TextButton bluntDagger = new TextButton("Physical Damage Amber", Assets.skin());
        table.add(bluntDagger).fillX();;
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
    }
}