package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Assets;
import com.mygdx.game.encounters.Outfitter;
import com.mygdx.game.screens.HoverBox;
import com.mygdx.game.screens.HoverClickListener;
import com.mygdx.game.screens.ItemImageButton;
import com.mygdx.game.state.GameState;
import com.mygdx.game.state.items.ItemSlot;
import com.mygdx.game.state.items.ItemSlotFactory;
import com.mygdx.game.state.items.ItemType;

import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class OutfitterScreen extends InGameEncounterScreen {
    private GameState gameState = GameState.getInstance();

    @Override
    public void show() {
        Outfitter outfitter = (Outfitter) gameState.getCurrentNode();
        populateOutfitter(outfitter);
    }

    private void populateOutfitter(Outfitter outfitter){
        List<ItemSlot> outfitterItems = outfitter.buildOutfitterItems();
        Table outfitterTable = new Table();
        Label title = new Label("Choose an item to begin the run with", Assets.skin());
        title.setAlignment(Align.center);
        outfitterTable.add(title).colspan(outfitterItems.size()).fillX();
        outfitterTable.row();
        for(ItemSlot item : outfitterItems) {
            ImageButton outfitterOptionButton = getOutfitterOptionButton(item);
            outfitterTable.add(outfitterOptionButton).fillX().pad(10);
        }
        populateEncounter(outfitterTable);
    }



    private ImageButton getOutfitterOptionButton(ItemSlot item) {
        TextureRegionDrawable drawable = new TextureRegionDrawable(Assets.skin().getRegion(item.getItemType().toString()));
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.up = drawable;

        ImageButton imageButton = new ImageButton(style);
        imageButton.addListener(new HoverClickListener(stage, new HoverBox()));
        return imageButton;
    }



    private void addItemToInventory(ItemSlot item){
        System.out.println("Adding chosen outfitter item to inventory: " + item.getName());
        gameState.getInventory().addItem(item);
    }


}
