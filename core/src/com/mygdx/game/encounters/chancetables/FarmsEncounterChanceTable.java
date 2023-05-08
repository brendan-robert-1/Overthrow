package com.mygdx.game.encounters.chancetables;

import java.util.*;
import java.util.stream.IntStream;
import com.mygdx.game.state.GameNode.NodeType;
//TODO import from json
public class FarmsEncounterChanceTable {
    private Map<Integer, Map<Integer,NodeType>> floorNumberToSlots = new HashMap<>();

    public FarmsEncounterChanceTable(){
        generateWeights();
    }

    private void generateWeights() {
        generateWeightsFloor0();
        generateWeightsFloor1();
        generateWeightsFloor2();
        generateWeightsFloor3();
        generateWeightsFloor4();
        generateWeightsFloor5();
        generateWeightsFloor6();
        generateWeightsFloor7();
        generateWeightsFloor8();
        generateWeightsFloor9();
        generateWeightsFloor10();
        generateWeightsFloor11();
        generateWeightsFloor12();
        generateWeightsFloor13();
        generateWeightsFloor14();
    }



    public NodeType generateRandomNode(int floorNumber){
        return getNodeTypeForRoll(roll(floorNumber), floorNumber);
    }

    private int roll(int floorNumber){
        return new Random().nextInt(1, this.floorNumberToSlots.get(floorNumber).size());
    }
    private NodeType getNodeTypeForRoll(int roll, int floorNumber){
        return floorNumberToSlots.get(floorNumber).get(roll);
    }

    private void generateWeightsFloor0(){
        Map<Integer,NodeType> weights = new HashMap<>();
        putIntoWeights(weights, IntStream.rangeClosed(1, 20).iterator(), NodeType.OUTFITTER);
        floorNumberToSlots.put(0, weights);
    }

    private void generateWeightsFloor1(){
        Map<Integer,NodeType> weights = new HashMap<>();
        putIntoWeights(weights, IntStream.rangeClosed(1, 20).iterator(), NodeType.BASIC_FIGHT);
        putIntoWeights(weights, IntStream.rangeClosed(21, 30).iterator(), NodeType.BASIC_FIGHT);
        putIntoWeights(weights, IntStream.rangeClosed(31, 35).iterator(), NodeType.BASIC_FIGHT);
        floorNumberToSlots.put(1, weights);
    }

    private void generateWeightsFloor2(){
        Map<Integer,NodeType> weights = new HashMap<>();
        putIntoWeights(weights, IntStream.rangeClosed(1, 20).iterator(), NodeType.BASIC_FIGHT);
        putIntoWeights(weights, IntStream.rangeClosed(21, 30).iterator(), NodeType.QUESTION_MARK);
        putIntoWeights(weights, IntStream.rangeClosed(31, 37).iterator(), NodeType.MARKET);
        floorNumberToSlots.put(2, weights);
    }

    private void generateWeightsFloor3(){
        Map<Integer,NodeType> weights = new HashMap<>();
        putIntoWeights(weights, IntStream.rangeClosed(1, 20).iterator(), NodeType.BASIC_FIGHT);
        putIntoWeights(weights, IntStream.rangeClosed(21, 30).iterator(), NodeType.QUESTION_MARK);
        putIntoWeights(weights, IntStream.rangeClosed(31, 40).iterator(), NodeType.MARKET);
        floorNumberToSlots.put(3, weights);
    }

    private void generateWeightsFloor4(){
        Map<Integer,NodeType> weights = new HashMap<>();
        putIntoWeights(weights, IntStream.rangeClosed(1, 20).iterator(), NodeType.BASIC_FIGHT);
        putIntoWeights(weights, IntStream.rangeClosed(21, 30).iterator(), NodeType.QUESTION_MARK);
        putIntoWeights(weights, IntStream.rangeClosed(31, 37).iterator(), NodeType.MARKET);
        floorNumberToSlots.put(4, weights);
    }

    private void generateWeightsFloor5(){
        Map<Integer,NodeType> weights = new HashMap<>();
        putIntoWeights(weights, IntStream.rangeClosed(1, 20).iterator(), NodeType.BASIC_FIGHT);
        putIntoWeights(weights, IntStream.rangeClosed(21, 30).iterator(), NodeType.QUESTION_MARK);
        putIntoWeights(weights, IntStream.rangeClosed(31, 50).iterator(), NodeType.ELITE_FIGHT);
        floorNumberToSlots.put(5, weights);
    }

    private void generateWeightsFloor6(){
        Map<Integer,NodeType> weights = new HashMap<>();
        putIntoWeights(weights, IntStream.rangeClosed(1, 60).iterator(), NodeType.BASIC_FIGHT);
        putIntoWeights(weights, IntStream.rangeClosed(61, 100).iterator(), NodeType.QUESTION_MARK);
        putIntoWeights(weights, IntStream.rangeClosed(101, 110).iterator(), NodeType.SAUNA);
        putIntoWeights(weights, IntStream.rangeClosed(111, 125).iterator(), NodeType.ELITE_FIGHT);
        putIntoWeights(weights, IntStream.rangeClosed(126, 135).iterator(), NodeType.MARKET);
        putIntoWeights(weights, IntStream.rangeClosed(136, 142).iterator(), NodeType.ARMOR_MERCHANT);
        putIntoWeights(weights, IntStream.rangeClosed(143, 148).iterator(), NodeType.WEAPON_MERCHANT);
        putIntoWeights(weights, IntStream.rangeClosed(149, 154).iterator(), NodeType.GEM_MERCHANT);
        floorNumberToSlots.put(6, weights);
    }
    private void generateWeightsFloor7(){
        Map<Integer,NodeType> weights = new HashMap<>();
        putIntoWeights(weights, IntStream.rangeClosed(1, 60).iterator(), NodeType.CHEST);
        floorNumberToSlots.put(7, weights);
    }
    private void generateWeightsFloor8(){
        Map<Integer,NodeType> weights = new HashMap<>();
        putIntoWeights(weights, IntStream.rangeClosed(1, 60).iterator(), NodeType.BASIC_FIGHT);
        putIntoWeights(weights, IntStream.rangeClosed(61, 100).iterator(), NodeType.QUESTION_MARK);
        putIntoWeights(weights, IntStream.rangeClosed(101, 110).iterator(), NodeType.SAUNA);
        putIntoWeights(weights, IntStream.rangeClosed(111, 125).iterator(), NodeType.ELITE_FIGHT);
        putIntoWeights(weights, IntStream.rangeClosed(126, 135).iterator(), NodeType.MARKET);
        putIntoWeights(weights, IntStream.rangeClosed(136, 142).iterator(), NodeType.ARMOR_MERCHANT);
        putIntoWeights(weights, IntStream.rangeClosed(143, 148).iterator(), NodeType.WEAPON_MERCHANT);
        putIntoWeights(weights, IntStream.rangeClosed(149, 154).iterator(), NodeType.GEM_MERCHANT);
        floorNumberToSlots.put(8, weights);
    }
    private void generateWeightsFloor9(){
        Map<Integer,NodeType> weights = new HashMap<>();
        putIntoWeights(weights, IntStream.rangeClosed(1, 60).iterator(), NodeType.BASIC_FIGHT);
        putIntoWeights(weights, IntStream.rangeClosed(61, 100).iterator(), NodeType.QUESTION_MARK);
        putIntoWeights(weights, IntStream.rangeClosed(101, 110).iterator(), NodeType.SAUNA);
        putIntoWeights(weights, IntStream.rangeClosed(111, 125).iterator(), NodeType.ELITE_FIGHT);
        putIntoWeights(weights, IntStream.rangeClosed(126, 135).iterator(), NodeType.MARKET);
        putIntoWeights(weights, IntStream.rangeClosed(136, 142).iterator(), NodeType.ARMOR_MERCHANT);
        putIntoWeights(weights, IntStream.rangeClosed(143, 148).iterator(), NodeType.WEAPON_MERCHANT);
        putIntoWeights(weights, IntStream.rangeClosed(149, 154).iterator(), NodeType.GEM_MERCHANT);
        floorNumberToSlots.put(9, weights);
    }
    private void generateWeightsFloor10(){
        Map<Integer,NodeType> weights = new HashMap<>();
        putIntoWeights(weights, IntStream.rangeClosed(1, 60).iterator(), NodeType.BASIC_FIGHT);
        putIntoWeights(weights, IntStream.rangeClosed(61, 100).iterator(), NodeType.QUESTION_MARK);
        putIntoWeights(weights, IntStream.rangeClosed(101, 110).iterator(), NodeType.SAUNA);
        putIntoWeights(weights, IntStream.rangeClosed(111, 125).iterator(), NodeType.ELITE_FIGHT);
        putIntoWeights(weights, IntStream.rangeClosed(126, 135).iterator(), NodeType.MARKET);
        putIntoWeights(weights, IntStream.rangeClosed(136, 142).iterator(), NodeType.ARMOR_MERCHANT);
        putIntoWeights(weights, IntStream.rangeClosed(143, 148).iterator(), NodeType.WEAPON_MERCHANT);
        putIntoWeights(weights, IntStream.rangeClosed(149, 154).iterator(), NodeType.GEM_MERCHANT);
        floorNumberToSlots.put(10, weights);
    }
    private void generateWeightsFloor11(){
        Map<Integer,NodeType> weights = new HashMap<>();
        putIntoWeights(weights, IntStream.rangeClosed(1, 60).iterator(), NodeType.BASIC_FIGHT);
        putIntoWeights(weights, IntStream.rangeClosed(61, 100).iterator(), NodeType.QUESTION_MARK);
        putIntoWeights(weights, IntStream.rangeClosed(101, 110).iterator(), NodeType.SAUNA);
        putIntoWeights(weights, IntStream.rangeClosed(111, 125).iterator(), NodeType.ELITE_FIGHT);
        putIntoWeights(weights, IntStream.rangeClosed(126, 135).iterator(), NodeType.MARKET);
        putIntoWeights(weights, IntStream.rangeClosed(136, 142).iterator(), NodeType.ARMOR_MERCHANT);
        putIntoWeights(weights, IntStream.rangeClosed(143, 148).iterator(), NodeType.WEAPON_MERCHANT);
        putIntoWeights(weights, IntStream.rangeClosed(149, 154).iterator(), NodeType.GEM_MERCHANT);
        floorNumberToSlots.put(11, weights);
    }
    private void generateWeightsFloor12(){
        Map<Integer,NodeType> weights = new HashMap<>();
        putIntoWeights(weights, IntStream.rangeClosed(1, 60).iterator(), NodeType.BASIC_FIGHT);
        putIntoWeights(weights, IntStream.rangeClosed(61, 100).iterator(), NodeType.QUESTION_MARK);
        putIntoWeights(weights, IntStream.rangeClosed(101, 110).iterator(), NodeType.SAUNA);
        putIntoWeights(weights, IntStream.rangeClosed(111, 125).iterator(), NodeType.ELITE_FIGHT);
        putIntoWeights(weights, IntStream.rangeClosed(126, 135).iterator(), NodeType.MARKET);
        putIntoWeights(weights, IntStream.rangeClosed(136, 142).iterator(), NodeType.ARMOR_MERCHANT);
        putIntoWeights(weights, IntStream.rangeClosed(143, 148).iterator(), NodeType.WEAPON_MERCHANT);
        putIntoWeights(weights, IntStream.rangeClosed(149, 154).iterator(), NodeType.GEM_MERCHANT);
        floorNumberToSlots.put(12, weights);
    }
    private void generateWeightsFloor13(){
        Map<Integer,NodeType> weights = new HashMap<>();
        putIntoWeights(weights, IntStream.rangeClosed(1, 10).iterator(), NodeType.SAUNA);
        floorNumberToSlots.put(13, weights);
    }
    private void generateWeightsFloor14(){
        Map<Integer,NodeType> weights = new HashMap<>();
        putIntoWeights(weights, IntStream.rangeClosed(1, 10).iterator(), NodeType.BOSS_FIGHT);
        floorNumberToSlots.put(14, weights);
    }

    private void putIntoWeights(Map<Integer,NodeType> weights, PrimitiveIterator.OfInt iterator, NodeType nodeType){
        while(iterator.hasNext()){
            int slot = iterator.nextInt();
            weights.put(slot, nodeType);
        }
    }




}
