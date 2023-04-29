package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.Assets;
import com.mygdx.game.OverthrowScreenAdapter;
import com.mygdx.game.state.GameState;

public class MarketScreen extends InGameEncounterScreen {
    private GameState gameState;
    public MarketScreen(GameState gameState){
        super(gameState);
        this.gameState = gameState;
    }

    @Override
    public void show() {
        Table market = new Table();
        stage.addActor(market);
        market.setFillParent(true);
        populateMarket(market);
        Gdx.input.setInputProcessor(stage);
    }

    private void populateMarket(Table table){
        Label label = new Label("Market", Assets.skin());
        table.add(label);
        table.row();
        TextButton healthPot = new TextButton("Minor Health Potion", Assets.skin());
        table.add(healthPot);
        table.row();
        TextButton hideShield = new TextButton("Hide Shield", Assets.skin());
        table.add(hideShield);
        table.row();
        TextButton bluntDagger = new TextButton("Blunt Dagger", Assets.skin());
        table.add(bluntDagger);
        table.row();
        TextButton leatherPants = new TextButton("Leather Pants", Assets.skin());
        table.add(leatherPants);
        table.row();
    }
}
