package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.Assets;
import com.mygdx.game.state.Character.CharacterType;

public class CharacterSprite extends Image {


    public CharacterSprite(CharacterType characterType){
        TextureAtlas atlas = Assets.getAssetManager().get("overthrow.atlas", TextureAtlas.class);
        TextureRegionDrawable trd = new TextureRegionDrawable(atlas.findRegion(characterType.toString()));
         this.setDrawable(trd);
        this.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        this.setScaling(Scaling.none);

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
