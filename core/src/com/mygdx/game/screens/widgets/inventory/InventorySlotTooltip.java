package com.mygdx.game.screens.widgets.inventory;


import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class InventorySlotTooltip extends Window {

    private Skin skin;
    private Label description;

    public InventorySlotTooltip(final Skin skin){
        super("", skin);
        this.skin = skin;

        description = new Label("", skin);

        this.add(description).pad(20);
        this.pad(5);
        this.pack();
        this.setVisible(false);
    }

    public void setVisible(InventorySlot inventorySlot, boolean visible) {
        super.setVisible(visible);

        if( inventorySlot == null ){
            return;
        }

        if (!inventorySlot.hasItem()) {
            super.setVisible(false);
        }
    }

    public void updateDescription(InventorySlot inventorySlot){
        if( inventorySlot.hasItem() ){
            StringBuilder string = new StringBuilder();
            InventoryItem item = inventorySlot.getTopInventoryItem();
            string.append(item.getDisplayName());
            string.append(System.getProperty("line.separator"));
            string.append(item.getItemShortDescription());
            string.append(System.getProperty("line.separator"));
            string.append(String.format("Sell value: %s GP", item.getTradeValue()));
            description.setText(string.toString());
            this.pack();
        }else{
            description.setText("");
            this.pack();
        }

    }
}

