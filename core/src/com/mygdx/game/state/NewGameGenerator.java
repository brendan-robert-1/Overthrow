package com.mygdx.game.state;

import com.mygdx.game.encounters.Outfitter;
import com.mygdx.game.encounters.OverthrowActs.ActType;

import java.util.UUID;

public class NewGameGenerator {
    public static GameState generateNewGame(Character.CharacterType characterType){
        UUID seed = UUID.randomUUID();
        MapGraph graph = MapGraph.buildNormalGameMap(seed, ActType.FARMS);
        GameNode currentNode = graph.getNodesForFloor(0).get(0);
        graph.printMap(currentNode);
        return new GameState(
                seed,
                characterSlotsGenerator(characterType),
                99,
                new Inventory(),
                graph,
                currentNode,
                0
        );
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


