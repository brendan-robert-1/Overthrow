package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.character.buff.Buff;
import com.mygdx.game.screens.widgets.inventory.InventorySlot;
import com.mygdx.game.screens.widgets.inventory.InventorySlotTooltip;

public class HudTooltipListener extends InputListener {
    private HudTooltip tooltip;
    private boolean isInside = false;
    private Vector2 currentCords;
    private Vector2 offset;

    public HudTooltipListener(HudTooltip tooltip){
        this.tooltip = tooltip;
        this.currentCords = new Vector2(0,0);
        this.offset = new Vector2(20,10);
    }


    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
        Actor actor = event.getListenerActor();
        String desc = getDescFrom(actor);
        isInside = true;

        currentCords.set(x, y);
        actor.localToStageCoordinates(currentCords);
        tooltip.updateDescription(desc);
        tooltip.setPosition(currentCords.x + offset.x, currentCords.y + offset.y);
        tooltip.toFront();
        tooltip.setVisible(true);
    }



    private String getDescFrom(Actor actor) {
        if(actor instanceof BuffSprite) {
            StringBuilder sb = new StringBuilder();
            BuffSprite bs = (BuffSprite) actor;
            sb.append(bs.getBuff().displayName());
            sb.append(System.getProperty("line.separator"));
            sb.append(bs.getBuff().getDisplayText());
            sb.append(System.getProperty("line.separator"));
            sb.append(bs.getBuff().turnsRemaining + " turns remaining");
            return sb.toString();
        } else if(actor instanceof  AbilitySelect) {
            StringBuilder sb = new StringBuilder();
            AbilitySelect as = (AbilitySelect) actor;
            sb.append(as.getAbility().name());
            sb.append(System.getProperty("line.separator"));
            sb.append(as.getAbility().description());
            return sb.toString();
        } else {
            return "<empty>";
        }
    }



    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
       Actor actor = event.getListenerActor();
        tooltip.setVisible( false);
        isInside = false;

        currentCords.set(x, y);
        actor.localToStageCoordinates(currentCords);
    }

    @Override
    public boolean mouseMoved(InputEvent event, float x, float y){
        Actor actor = event.getListenerActor();
        if( isInside ){
            currentCords.set(x, y);
            actor.localToStageCoordinates(currentCords);

            tooltip.setPosition(currentCords.x+offset.x, currentCords.y+offset.y);
        }
        return false;
    }


    @Override
    public void touchDragged (InputEvent event, float x, float y, int pointer) {
//        InventorySlot inventorySlot = (InventorySlot)event.getListenerActor();
//        tooltip.setVisible(false);
    }

    @Override
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        return true;
    }

}

