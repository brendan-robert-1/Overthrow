package com.mygdx.game.state.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.google.gson.Gson;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.screens.widgets.inventory.InventoryItem.ItemTypeId;

public class InventoryItemFactory {

    private static final String ITEMS_FILE = "gamedata/items.json";
    private static Items items;
    private static InventoryItemFactory instance;
    private InventoryItemFactory(){
        items  = loadItems();
    }
    public static InventoryItemFactory getInstance(){
        if(instance == null){
            instance = new InventoryItemFactory();
        }
        return instance;
    }

    public InventoryItem of(ItemTypeId itemType) {
        InventoryItem newItem = new InventoryItem(items.getItems().get(itemType));
        TextureAtlas atlas = Assets.getAssetManager().get("overthrow.atlas", TextureAtlas.class);
        TextureRegionDrawable trd = new TextureRegionDrawable(atlas.findRegion(newItem.getItemTypeId().toString()));
        newItem.setDrawable(new TextureRegionDrawable(trd));
        newItem.setScaling(Scaling.none);
        return newItem;
    }

    private static Items loadItems(){
        Gson gson = new Gson();
        String jsonStr = Gdx.files.internal(ITEMS_FILE).readString();
        return gson.fromJson(jsonStr, Items.class);
    }

}
