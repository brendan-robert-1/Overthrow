package com.mygdx.game.encounters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.encounterscreens.MainGameScreen;
import com.mygdx.game.screens.widgets.*;
import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.screens.widgets.inventory.InventoryUi;
import com.mygdx.game.screens.widgets.markets.MarketOption;
import com.mygdx.game.state.GameState;
import com.mygdx.game.state.items.InventoryItemFactory;
import com.mygdx.game.state.shops.ShopOffering;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.mygdx.game.Assets.MASTER_VOLUME;

public class MarketNode extends GameNode implements ProceedSubject {


    @Override
    public String ambientSounds() {
        return null;
    }

    private Map<InventoryItem, Integer> waresToPrice;

    @Override
    public String backgroundAsset() {
        return "farms-fire";
    }

    public MarketNode(Map<InventoryItem, Integer> waresToPrice){
        super(NodeType.MARKET, "Market");
        this.waresToPrice = waresToPrice;
        this.setBackground(Assets.skin().getDrawable("button-down"));
        this.add(marketWindow()).expand().fill();
        this.setSize(Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 100);
        this.setPosition(Gdx.graphics.getWidth()/2 - this.getWidth()/2, Gdx.graphics.getHeight()/2 - this.getHeight()/2);
        this.pack();

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Market");
        return sb.toString();
    }

    private Table marketWindow(){
        Table table = new Table();
        table.add(getOptionPanel(InventoryItem.ItemTypeId.SNUG_SANDALS)).expand().fill().height(128).width(128).pad(20);
        table.add(getOptionPanel(InventoryItem.ItemTypeId.RUSTY_DAGGER)).expand().fill().height(128).width(128).pad(20);
        table.add(getOptionPanel(InventoryItem.ItemTypeId.HEALTH_POT)).expand().fill().height(128).width(128).pad(20);
        table.row();
        table.add(getOptionPanel(InventoryItem.ItemTypeId.HIDE_SHIELD)).expand().fill().height(128).width(128).pad(20);
        table.add(getOptionPanel(InventoryItem.ItemTypeId.MINER_HAT)).expand().fill().height(128).width(128).pad(20);
        table.add(getOptionPanel(InventoryItem.ItemTypeId.LEATHER_PANTS)).expand().fill().height(128).width(128).pad(20);
        table.row();
        ProceedButton proceedButton = new ProceedButton();
        proceedButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MarketNode.this.notify(ProceedObserver.ProceedEvent.PROCEED);
                super.clicked(event, x, y);
            }
        });
        table.add(proceedButton).colspan(3).expandX().right().padRight(5);
        return table;
    }

    public Table getOptionPanel(InventoryItem.ItemTypeId itemTypeId){
        InventoryItem inventoryItem = InventoryItemFactory.getInstance().of(itemTypeId);
        Table table = new MarketOption(inventoryItem);
        ItemSprite itemSprite = new ItemSprite(itemTypeId);
        itemSprite.setScaling(Scaling.fit);
        table.add(itemSprite).expand().fill();
        table.row();
        table.add(new Label("Price: "+ inventoryItem.getCoinValue(), Assets.skin())).padTop(10);
        table.addListener(new HudTooltipListener());
        table.addListener(purchaseClickListener(inventoryItem));
        return table;
    }

    private InputListener purchaseClickListener(InventoryItem inventoryItem){
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(TopBar.getInstance().getCoin() >= inventoryItem.getCoinValue()){
                    Sound purchase = Assets.getInstance().getSoundAsset("purchase.mp3");
                    purchase.play(MASTER_VOLUME);
                    InventoryUi.getInstance().addToFirstOpenSlot(inventoryItem);
                    TopBar.getInstance().decreaseCoinBy(inventoryItem.getCoinValue());
                    event.getListenerActor().remove();
                    TopBar.getInstance().update();
                    super.clicked(event, x, y);
                }
            }
        };
    }



    public Map<InventoryItem, Integer> getWaresToPrice() {
        return waresToPrice;
    }

    public List<ShopOffering> getShopOfferings(){
        List<ShopOffering> offerings = new ArrayList<>();
        for(InventoryItem item : waresToPrice.keySet()){
            int price = waresToPrice.get(item);
            ShopOffering offering = new ShopOffering();
            offering.setInventoryItem(item);
            offering.setPrice(price);
            offerings.add(offering);
        }
        return offerings;
    }



    @Override
    public void notify(ProceedObserver.ProceedEvent event) {
        MainGameScreen.getInstance().onNotify(event);
    }
}
