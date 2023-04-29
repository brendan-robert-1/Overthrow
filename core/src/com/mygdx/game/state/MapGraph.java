package com.mygdx.game.state;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.ImmutableGraph;
import com.mygdx.game.encounters.Market;
import com.mygdx.game.encounters.OverthrowActs;
import com.mygdx.game.encounters.fights.FightFactory;

import java.util.Map;
import java.util.UUID;

public class MapGraph {
    private ImmutableGraph<GameNode> gameMap;
    private MapGraph(ImmutableGraph<GameNode> gameMap){this.gameMap = gameMap;}

    public static MapGraph buildNormalGameMap(UUID seed, GameNode startingNode){
        ImmutableGraph<GameNode> graph = GraphBuilder.directed()
                .<GameNode>immutable()
                .putEdge(startingNode, FightFactory.generateRandomFightFor(OverthrowActs.ActType.FARMS))
                .putEdge(startingNode, new Market())
                .putEdge(startingNode, FightFactory.generateRandomFightFor(OverthrowActs.ActType.FARMS))
                .build();
        return new MapGraph(graph);
    }



    public ImmutableGraph<GameNode> getGameMap() {
        return gameMap;
    }
}
