package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.mygdx.game.Assets;
import com.mygdx.game.state.Character;

public class AbilitiesPanel extends Table {
    private Character activeCharacter;
    public AbilitiesPanel(Character activeCharacter) {
        this.activeCharacter = activeCharacter;
        this.setBackground(Assets.skin().getDrawable("button-up"));
        this.setWidth(512);
        this.setHeight(128);
        this.setPosition(Gdx.graphics.getWidth()/2f - this.getWidth()/2f, 0);
    }
}
