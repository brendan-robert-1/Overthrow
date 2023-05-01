package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
        Dialog dialog = new Dialog("Outfitter", Assets.skin()){
            {
                text("Select an item to begin you're run with");
                for(ItemSlot item : outfitterItems){
                    button(item.getName(), item);
                    row();
                }
            }
            @Override
            protected void result(Object object) {
                ItemSlot chosenItem = (ItemSlot) object;
                addItemToInventory(chosenItem);
                redirectNextNode();
                super.result(object);
            }
        };
       dialog.show(stage);
    }

    private void addItemToInventory(ItemSlot item){
        System.out.println("Adding chosen outfitter item to inventory: " + item.getName());
        gameState.getInventory().addItem(item);
    }


}
