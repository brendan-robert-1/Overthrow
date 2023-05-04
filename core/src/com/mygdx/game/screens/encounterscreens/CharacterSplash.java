package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.Assets;
import com.mygdx.game.state.Character.CharacterType;

public class CharacterSplash extends Image {


    public CharacterSplash(CharacterType characterType){
        super(Assets.skin(),characterType.toString().toLowerCase() + "-splash");
        this.setPosition(getXVal(),getYVal());
    }

    private float getXVal(){
        return  (Gdx.graphics.getWidth()/2-(this.getWidth()/5));
    }

    private float getYVal(){
       return  Gdx.graphics.getHeight()*1/2-this.getHeight()/2;
    }

}
