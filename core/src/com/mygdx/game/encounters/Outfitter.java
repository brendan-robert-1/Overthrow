package com.mygdx.game.encounters;

import com.mygdx.game.state.items.ItemSlot;
import com.mygdx.game.state.items.ItemSlotFactory;
import com.mygdx.game.state.items.ItemType;

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

    public List<ItemSlot> buildOutfitterItems() {
        List<ItemSlot> outfitterList = new ArrayList<>();
        outfitterList.add(ItemSlotFactory.one(ItemType.HIDE_SHIELD));
        outfitterList.add(ItemSlotFactory.one(ItemType.RUSTY_DAGGER));
        outfitterList.add(ItemSlotFactory.one(ItemType.FRESHMAN_SPELLCRAFT_NOTEBOOK));
        return outfitterList;
    }
}
