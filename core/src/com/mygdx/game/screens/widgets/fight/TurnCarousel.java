package com.mygdx.game.screens.widgets.fight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.Assets;

public class TurnCarousel extends Table {
    public TurnCarousel(){
        Image image = new Image(Assets.skin().getPatch("inventory-background"));
        image.setSize(52,52);
        Image image2 = new Image(Assets.skin().getPatch("inventory-background"));
        image.setSize(52,52);
        Image image3 = new Image(Assets.skin().getPatch("inventory-background"));
        image.setSize(52,52);
        Image image4 = new Image(Assets.skin().getPatch("inventory-background"));
        image.setSize(52,52);
        Image image5 = new Image(Assets.skin().getPatch("inventory-background"));
        image.setSize(52,52);
        this.add(image);
        this.row();
        this.add(image2);
        this.row();
        this.add(image3);
        this.row();
        this.add(image4);
        this.row();
        this.add(image5);
        this.setPosition(Gdx.graphics.getWidth()-100, Gdx.graphics.getHeight()/2);
        this.setVisible(false);
    }
}
