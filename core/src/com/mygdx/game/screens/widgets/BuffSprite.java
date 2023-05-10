package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.Assets;
import com.mygdx.game.character.buff.Buff;

public class BuffSprite extends Image {
    private Buff buff;

    public BuffSprite(Buff buff) {
        this.buff = buff;
        TextureAtlas atlas = Assets.getAssetManager().get("overthrow.atlas", TextureAtlas.class);
        TextureRegionDrawable trd = new TextureRegionDrawable(atlas.findRegion(buff.getBuffType().toString()));
        this.setDrawable(trd);
        this.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        this.setScaling(Scaling.none);
        this.pack();
    }



    public Buff getBuff() {
        return buff;
    }
}
