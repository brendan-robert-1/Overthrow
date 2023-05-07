package com.mygdx.game.screens.widgets.inventory;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;

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

//        TextureAtlas atlas = Assets.getAssetManager().get("overthrow.atlas", TextureAtlas.class);
//        TextureRegionDrawable trd = new TextureRegionDrawable(atlas.findRegion(InventoryItem.ItemTypeId.HIDE_SHIELD.toString()));
//        Image image = new Image(trd);

        payload.setDragActor(getActor());
        event.getStage().addActor(getActor());
        System.out.println("x, y: " + x + ", " +y);
        System.out.println("actor.getWidth(): " + actor.getWidth() + ", actor.getHeight(): " + actor.getHeight());
        System.out.println("stage x: " + event.getStageX() + ", stage y: " + event.getStageY());
        actor.setZIndex(Integer.MAX_VALUE);

        return payload;
    }
    @Override
    public void dragStop (InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
        if( target == null ){
            sourceSlot.add(payload.getDragActor());
        }
    }

    public InventorySlot getSourceSlot() {
        return sourceSlot;
    }
}
