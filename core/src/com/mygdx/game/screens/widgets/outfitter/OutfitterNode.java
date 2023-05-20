package com.mygdx.game.screens.widgets.outfitter;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.encounterscreens.MainGameScreen;
import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.screens.widgets.inventory.InventoryItem.ItemTypeId;
import com.mygdx.game.encounters.GameNode;
import com.mygdx.game.state.items.InventoryItemFactory;

import java.util.ArrayList;
import java.util.List;

import static com.mygdx.game.Assets.MASTER_VOLUME;

public class OutfitterNode extends GameNode implements OutfitterSubject {

    @Override
    public String ambientSounds() {
        return "fire.mp3";
    }
    @Override
    public String backgroundAsset() {
        return "town";
    }

    Array<OutfitterObserver> observers;

    public OutfitterNode(){
        super(NodeType.OUTFITTER, "Choose an item to start the run");
        this.observers = new Array<>();
        observers.add(MainGameScreen.getInstance());
        this.add(getOutfitterTable());
        this.pack();
    }


    public Table getOutfitterTable(){
        Table table = new Table();
        List<InventoryItem> outfitterItems = buildOutfitterItems();
        table.row();
        table.row();
        for(InventoryItem item : outfitterItems){
            table.add(getOptionTable(item)).space(20.0f).minSize(150.0f);
        }
        return table;
    }

    private Table getOptionTable(InventoryItem inventoryItem){
        Table table1 = new Table();
        table1.setBackground(Assets.skin().getDrawable("button-up"));

        TextureAtlas atlas = Assets.getAssetManager().get("overthrow.atlas", TextureAtlas.class);
        TextureRegionDrawable trd = new TextureRegionDrawable(atlas.findRegion(inventoryItem.getItemTypeId().toString()));
        Image image = new Image(trd);
        image.setScaling(Scaling.fill);
      //  table1.addListener(new HoverClickListener(getNodeStage(), new InspectBox(inventoryItem.getDisplayName(), inventoryItem.getItemShortDescription())));
        table1.addListener(clickOption(inventoryItem));
        table1.add(image).minSize(96).maxSize(96);
        return table1;
    }

    private ClickListener clickOption(InventoryItem item){
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                final Sound sound1 = Assets.getInstance().getSoundAsset("select-option.mp3");
                sound1.play(MASTER_VOLUME);
                OutfitterNode.this.notify(item, OutfitterObserver.OutfitterEvent.ITEM_SELECTED);
                OutfitterNode.this.remove();
                super.clicked(event, x, y);
            }
        };
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Outfitter");
        return sb.toString();
    }

    public List<InventoryItem> buildOutfitterItems() {
        List<InventoryItem> outfitterList = new ArrayList<>();
        outfitterList.add(InventoryItemFactory.getInstance().of(ItemTypeId.FIRE_POT));
        outfitterList.add(InventoryItemFactory.getInstance().of(ItemTypeId.HEALTH_POT));
        outfitterList.add(InventoryItemFactory.getInstance().of(ItemTypeId.RUSTY_DAGGER));
        return outfitterList;
    }



    @Override
    public void addObserver(OutfitterObserver observer) {
        observers.add(observer);
    }



    @Override
    public void removeObserver(OutfitterObserver observer) {
        observers.removeValue(observer, true);
    }



    @Override
    public void removeAllObservers() {
        for(OutfitterObserver observer : observers){
            observers.removeValue(observer,true);
        }
    }



    @Override
    public void notify(InventoryItem itemSelected, OutfitterObserver.OutfitterEvent event) {
        System.out.println("The outfitter window is notifying its observers..");
        for(OutfitterObserver observer : observers){
            observer.onNotify(itemSelected, event);
        }
    }
}
