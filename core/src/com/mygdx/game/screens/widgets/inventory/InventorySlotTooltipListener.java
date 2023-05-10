package com.mygdx.game.screens.widgets.inventory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class InventorySlotTooltipListener extends InputListener {

    private InventorySlotTooltip tooltip;
    private boolean isInside = false;
    private Vector2 currentCords;
    private Vector2 offset;

    public InventorySlotTooltipListener(InventorySlotTooltip tooltip){
        this.tooltip = tooltip;
        this.currentCords = new Vector2(0,0);
        this.offset = new Vector2(20,10);
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
        InventorySlot inventorySlot = (InventorySlot)event.getListenerActor();

        isInside = true;

        currentCords.set(x, y);
        inventorySlot.localToStageCoordinates(currentCords);
        tooltip.updateDescription(inventorySlot);
        tooltip.setPosition(currentCords.x + offset.x, currentCords.y + offset.y);
        tooltip.toFront();
        tooltip.setVisible(inventorySlot, true);
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
        InventorySlot inventorySlot = (InventorySlot)event.getListenerActor();
        tooltip.setVisible(inventorySlot, false);
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

            tooltip.setPosition(currentCords.x+offset.x, currentCords.y+offset.y);
        }
        return false;
    }


    @Override
    public void touchDragged (InputEvent event, float x, float y, int pointer) {
        InventorySlot inventorySlot = (InventorySlot)event.getListenerActor();
        tooltip.setVisible(inventorySlot, false);
    }

    @Override
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        return true;
    }

}
