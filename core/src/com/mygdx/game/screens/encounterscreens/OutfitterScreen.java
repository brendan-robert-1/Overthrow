package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Assets;
import com.mygdx.game.encounters.Outfitter;
import com.mygdx.game.state.GameState;
import com.mygdx.game.state.items.ItemSlot;
import com.mygdx.game.state.items.ItemSlotFactory;
import com.mygdx.game.state.items.ItemType;

import java.util.ArrayList;
import java.util.List;

public class OutfitterScreen extends InGameEncounterScreen {
    private GameState gameState = GameState.getInstance();

    @Override
    public void show() {
        Outfitter outfitter = (Outfitter) gameState.getCurrentNode();
        populateOutfitter(outfitter);
        Gdx.input.setInputProcessor(stage);
    }

    private void populateOutfitter(Outfitter outfitter){
        List<ItemSlot> outfitterItems = outfitter.buildOutfitterItems();
        Table outfitterTable = new Table();
        Label title = new Label("Choose an item to begin the run with", Assets.skin());
        title.setAlignment(Align.center);
        outfitterTable.add(title).colspan(outfitterItems.size()).fillX();
        outfitterTable.row();
        for(ItemSlot item : outfitterItems){
           TextureRegion region = new TextureRegion(Assets.getAssetManager().get(item.getItemType().toString() + "_64.png", Texture.class)); //TODO turn into item -> texture resolver
           TextureRegionDrawable drawable = new TextureRegionDrawable(region);
           ImageButton option = new ImageButton(drawable);
            outfitterTable.add(option).fillX().pad(10);
            option.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    addItemToInventory(item);
                    redirectNextNode();
                }
            });
        }
        populateEncounter(outfitterTable);
    }

    private void addItemToInventory(ItemSlot item){
        System.out.println("Adding chosen outfitter item to inventory: " + item.getName());
        gameState.getInventory().addItem(item);
    }


}
