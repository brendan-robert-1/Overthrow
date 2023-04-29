package com.mygdx.game.state;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.ImmutableGraph;

import java.util.Map;
import java.util.UUID;

public class MapGraph {
    private ImmutableGraph<GameNode> gameMap;
    private MapGraph(ImmutableGraph<GameNode> gameMap){this.gameMap = gameMap;}

    public static MapGraph buildNormalGameMap(UUID seed){
        GameNode outfitter = GameNode.of(GameNode.NodeType.OUTFITTER);
        ImmutableGraph<GameNode> graph = GraphBuilder.directed()
                .<GameNode>immutable()
                .putEdge(outfitter, GameNode.of(GameNode.NodeType.FIGHT))
                .putEdge(outfitter, GameNode.of(GameNode.NodeType.MARKET))
                .putEdge(outfitter, GameNode.of(GameNode.NodeType.FIGHT))
                .build();
        return new MapGraph(graph);
    }

    public static MapGraph buildNormalGameMap(UUID seed, GameNode startingNode){
        GameNode outfitter = GameNode.of(GameNode.NodeType.OUTFITTER);
        ImmutableGraph<GameNode> graph = GraphBuilder.directed()
                .<GameNode>immutable()
                .putEdge(startingNode, GameNode.of(GameNode.NodeType.FIGHT))
                .putEdge(startingNode, GameNode.of(GameNode.NodeType.MARKET))
                .putEdge(startingNode, GameNode.of(GameNode.NodeType.FIGHT))
                .build();
        return new MapGraph(graph);
    }



    public ImmutableGraph<GameNode> getGameMap() {
        return gameMap;
    }
}
