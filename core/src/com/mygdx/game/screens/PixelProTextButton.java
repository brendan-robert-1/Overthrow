package com.mygdx.game.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class PixelProTextButton extends TextButton {

    public PixelProTextButton(String text, Skin skin) {
        super(text, skin);
        setDefaultPad();
    }



    public PixelProTextButton(String text, Skin skin, String styleName) {
        super(text, skin, styleName);
        setDefaultPad();
    }



    public PixelProTextButton(String text, TextButtonStyle style) {
        super(text, style);
        setDefaultPad();
    }

    private void setDefaultPad(){
        this.pad(20);
    }
}
