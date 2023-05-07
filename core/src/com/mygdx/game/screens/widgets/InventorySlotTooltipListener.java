package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Assets;

public class InventorySlotTooltipListener extends InputListener {

    private InventorySlotTooltip toolTip;
    private boolean isInside = false;
    private Vector2 currentCords;
    private Vector2 offset;

    public InventorySlotTooltipListener(InventorySlotTooltip toolTip){
        this.toolTip = toolTip;
        this.currentCords = new Vector2(0,0);
        this.offset = new Vector2(20,10);
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
        InventorySlot inventorySlot = (InventorySlot)event.getListenerActor();

        isInside = true;

        currentCords.set(x, y);
        inventorySlot.localToStageCoordinates(currentCords);
        toolTip.updateDescription(inventorySlot);
        toolTip.setPosition(currentCords.x + offset.x, currentCords.y + offset.y);
        toolTip.toFront();
        toolTip.setVisible(inventorySlot, true);
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
        InventorySlot inventorySlot = (InventorySlot)event.getListenerActor();
        toolTip.setVisible(inventorySlot, false);
        isInside = false;

        currentCords.set(x, y);
        inventorySlot.localToStageCoordinates(currentCords);
    }

    @Override
    public boolean mouseMoved(InputEvent event, float x, float y){
        InventorySlot inventorySlot = (InventorySlot)event.getListenerActor();
        if( isInside ){
            currentCords.set(x, y);
            inventorySlot.localToStageCoordinates(currentCords);

            toolTip.setPosition(currentCords.x+offset.x, currentCords.y+offset.y);
        }
        return false;
    }


    @Override
    public void touchDragged (InputEvent event, float x, float y, int pointer) {
        InventorySlot inventorySlot = (InventorySlot)event.getListenerActor();
        toolTip.setVisible(inventorySlot, false);
    }

    @Override
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        return true;
    }

}
