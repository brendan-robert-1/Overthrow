package com.mygdx.game.encounters;

import com.mygdx.game.state.ItemSlot;

import java.util.ArrayList;
import java.util.List;

public class Blacksmith extends Encounter{
    public Blacksmith(){
        super(NodeType.BLACKSMITH);
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Blacksmith");
        return sb.toString();
    }
}
