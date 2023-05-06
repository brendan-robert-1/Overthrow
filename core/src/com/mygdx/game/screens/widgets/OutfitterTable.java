package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.Assets;
import com.mygdx.game.encounters.Outfitter;
import com.mygdx.game.screens.HoverClickListener;
import com.mygdx.game.screens.encounterscreens.InGameEncounterScreen;
import com.mygdx.game.state.GameState;
import com.mygdx.game.state.items.ItemSlot;

import java.util.List;

public class OutfitterTable extends Table {

    private final Stage stage;
    public OutfitterTable(Outfitter outfitter, Stage stage){
        this.stage = stage;
        List<ItemSlot> outfitterItems = outfitter.buildOutfitterItems();
        Label label = new Label("Choose an item to start the run.", Assets.skin(), "title");
        this.add(label).colspan(outfitterItems.size());
        this.row();
        this.row();
        for(ItemSlot item : outfitterItems){
            this.add(getOptionTable(item)).space(20.0f).minSize(150.0f);
        }
        this.pack();
    }

    private Table getOptionTable(ItemSlot itemSlot){
        Table table1 = new Table();
        table1.setBackground(Assets.skin().getDrawable("button-up"));

        Image image = new Image(Assets.skin(), itemSlot.getSpriteName());
        image.setScaling(Scaling.fill);
        table1.addListener(new HoverClickListener(stage, new InspectBox(itemSlot.getName(), itemSlot.getDescription())));
        table1.addListener(clickOption(itemSlot));
        table1.add(image).minSize(96).maxSize(96);
        return table1;
    }

    private ClickListener clickOption(ItemSlot item){
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

