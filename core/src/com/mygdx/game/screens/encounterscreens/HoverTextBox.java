package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.mygdx.game.Assets;

public class HoverTextBox extends TextButton {

    public HoverTextBox(String text){
        super(text, Assets.skin());
    }


    public HoverTextBox(String text, Skin skin) {
        super(text, skin);
    }



    public HoverTextBox(String text, Skin skin, String styleName) {
        super(text, skin, styleName);
    }



    public HoverTextBox(String text, TextButtonStyle style) {
        super(text, style);
    }
}
