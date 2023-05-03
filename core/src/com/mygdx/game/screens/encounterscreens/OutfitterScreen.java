package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Assets;
import com.mygdx.game.encounters.Outfitter;
import com.mygdx.game.screens.widgets.InspectBox;
import com.mygdx.game.screens.HoverClickListener;
import com.mygdx.game.encounters.state.GameState;
import com.mygdx.game.encounters.state.items.ItemSlot;

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
        Label title = new Label("Choose an item to begin the run with", Assets.skin(), "title");
        title.setAlignment(Align.center);
        outfitterTable.add(title).colspan(outfitterItems.size());
        outfitterTable.row();
        for(ItemSlot item : outfitterItems) {
            Table outfitterOptionButton = getOutfitterOptionButton(item);
            outfitterTable.add(outfitterOptionButton).expandY().fillY();
        }
        populateEncounter(outfitterTable);
    }



    private Table getOutfitterOptionButton(ItemSlot item) {
        Table outfitterOption = new Table();
        TextureRegionDrawable drawable = new TextureRegionDrawable(Assets.skin().getRegion(item.getItemType().toString()));
        Drawable background = Assets.skin().getDrawable("button-over");
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = drawable;
        style.imageDown = drawable;
        style.imageOver = drawable;
        style.up = background;
        style.down = background;
        style.over = background;
        ImageButton imageButton = new ImageButton(style);
        outfitterOption.addListener(new HoverClickListener(stage, new InspectBox()));
        outfitterOption.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                addItemToInventory(item);
                redirectNextNode();
            }
        });
        outfitterOption.add(imageButton).expand().fill();
        return outfitterOption;
    }



    private void addItemToInventory(ItemSlot item){
        System.out.println("Adding chosen outfitter item to inventory: " + item.getName());
        gameState.getInventory().addItem(item);
    }


}
