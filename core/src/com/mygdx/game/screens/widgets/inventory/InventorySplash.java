package com.mygdx.game.screens.widgets.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.Assets;

public class InventorySplash extends Image {
    public InventorySplash(InventoryItem.ItemUseType useType){
        TextureAtlas atlas = Assets.getAssetManager().get("overthrow.atlas", TextureAtlas.class);
        TextureRegionDrawable trd = new TextureRegionDrawable(atlas.findRegion(useType.toString()));
        this.setDrawable(trd);
        this.setSize(InventoryUi.SLOT_WIDTH, InventoryUi.SLOT_HEIGHT);
        this.setScaling(Scaling.fit);

        this.setPosition(getXVal(),0);
        this.pack();
    }

    private float getXVal(){
        return  (Gdx.graphics.getWidth()/2-(this.getWidth()/5));
    }

    private float getYVal(){
        return  Gdx.graphics.getHeight()/2-this.getHeight()/2;
    }

}
