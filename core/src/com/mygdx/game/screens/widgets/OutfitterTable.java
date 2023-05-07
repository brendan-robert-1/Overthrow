package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.Assets;
import com.mygdx.game.encounters.Outfitter;
import com.mygdx.game.screens.HoverClickListener;
import com.mygdx.game.screens.encounterscreens.InGameEncounterScreen;
import com.mygdx.game.state.GameState;

import java.util.List;

public class OutfitterTable extends Table {

    private final Stage stage;
    public OutfitterTable(Outfitter outfitter, Stage stage){
        this.stage = stage;
        List<InventoryItem> outfitterItems = outfitter.buildOutfitterItems();
        Label label = new Label("Choose an item to start the run.", Assets.skin(), "title");
        this.add(label).colspan(outfitterItems.size());
        this.row();
        this.row();
        for(InventoryItem item : outfitterItems){
            this.add(getOptionTable(item)).space(20.0f).minSize(150.0f);
        }
        this.pack();
    }

    private Table getOptionTable(InventoryItem inventoryItem){
        Table table1 = new Table();
        table1.setBackground(Assets.skin().getDrawable("button-up"));

        TextureAtlas atlas = Assets.getAssetManager().get("overthrow.atlas", TextureAtlas.class);
        TextureRegionDrawable trd = new TextureRegionDrawable(atlas.findRegion(inventoryItem.getItemTypeId().toString()));
        Image image = new Image(trd);
        image.setScaling(Scaling.fill);
        table1.addListener(new HoverClickListener(stage, new InspectBox(inventoryItem.getDisplayName(), inventoryItem.getItemShortDescription())));
        table1.addListener(clickOption(inventoryItem));
        table1.add(image).minSize(96).maxSize(96);
        return table1;
    }

    private ClickListener clickOption(InventoryItem item){
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameState.getInstance().getInventory().addItem(item);
                InGameEncounterScreen.redirectNextNode();
                super.clicked(event, x, y);
            }
        };
    }
}

