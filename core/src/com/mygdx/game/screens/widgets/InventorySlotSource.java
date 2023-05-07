package com.mygdx.game.screens.widgets;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.mygdx.game.Assets;

public class InventorySlotSource extends Source {

    private DragAndDrop dragAndDrop;
    private InventorySlot sourceSlot;

    public InventorySlotSource(InventorySlot sourceSlot, DragAndDrop dragAndDrop) {
        super(sourceSlot.getTopInventoryItem());
        this.sourceSlot = sourceSlot;
        this.dragAndDrop = dragAndDrop;
    }

    @Override
    public Payload dragStart(InputEvent event, float x, float y, int pointer) {
        Payload payload = new Payload();

        Actor actor = getActor();
        if( actor == null ){
            return null;
        }

        InventorySlot source = (InventorySlot)actor.getParent();
        if( source == null ){
            return null;
        }else{
            sourceSlot = source;
        }

        sourceSlot.decrementItemCount(true);

        payload.setDragActor(actor);
        dragAndDrop.setDragActorPosition(-x, -y + actor.getHeight());

        return payload;
    }
    @Override
    public void dragStop (InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
        if( target == null ){
            sourceSlot.add(payload.getDragActor());
        }
        System.out.println("stopping drag: -" + x + ", -" + y);
    }

    public InventorySlot getSourceSlot() {
        return sourceSlot;
    }
}
