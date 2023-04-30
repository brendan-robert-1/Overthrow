package com.mygdx.game.state;

import java.util.*;
import java.util.stream.IntStream;
import com.mygdx.game.state.GameNode.NodeType;
//TODO import from json
public class FarmsEncounterChanceTable {

    private final Integer TOTAL_SLOTS = 154;
    private Map<Integer, NodeType> slots = new HashMap<>();

    public FarmsEncounterChanceTable(){
        generateWeights();
    }

    private void generateWeights(){
        putIntoWeights(IntStream.rangeClosed(1, 60).iterator(), NodeType.BASIC_FIGHT);
        putIntoWeights(IntStream.rangeClosed(61, 100).iterator(), NodeType.QUESTION_MARK);
        putIntoWeights(IntStream.rangeClosed(101, 110).iterator(), NodeType.SAUNA);
        putIntoWeights(IntStream.rangeClosed(111, 125).iterator(), NodeType.ELITE_FIGHT);
        putIntoWeights(IntStream.rangeClosed(126, 135).iterator(), NodeType.MARKET);
        putIntoWeights(IntStream.rangeClosed(136, 142).iterator(), NodeType.ARMOR_MERCHANT);
        putIntoWeights(IntStream.rangeClosed(143, 148).iterator(), NodeType.WEAPON_MERCHANT);
        putIntoWeights(IntStream.rangeClosed(149, 154).iterator(), NodeType.GEM_MERCHANT);
    }

    private void putIntoWeights(PrimitiveIterator.OfInt iterator, NodeType nodeType){
        while(iterator.hasNext()){
            int slot = iterator.nextInt();
            slots.put(slot, nodeType);
        }
    }

    private NodeType getNodeTypeForRoll(int roll){
        if(roll > TOTAL_SLOTS || roll < 1){
            throw  new IllegalArgumentException("wrong");
        }
        return slots.get(roll);
    }

    private int roll(){
      return new Random().nextInt(this.getTotalSlots());
    }

    public NodeType generateRandomNode(){
        return getNodeTypeForRoll(roll());
    }


    public Integer getTotalSlots() {
        return TOTAL_SLOTS;
    }
}
