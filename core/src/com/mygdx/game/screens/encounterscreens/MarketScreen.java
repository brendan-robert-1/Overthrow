package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Assets;
import com.mygdx.game.encounters.Market;
import com.mygdx.game.state.GameState;
import com.mygdx.game.state.items.ItemSlot;

public class MarketScreen extends InGameEncounterScreen {
    private GameState gameState;
    private Market currentMarket;
    public MarketScreen(GameState gameState){
        super(gameState);
        this.gameState = gameState;
        this.currentMarket = (Market) gameState.currentNode();
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
        for(ItemSlot offering : currentMarket.getWaresToPrice().keySet()){
            int price = currentMarket.getWaresToPrice().get(offering);
            TextButton healthPot = new TextButton(offering.name() + ": " + price + " coin(s)", Assets.skin());
            table.add(healthPot).fillX();
            table.row();
        }
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
