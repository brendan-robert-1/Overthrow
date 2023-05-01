package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Assets;
import com.mygdx.game.encounters.Market;
import com.mygdx.game.state.GameState;
import com.mygdx.game.state.items.ItemSlot;
import com.mygdx.game.state.shops.ShopOffering;

import java.util.List;

public class MarketScreen extends InGameEncounterScreen {
    private GameState gameState = GameState.getInstance();
    private Market currentMarket;
    public MarketScreen(){
        this.currentMarket = (Market) gameState.getCurrentNode();
    }

    @Override
    public void show() {
        Market market = (Market) gameState.getCurrentNode();
        populateMarket(market);
        Gdx.input.setInputProcessor(stage);
    }

    private void populateMarket(Market market){
        Table table = new Table();
        table.bottom().right();
        table.padRight(200);
        table.padBottom(300);
        addShopOfferings(table, market.getShopOfferings());
        TextButton proceed = new TextButton("Next Encounter ->", Assets.skin());
        proceed.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Proceeding to next encounter...");
                redirectNextNode();
            }
        });
        table.add(proceed).right().padRight(25);
        table.row();
        table.setDebug(true);
        table.setFillParent(true);
        stage.addActor(table);
    }



    private void addShopOfferings(Table table, List<ShopOffering> shopOfferings) {
        int counter = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j <3; j++){
                if(counter <= shopOfferings.size() -1){
                    table.add(shopOfferingButton(shopOfferings.get(counter))).fillX();
                }else {
                    //table.add(new Label("_________", Assets.skin()));
                }
                counter++;
            }
            table.row();
        }

    }

    private TextButton shopOfferingButton(ShopOffering offering){
        TextButton button = new TextButton(offering.getItemSlot().getName() + ": " + offering.getPrice() + " coins", Assets.skin());
        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(gameState.getCoin() >= offering.getPrice()){
                    chargeAndAddToInventory(offering);
                }else {
                    System.out.println("Too expensive!");
                }
            }
        });
        return button;
    }



    private void chargeAndAddToInventory(ShopOffering offering) {
        gameState.setCoin(gameState.getCoin() - offering.getPrice());
        gameState.getInventory().addItem(offering.getItemSlot());
        System.out.println("New coin amount after purchase: " + gameState.getCoin());
        super.updateCoins();
    }
}
