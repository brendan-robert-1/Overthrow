package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.mygdx.game.Assets;

public class InspectBox extends Window {

    /**
     * Default constructor.
     */
    public InspectBox() {
        super("", Assets.skin());
        setDefaults();
    }

    public InspectBox(String title){
        super(title, Assets.skin());
        setDefaults();
    }

    private void setDefaults(){
        setSize(300, 200);
        setVisible(false);
        setMovable(true);
        setModal(false);
        setClip(false);
        setTransform(true);
    }
}