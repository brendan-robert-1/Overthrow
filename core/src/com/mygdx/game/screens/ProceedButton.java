package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.Assets;

public class ProceedButton extends TextButton {

    public ProceedButton(){
        super("Proceed", Assets.skin());
       // this.setPosition(Gdx.graphics.getWidth()-this.getWidth() + 10, 50);
        setDefaultPad();
    }

    public ProceedButton(String text, Skin skin) {
        super(text, skin);
    }



    public ProceedButton(String text, Skin skin, String styleName) {
        super(text, skin, styleName);
    }



    public ProceedButton(String text, TextButtonStyle style) {
        super(text, style);
    }

    private void setDefaultPad(){
        this.padLeft(20);
        this.padRight(30);
    }
}
