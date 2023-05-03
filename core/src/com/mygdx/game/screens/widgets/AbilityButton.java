package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.Assets;

public class AbilityButton extends TextButton {
    private Skin skin;

    public AbilityButton(String text){
        super(text, Assets.skin());
        this.skin = Assets.skin();
    }
    public AbilityButton(String text, Skin skin) {
        super(text, skin);
    }



    public AbilityButton(String text, Skin skin, String styleName) {
        super(text, skin, styleName);
    }



    public AbilityButton(String text, TextButtonStyle style) {
        super(text, style);
    }
}
