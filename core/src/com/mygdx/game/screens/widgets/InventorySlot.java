package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.utils.SnapshotArray;
import com.mygdx.game.Assets;

public class InventorySlot extends Stack {
    private Stack defaultBackground;
    private Image decal;
    private int numItems = 0;

    public InventorySlot(){
        defaultBackground = new Stack();
        Image image = new Image(Assets.skin().getPatch("ability-portrait"));
        defaultBackground.add(image);
        this.add(defaultBackground);
    }

    public InventorySlot(Image decal){
        this();
        this.decal = decal;
        defaultBackground.add(decal);
    }



    public void clearAllInventoryItems(boolean b) {
        if(hasItem()){
            SnapshotArray<Actor> arrayChildren = this.getChildren();
            int numItems = getNumItems();
            for(int i = 0; i < numItems; i++){
                decrementItemCount();
                arrayChildren.pop();
            }
        }
    }

    public void decrementItemCount() {
        numItems--;
        //_numItemsLabel.setText(String.valueOf(numItems));
        if( defaultBackground.getChildren().size == 1 ){
            defaultBackground.add(decal);
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

}
