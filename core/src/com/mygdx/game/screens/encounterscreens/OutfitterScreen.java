package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Assets;
import com.mygdx.game.state.GameState;
import com.mygdx.game.state.items.ItemSlot;
import com.mygdx.game.state.items.ItemSlotFactory;
import com.mygdx.game.state.items.ItemType;

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
        Label name = new Label("Outfitter", Assets.skin());
        table.add(name).padBottom(40);
        table.row();
        Label label = new Label("Select an item to begin you're run with", Assets.skin());
        table.add(label).padBottom(15);
        table.row();
        table.add(outfitterOption(ItemSlotFactory.one(ItemType.MINOR_HEALTH_POT))).fillX().padBottom(10);
        table.row();
        table.row();
        table.setFillParent(true);
    }
    private TextButton outfitterOption(ItemSlot itemSlot){
        TextButton outfitterOption =  new TextButton(itemSlot.name()+": " + itemSlot.quantity(), Assets.skin());
        outfitterOption.getLabel().setAlignment(Align.left);
        outfitterOption.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Outfitting with: " + itemSlot.name());
                gameState.inventory().getInventoryMap().add(itemSlot);
                redirectNextNode();
            }
        });
        return outfitterOption;
    }
}
