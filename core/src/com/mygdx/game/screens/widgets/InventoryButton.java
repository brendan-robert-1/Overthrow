package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.Assets;

public class InventoryButton extends TextButton {

    public InventoryButton(String text){
        super(text, Assets.skin(), "small");
        this.pad(15);
    }
    public InventoryButton(String text, Skin skin) {
        super(text, skin);
    }



    public InventoryButton(String text, Skin skin, String styleName) {
        super(text, skin, styleName);
    }



    public InventoryButton(String text, TextButtonStyle style) {
        super(text, style);
    }
}
