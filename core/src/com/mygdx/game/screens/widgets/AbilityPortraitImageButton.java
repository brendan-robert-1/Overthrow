package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Assets;

import java.awt.*;

public class AbilityPortraitImageButton extends ImageButton {

    public AbilityPortraitImageButton(){
        super(Assets.skin());
        TextureRegionDrawable background = new TextureRegionDrawable(Assets.skin().getRegion("ability-portrait"));
        //Drawable up = Assets.skin().getDrawable("MINOR_HEALTH_POT");
        ImageButtonStyle style = this.getStyle();
        this.setBackground(background);
        //style.up = up;
    }
}
