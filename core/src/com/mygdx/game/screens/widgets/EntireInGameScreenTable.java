package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Assets;

public class EntireInGameScreenTable extends Table {

    public EntireInGameScreenTable(){
        TextureRegionDrawable trd = new TextureRegionDrawable(Assets.skin().getRegion("farms2"));
        this.setBackground(trd);
        this.setFillParent(true);
        this.pack();
    }


}
