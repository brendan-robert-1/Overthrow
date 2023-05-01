package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.encounterscreens.InGameEncounterScreen;
import com.mygdx.game.state.GameState;

public class WishingWellScreen extends InGameEncounterScreen {
    private GameState gameState = GameState.getInstance();
    @Override
    public void show() {
        Table market = new Table();
        stage.addActor(market);
        market.setFillParent(true);
        populateMarket(market);
        Gdx.input.setInputProcessor(stage);
    }

    private void populateMarket(Table table){
        Label label = new Label("Wishing Well", Assets.skin());
        table.add(label);
        table.row();
        TextButton throwCoins = new TextButton("Throw coins", Assets.skin());
        table.add(throwCoins);
        TextButton wish = new TextButton("Wish", Assets.skin());
        wish.addListener(wishListener());
        table.add(wish);
        table.row();
    }
    private ClickListener wishListener(){
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Wished!");
                redirectNextNode();
            }
        };
    }
}
