package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Gdx;
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
        Gdx.input.setInputProcessor(stage);
    }

    private void populateOutfitter(Outfitter outfitter){
        List<ItemSlot> outfitterItems = outfitter.buildOutfitterItems();
        Table outfitterTable = new Table();
        Label title = new Label("Choose an item to begin the run with", Assets.skin());
        title.setAlignment(Align.center);
        outfitterTable.add(title).colspan(outfitterItems.size()).fillX();
        outfitterTable.row();

        NinePatch ninePatch = new NinePatch(Assets.getAssetManager().get("slot_9patch.png", Texture.class), 5, 5, 5, 5);
        NinePatchDrawable background = new NinePatchDrawable(ninePatch);

        for(ItemSlot item : outfitterItems) {
            ImageButton outfitterOptionButton = getOutfitterOptionButton(item);
            outfitterTable.add(outfitterOptionButton).fillX().pad(10);
        }
        populateEncounter(outfitterTable);
    }



    private ImageButton getOutfitterOptionButton(ItemSlot item) {
        Texture itemSprite = Assets.getAssetManager().get(item.getItemType().toString() + "_64.png", Texture.class);
        TextureRegionDrawable drawable = new TextureRegionDrawable(itemSprite);
        ImageButton imageButton = new ImageButton(drawable);


        InputListener mouseMoved = new InputListener(){
            @Override
            public boolean mouseMoved(InputEvent event, float x, float y) {
                System.out.println(event.getStageX()+ ", " + event.getStageY());
                hoverMenu.remove();
                hoverMenu.setFillParent(true);
                hoverMenu = new Table();
                stage.addActor(hoverMenu);
                hoverMenu.add(new HoverBox(event.getStageX(), event.getStageY(), "Hello", "Hello"));
                return super.mouseMoved(event, x, y);
            }
        };

        imageButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                addItemToInventory(item);
                redirectNextNode();
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                stage.addListener(mouseMoved);
                System.out.println("Considering " + item.getName());
                super.enter(event, x, y, pointer, fromActor);
            }



            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                System.out.println("Stopped Considering " + item.getName());
                hoverMenu.remove();
                stage.removeListener(mouseMoved);
                super.exit(event, x, y, pointer, toActor);
            }
        });
        return imageButton;
    }



    private void addItemToInventory(ItemSlot item){
        System.out.println("Adding chosen outfitter item to inventory: " + item.getName());
        gameState.getInventory().addItem(item);
    }


}
