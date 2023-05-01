package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Assets;
import com.mygdx.game.state.GameState;
import com.mygdx.game.state.items.ItemSlot;
import com.mygdx.game.state.items.ItemSlotFactory;
import com.mygdx.game.state.items.ItemType;

import java.util.ArrayList;
import java.util.List;

public class OutfitterScreen extends InGameEncounterScreen {
    private GameState gameState;

    public OutfitterScreen(GameState gameState) {
        super(gameState);
        this.gameState = gameState;
    }

    @Override
    public void show() {
        Table table = new Table();
        table.right();
        table.padRight(200);
        stage.addActor(table);
        table.setFillParent(true);
        populateOutfitter(table);
        Gdx.input.setInputProcessor(stage);
    }

    private void populateOutfitter(Table table){
        List<ItemSlot> outfitterItems = buildOutfitterItems();
        Dialog dialog = new Dialog("Outfitter", Assets.skin()){
            {
                text("Select an item to begin you're run with");
                for(ItemSlot item : outfitterItems){
                    button(item.name());
                    row();
                }
            }
            @Override
            protected void result(Object object) {
                super.result(object);
            }
        };
       dialog.show(stage).right();
    }

    private List<ItemSlot> buildOutfitterItems() {
        List<ItemSlot> outfitterList = new ArrayList<>();
        outfitterList.add(ItemSlotFactory.one(ItemType.HIDE_SHIELD));
        outfitterList.add(ItemSlotFactory.one(ItemType.RUSTY_DAGGER));
        outfitterList.add(ItemSlotFactory.one(ItemType.FRESHMAN_SPELLCRAFT_NOTEBOOK));
        return outfitterList;
    }
}
