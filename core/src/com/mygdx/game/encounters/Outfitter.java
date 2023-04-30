package com.mygdx.game.encounters;

public class Outfitter extends Encounter{
        public Outfitter(){
            super(NodeType.OUTFITTER);
        }



    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Outfitter");
        return sb.toString();
    }
}
