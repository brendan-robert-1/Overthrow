package com.mygdx.game.encounters;

import com.mygdx.game.screens.widgets.InventoryItem;
import com.mygdx.game.screens.widgets.InventoryItem.ItemTypeId;
import com.mygdx.game.state.items.InventoryItemFactory;

import java.util.ArrayList;
import java.util.List;

public class Outfitter extends Encounter{
    public Outfitter(){
        super(NodeType.OUTFITTER, "Outfitter");
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Outfitter");
        return sb.toString();
    }

    public List<InventoryItem> buildOutfitterItems() {
        List<InventoryItem> outfitterList = new ArrayList<>();
        outfitterList.add(InventoryItemFactory.getInstance().of(ItemTypeId.HIDE_SHIELD));
        outfitterList.add(InventoryItemFactory.getInstance().of(ItemTypeId.HEALTH_POT));
        outfitterList.add(InventoryItemFactory.getInstance().of(ItemTypeId.RUSTY_DAGGER));
        return outfitterList;
    }
}
