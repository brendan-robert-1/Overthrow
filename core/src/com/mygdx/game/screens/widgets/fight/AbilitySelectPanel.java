package com.mygdx.game.screens.widgets.fight;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.Assets;

public class AbilitySelectPanel extends Table {

    public AbilitySelectPanel(){
        Image image = new Image(Assets.skin().getPatch("inventory-background"));
        image.setSize(52,52);
        Image image2 = new Image(Assets.skin().getPatch("inventory-background"));
        image.setSize(52,52);
        Image image3 = new Image(Assets.skin().getPatch("inventory-background"));
        this.add(image);
        this.add(image2);
        this.add(image3);
        this.setPosition(200,200);
        this.setVisible(false);
    }
}
