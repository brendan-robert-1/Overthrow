package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Assets;
import com.mygdx.game.OverthrowScreenAdapter;
import com.mygdx.game.items.Bandages;
import com.mygdx.game.items.ButcherKnife;
import com.mygdx.game.items.HideShield;
import com.mygdx.game.items.MinorHealthPot;
import com.mygdx.game.screens.GameStateScreen;
import com.mygdx.game.state.GameState;
import com.mygdx.game.state.ItemSlot;

public class OutfitterScreen extends InGameEncounterScreen {
    private GameState gameState;

    public OutfitterScreen(GameState gameState) {
        super(gameState);
        this.gameState = gameState;
    }

    @Override
    public void show() {
        Table market = new Table();
        stage.addActor(market);
        market.setFillParent(true);
        populateOutfitter(market);
        Gdx.input.setInputProcessor(stage);
    }

    private void populateOutfitter(Table table){
        Label name = new Label("Outfitter", Assets.skin());
        table.add(name).padBottom(40);
        table.row();
        Label label = new Label("Select an item to begin you're run with", Assets.skin());
        table.add(label).padBottom(15);
        table.row();
        table.add(outfitterOption(new MinorHealthPot().one())).fillX().padBottom(10);
        table.row();
        table.add(outfitterOption(new Bandages().of(2))).fillX().padBottom(10);
        table.row();
        table.add(outfitterOption(new HideShield().one())).fillX().padBottom(10);
        table.row();
        table.add(outfitterOption(new ButcherKnife().one())).fillX().padBottom(10);
        table.row();
        table.setFillParent(true);
    }
    private TextButton outfitterOption(ItemSlot itemSlot){
        TextButton outfitterOption =  new TextButton(itemSlot.name()+": " + itemSlot.quantity(), Assets.skin());
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

    private void redirectNextNode(){
        ((Game) Gdx.app.getApplicationListener()).setScreen(new NextEncounterSelectionScreen(gameState));
    }
}
