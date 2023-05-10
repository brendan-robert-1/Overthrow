package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.screens.widgets.inventory.InventorySlot;

public class HudTooltip extends Window {
    private Skin skin;
    private Label description;

    public HudTooltip(final Skin skin){
        super("", skin);
        this.skin = skin;

        description = new Label("", skin);

        this.add(description).pad(20);
        this.pad(5);
        this.pack();
        this.setVisible(false);
    }

    public void updateDescription(String desc){
        description.setText(desc);
        this.pack();
    }
}
