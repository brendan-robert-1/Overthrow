package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;
import com.mygdx.game.encounters.Market;
import com.mygdx.game.screens.widgets.*;
import com.mygdx.game.state.GameState;
import com.mygdx.game.state.shops.ShopOffering;

import java.util.List;

public class MarketScreen extends ScreenAdapter {
    private GameState gameState = GameState.getInstance();
    private Market market;
    private Stage stage;
    private Viewport viewport;

    @Override
    public void show() {
        stage = new Stage();
        viewport = new ScreenViewport();
        Table entireScreen = new EntireInGameScreenTable();
        InventoryUi inventoryUi = new InventoryUi();
        entireScreen.add(new TopBar(inventoryUi)).expand().fillX().colspan(2).top();
        entireScreen.row();
        entireScreen.add(new Team()).expand().bottom().left().pad(40);
        Table market = market();
        entireScreen.add(market).expand().bottom().right();
        stage.addActor(entireScreen);
        Gdx.input.setInputProcessor(stage);
    }

    private Table market(){
        Table table = new Table();
        PixelProTextButton viewWares = new PixelProTextButton("View Wares");
        PixelProTextButton nextEncounter = new PixelProTextButton("Next Encounter");

        viewWares.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                displayMarket();
            }
        });
        nextEncounter.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                InGameEncounterScreen.redirectNextNode();
            }
        });
        table.add(viewWares);
        table.add(nextEncounter);
        return table;
    }

    private void displayMarket(){
        MarketTable marketTable = new MarketTable(market);
        marketTable.setFillParent(true);
        stage.addActor(marketTable);
    }



    private void addShopOfferings(Table table, List<ShopOffering> shopOfferings) {
        int counter = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j <3; j++){
                if(counter <= shopOfferings.size() -1){
                    table.add(shopOfferingButton(shopOfferings.get(counter))).pad(5).fillX();
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

    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f,.1f, .15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
