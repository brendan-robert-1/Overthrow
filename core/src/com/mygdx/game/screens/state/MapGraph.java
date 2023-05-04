package com.mygdx.game.screens.state;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.ImmutableGraph;
import com.google.common.graph.MutableGraph;
import com.mygdx.game.encounters.OverthrowActs.ActType;


import java.util.*;

@SuppressWarnings("UnstableApiUsage")
public class MapGraph {
    private static final int ACT_ENCOUNTER_COUNT = 14;
    private ImmutableGraph<GameNode> graph;
    private Map<Integer, List<GameNode>> floorNumberToEncounters;
    private MapGraph(Map<Integer, List<GameNode>> floorNumberToEncounters){
        this.floorNumberToEncounters = floorNumberToEncounters;
        graph = buildGraphFrom(floorNumberToEncounters);
    }

    /**
     * Dumb and graphs all encounters to all next floor encounters, will need to trim method to only add edges on some
     * @param floorNumberToEncounters
     * @return
     */
    private ImmutableGraph<GameNode> buildGraphFrom(Map<Integer, List<GameNode>> floorNumberToEncounters) {
        MutableGraph<GameNode> graph = GraphBuilder.directed().build();
        for(Integer floorNumber : floorNumberToEncounters.keySet()){
            List<GameNode> encounters = floorNumberToEncounters.get(floorNumber);
            List<GameNode> nextFloorEncounters = getNextFloorEncounters(floorNumber, floorNumberToEncounters);
            if(nextFloorEncounters != null){
                addEdgesFrom(encounters, nextFloorEncounters, graph);
            }
        }
        return ImmutableGraph.copyOf(graph);
    }

    private void addEdgesFrom(List<GameNode> encounters, List<GameNode> nextFloorEncounters, MutableGraph<GameNode> graph) {
        for(GameNode encounter : encounters){
            for(GameNode nextFloorEncounter : nextFloorEncounters){
                graph.putEdge(encounter, nextFloorEncounter);
            }
        }
    }

    private List<GameNode> getNextFloorEncounters(Integer floorNumber, Map<Integer, List<GameNode>> floorNumberToEncounters) {
       if(floorNumber >= floorNumberToEncounters.size()){
           return null;
       }else {
           return floorNumberToEncounters.get(floorNumber + 1);
       }
    }

    public static MapGraph buildNormalGameMap(UUID seed, ActType actType){
        Map<Integer, List<GameNode>> floorNumberToEncounters = new HashMap<>();
        for(int floorNumber = 0; floorNumber <= ACT_ENCOUNTER_COUNT; floorNumber++){
            List<GameNode> floorEncounters = generateEncountersForFloor(floorNumber, actType);
            floorNumberToEncounters.put(floorNumber, floorEncounters);
        }
        return new MapGraph(floorNumberToEncounters);
    }

    private static List<GameNode> generateEncountersForFloor(int floorNumber, ActType actType){
        List<GameNode> currentFloorEncounters = new ArrayList<>();
        Map<Integer, Integer> floorNumberToMinEncounterSize = floorNumberToMinEncounterSize();
        Map<Integer, Integer> floorNumberToMaxEncounterSize = floorNumberToMaxEncounterSize();
        int minSize = floorNumberToMinEncounterSize.get(floorNumber);
        int maxSize = floorNumberToMaxEncounterSize.get(floorNumber);
        int encountersThisFloor;
        if(minSize == maxSize){
            encountersThisFloor = minSize;
        }else {
            encountersThisFloor = new Random().nextInt(minSize,maxSize);
        }

        for(int i = 0; i < encountersThisFloor; i++){
            GameNode encounter = NodeGenerator.generateRandomNode(actType, floorNumber);
            currentFloorEncounters.add(encounter);
        }
        return currentFloorEncounters;
    }

    private static Map<Integer, Integer> floorNumberToMaxEncounterSize(){
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        map.put(1, 3);
        map.put(2, 4);
        map.put(3, 4);
        map.put(4, 4);
        map.put(5, 4);
        map.put(6, 4);
        map.put(7, 4);
        map.put(8, 4);
        map.put(9, 4);
        map.put(10, 4);
        map.put(11, 4);
        map.put(12, 4);
        map.put(13, 4);
        map.put(14, 1);
        return map;
    }
    private static Map<Integer, Integer> floorNumberToMinEncounterSize(){
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        map.put(1, 2);
        map.put(2, 2);
        map.put(3, 2);
        map.put(4, 2);
        map.put(5, 2);
        map.put(6, 2);
        map.put(7, 2);
        map.put(8, 2);
        map.put(9, 2);
        map.put(10, 2);
        map.put(11, 2);
        map.put(12, 2);
        map.put(13, 2);
        map.put(14, 1);
        return map;
    }




    public void printMap(GameNode startingNode){
        System.out.println("0         " + getConsoleCodeFor(startingNode.getNodeType()) + "         ");

    }
    
    private String getConsoleCodeFor(GameNode.NodeType nodeType){
        switch(nodeType){
            case OUTFITTER -> {return "O";}
            case BASIC_FIGHT -> {return "F";}
            case ELITE_FIGHT -> {return "EF";}
            case BOSS_FIGHT -> {return "B";}
            case MARKET -> {return "M";}
            case ARMOR_MERCHANT -> {return "AM";}
            case WEAPON_MERCHANT -> {return "WM";}
            case BLACKSMITH -> {return "BS";}
            case WISHING_WELL -> {return "WW";}
            case SAUNA -> {return "S";}
            case ABILITY_TRAINER -> {return "AT";}
            case GEM_MERCHANT -> {return "GM";}
            case QUESTION_MARK -> {return "?";}
            case CHEST -> {return "T";}
            default -> throw new IllegalStateException("Unexpected value: " + nodeType);
        }
    }

    public List<GameNode> getNodesForFloor(int floorNumber){
        return floorNumberToEncounters.get(floorNumber);
    }



    public ImmutableGraph<GameNode> getGraph() {
        return graph;
    }
}
