package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Assets;

public class InspectBox extends Window {

    /**
     * Default constructor.
     */
    public InspectBox() {
        super("", Assets.skin());
        setDefaults("", "");
    }

    public InspectBox(String title, String description){
        super("", Assets.skin());
        setDefaults(title, description);
    }

    private void setDefaults(String title, String description){
        setVisible(false);
        setMovable(true);
        setModal(false);
        setClip(false);
        setTransform(true);
        Table table = new Table();
        Label desc = new Label(title + "\n\n" + description, Assets.skin());
        table.add(desc);
        this.add(table).expand().top().left().padTop(30).padLeft(15).padRight(15).padBottom(15);
        this.pack();
    }
}