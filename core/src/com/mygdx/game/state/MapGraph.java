package com.mygdx.game.state;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.ImmutableGraph;
import com.google.common.graph.MutableGraph;
import com.mygdx.game.encounters.Market;
import com.mygdx.game.encounters.OverthrowActs;
import com.mygdx.game.encounters.WishingWell;
import com.mygdx.game.encounters.fights.BossFightFactory;
import com.mygdx.game.encounters.fights.FightFactory;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@SuppressWarnings("UnstableApiUsage")
public class MapGraph {
    private final ImmutableGraph<GameNode> gameMap;
    private MapGraph(ImmutableGraph<GameNode> gameMap){this.gameMap = gameMap;}

    public static MapGraph buildNormalGameMap(UUID seed, GameNode startingNode){
        MutableGraph<GameNode> graph = GraphBuilder.directed().build();
        graph.addNode(startingNode);
        Set<GameNode> floorEncounters = addSuccessors(graph, startingNode, 3);
        addActBoss(graph, floorEncounters);
        return new MapGraph(ImmutableGraph.copyOf(graph));
    }

    private static Set<GameNode> addSuccessors(MutableGraph<GameNode> graph, GameNode currentNode, int encountersOnFloor){
        Set<GameNode> currentFloorEncounters = new HashSet<>();
        GameNode firstNode = FightFactory.generateRandomFightFor(OverthrowActs.ActType.FARMS);
        GameNode secondNode = new Market();
        GameNode thirdNode = new WishingWell();

        graph.putEdge(currentNode, firstNode);
        graph.putEdge(currentNode, secondNode);
        graph.putEdge(currentNode, thirdNode);

        currentFloorEncounters.add(firstNode);
        currentFloorEncounters.add(secondNode);
        currentFloorEncounters.add(thirdNode);
        return currentFloorEncounters;
    }

    private static void addActBoss(MutableGraph<GameNode> graph, Set<GameNode> floorEncounters){
        GameNode actBoss = BossFightFactory.generateRandomBossFightFor(OverthrowActs.ActType.FARMS);
        for(GameNode encounter : floorEncounters){
            graph.putEdge(encounter, actBoss);
        }
    }
    public ImmutableGraph<GameNode> getGameMap() {
        return gameMap;
    }
}
