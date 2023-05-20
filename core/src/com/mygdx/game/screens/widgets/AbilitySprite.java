package com.mygdx.game.screens.widgets;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.Assets;
import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.screens.widgets.inventory.InventoryItem;

public class AbilitySprite extends Image {
    public AbilitySprite(Ability.AbilityType abilityType) {
        TextureAtlas atlas = Assets.getAssetManager().get("overthrow.atlas", TextureAtlas.class);
        TextureRegionDrawable trd = new TextureRegionDrawable(atlas.findRegion(abilityType.toString()));
        this.setDrawable(trd);
        this.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        this.setScaling(Scaling.none);

        this.pack();
    }

    public AbilitySprite(InventoryItem.ItemTypeId itemTypeId) {
        TextureAtlas atlas = Assets.getAssetManager().get("overthrow.atlas", TextureAtlas.class);
        TextureRegionDrawable trd = new TextureRegionDrawable(atlas.findRegion(itemTypeId.toString()));
        this.setDrawable(trd);
        this.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        this.setScaling(Scaling.none);

        this.pack();
    }
}
