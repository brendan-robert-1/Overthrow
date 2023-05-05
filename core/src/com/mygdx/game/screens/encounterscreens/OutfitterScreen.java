package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Assets;
import com.mygdx.game.encounters.Outfitter;
import com.mygdx.game.screens.widgets.InspectBox;
import com.mygdx.game.screens.HoverClickListener;
import com.mygdx.game.state.GameState;
import com.mygdx.game.state.items.ItemSlot;
import org.checkerframework.checker.units.qual.C;

import java.util.List;

public class OutfitterScreen extends InGameEncounterScreen {
    private GameState gameState = GameState.getInstance();
    private Skin skin = Assets.skin();
    private Table outfitterOptions;
    @Override
    public void show() {
        Outfitter outfitter = (Outfitter) gameState.getCurrentNode();
        populateOutfitter(outfitter);
    }

    private void populateOutfitter(Outfitter outfitter){
        List<ItemSlot> outfitterItems = outfitter.buildOutfitterItems();
        Label label = new Label("Choose an item to start the run.",Assets.skin(), "title");


        outfitterOptions = new Table();
        outfitterOptions.setFillParent(true);

        outfitterOptions = new Table();
        outfitterOptions.add(label).colspan(outfitterItems.size());
        outfitterOptions.row();
        outfitterOptions.row();
        outfitterOptions.setName("outfitterOptions");
        outfitterOptions.pad(0.0f);
        outfitterOptions.align(Align.bottomRight);
        outfitterOptions.setFillParent(true);
        outfitterOptions.padBottom(150);
        outfitterOptions.padRight(50);


        for(ItemSlot item : outfitterItems){
            outfitterOptions.add(getOptionTable(item)).space(20.0f).minSize(150.0f);
        }

        stage.addActor(outfitterOptions);


    }

    private Table getOptionTable(ItemSlot itemSlot){
        Table table1 = new Table();
        table1.setBackground(Assets.skin().getDrawable("button-up"));

        Image image = new Image(Assets.skin(), itemSlot.getSpriteName());
        image.setScaling(Scaling.fill);
        table1.addListener(new HoverClickListener(new InspectBox(itemSlot.getName(), itemSlot.getDescription())));
        table1.addListener(clickOption(itemSlot));
        table1.add(image).minSize(96).maxSize(96);
        return table1;
    }
    private ClickListener clickOption(ItemSlot item){
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameState.getInstance().getInventory().addItem(item);
                outfitterOptions.remove();
                redirectNextNode();
                super.clicked(event, x, y);
            }
        };
    }
}