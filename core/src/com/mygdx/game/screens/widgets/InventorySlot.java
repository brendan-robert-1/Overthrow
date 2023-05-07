package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.widgets.InventoryItem.ItemUseType;

public class InventorySlot extends Stack implements InventorySlotSubject {
    private Stack defaultBackground;
    private Image decal;
    private int numItems = 0;
    private Label numItemsLabel;
    private ItemUseType filterItemType;
    private Array<InventorySlotObserver> observers;


    public InventorySlot(){

        defaultBackground = new Stack();
        observers = new Array<>();
        decal = new Image();
        Image image = new Image(Assets.skin().getPatch("inventory-background"));
        defaultBackground.add(image);
        numItemsLabel = new Label(String.valueOf(numItemsLabel), Assets.skin());
        numItemsLabel.setAlignment(Align.bottomRight);
        numItemsLabel.setVisible(false);
        this.add(defaultBackground);
        this.add(numItemsLabel);
    }

    public InventorySlot(ItemUseType filterItemType, Image decal){
        this();
        this.filterItemType = filterItemType;
        this.decal = decal;
        defaultBackground.add(decal);
    }


    public void clearAllInventoryItems(boolean sendRemoveNotification) {
        if(hasItem()){
            SnapshotArray<Actor> arrayChildren = this.getChildren();
            int numItems = getNumItems();
            for(int i = 0; i < numItems; i++){
                decrementItemCount(sendRemoveNotification);
                arrayChildren.pop();
            }
        }
    }

    public void decrementItemCount(boolean sendRemoveNotification) {
        numItems--;
        numItemsLabel.setText(String.valueOf(numItems));
        if( defaultBackground.getChildren().size == 1 ){
            defaultBackground.add(decal);
        }
        checkVisibilityOfItemCount();
        if( sendRemoveNotification ){
            notify(this, InventorySlotObserver.SlotEvent.REMOVED_ITEM);
        }
    }

    private void checkVisibilityOfItemCount(){
        if( numItems < 2){
            numItemsLabel.setVisible(false);
        }else{
            numItemsLabel.setVisible(true);
        }
    }

    public boolean hasItem(){
        if( hasChildren() ){
            SnapshotArray<Actor> items = this.getChildren();
            if( items.size > 2 ){
                return true;
            }
        }
        return false;
    }

    public int getNumItems(){
        if( hasChildren() ){
            SnapshotArray<Actor> items = this.getChildren();
            return items.size - 2;
        }
        return 0;
    }

    public boolean doesAcceptItemUseType(ItemUseType itemUseType){
        if(filterItemType == null){
            return true;
        }
        return filterItemType.equals(itemUseType);
    }

    public InventoryItem getTopInventoryItem(){
        InventoryItem actor = null;
        if( hasChildren() ){
            SnapshotArray<Actor> items = this.getChildren();
            if( items.size > 2 ){
                actor = (InventoryItem) items.peek();
            }
        }
        return actor;
    }
    public void incrementItemCount(boolean sendAddNotification) {
        numItems++;
        numItemsLabel.setText(String.valueOf(numItems));
        if( defaultBackground.getChildren().size > 1 ){
            defaultBackground.getChildren().pop();
        }
        checkVisibilityOfItemCount();
        if( sendAddNotification ){
            notify(this, InventorySlotObserver.SlotEvent.ADDED_ITEM);
        }
    }

    @Override
    public void addObserver(InventorySlotObserver slotObserver) {
        observers.add(slotObserver);
    }

    @Override
    public void removeObserver(InventorySlotObserver slotObserver) {
        observers.removeValue(slotObserver, true);
    }

    @Override
    public void removeAllObservers() {
        for(InventorySlotObserver observer: observers){
            observers.removeValue(observer, true);
        }
    }

    @Override
    public void notify(final InventorySlot slot, InventorySlotObserver.SlotEvent event) {
        for(InventorySlotObserver observer: observers){
            observer.onNotify(slot, event);
        }
    }
    @Override
    public void add(Actor actor) {
        super.add(actor);

        if( numItemsLabel == null ){
            return;
        }

        if( !actor.equals(defaultBackground) && !actor.equals(numItemsLabel) ) {
            incrementItemCount(true);
        }
    }

    static public void swapSlots(InventorySlot source, InventorySlot inventorySlotTarget, InventoryItem dragActor){
        //check if items can accept each other, otherwise, no swap
        if( !inventorySlotTarget.doesAcceptItemUseType(dragActor.getItemUseType()) ||
                !source.doesAcceptItemUseType(inventorySlotTarget.getTopInventoryItem().getItemUseType())) {
            source.add(dragActor);
            return;
        }

        //swap
        Array<Actor> tempArray = source.getAllInventoryItems();
        tempArray.add(dragActor);
        source.add(inventorySlotTarget.getAllInventoryItems());
        inventorySlotTarget.add(tempArray);
    }

    public Array<Actor> getAllInventoryItems() {
        Array<Actor> items = new Array<Actor>();
        if( hasItem() ){
            SnapshotArray<Actor> arrayChildren = this.getChildren();
            int numInventoryItems =  arrayChildren.size - 2;
            for(int i = 0; i < numInventoryItems; i++) {
                decrementItemCount(true);
                items.add(arrayChildren.pop());
            }
        }
        return items;
    }

    public void add(Array<Actor> array) {
        for( Actor actor : array){
            super.add(actor);

            if( numItemsLabel == null ){
                return;
            }

            if( !actor.equals(defaultBackground) && !actor.equals(numItemsLabel) ) {
                incrementItemCount(true);
            }
        }
    }


    public InventorySlot setFilterItemType(ItemUseType filterItemType) {
        this.filterItemType = filterItemType;
        return this;
    }
}
