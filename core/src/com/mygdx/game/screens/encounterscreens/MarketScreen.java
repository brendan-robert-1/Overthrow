package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;
import com.mygdx.game.encounters.MarketNode;
import com.mygdx.game.screens.widgets.*;
import com.mygdx.game.state.GameState;
import com.mygdx.game.state.shops.ShopOffering;

import java.util.List;

public class MarketScreen extends ScreenAdapter {
    private MarketNode marketNode;
    private Stage stage;
    private Viewport viewport;

    @Override
    public void show() {
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
            }
        });
        table.add(viewWares);
        table.add(nextEncounter);
        return table;
    }

    private void displayMarket(){
        MarketTable marketTable = new MarketTable(marketNode);
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
        TextButton button = new TextButton(offering.getInventoryItem().getName() + ": " + offering.getPrice() + " coins", Assets.skin());
        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(TopBar.getInstance().getCoin() >= offering.getPrice()){
                    chargeAndAddToInventory(offering);
                }else {
                    System.out.println("Too expensive!");
                }
            }
        });
        return button;
    }



    private void chargeAndAddToInventory(ShopOffering offering) {
        TopBar.getInstance().setCoin(TopBar.getInstance().getCoin() - offering.getPrice());

        System.out.println("New coin amount after purchase: " + TopBar.getInstance().getCoin());

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
