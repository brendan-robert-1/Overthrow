package com.mygdx.game.state;

import com.mygdx.game.encounters.Outfitter;
import com.mygdx.game.encounters.OverthrowActs.ActType;

import java.util.UUID;

public class NewGameGenerator {
    public static void generateNewGame(Character.CharacterType characterType){
        UUID seed = UUID.randomUUID();
        MapGraph graph = MapGraph.buildNormalGameMap(seed, ActType.FARMS);
        GameNode currentNode = graph.getNodesForFloor(0).get(0);
        graph.printMap(currentNode);
        GameState state = GameState.getInstance();
        state.setRunSeed(seed);
        state.setCharacterSlots(characterSlotsGenerator(characterType));
        state.setCoin(99);
        state.setInventory(new Inventory());
        state.setMapGraph(graph);
        state.setCurrentNode(currentNode);
        state.setCurrentFloor(0);
    }
    private static CharacterSlots characterSlotsGenerator(Character.CharacterType characterType){
        return new CharacterSlots(
                null,
                null,
                null,
                Character.generateNewCharacter(characterType)
        );
    }
    private static EnemySlots newEmptyEnemySlots(){
        return new EnemySlots(null, null, null, null);
    }
}


