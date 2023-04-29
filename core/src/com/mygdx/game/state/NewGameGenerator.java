package com.mygdx.game.state;

import com.mygdx.game.encounters.Outfitter;

import java.util.UUID;

public class NewGameGenerator {
    public static GameState generateNewGame(Character.CharacterType characterType){
        UUID seed = UUID.randomUUID();
        Outfitter outfitter = new Outfitter();
        MapGraph graph = MapGraph.buildNormalGameMap(seed, outfitter);
        return new GameState(
                seed,
                characterSlotsGenerator(characterType),
                99,
                new Inventory(),
                graph,
                outfitter
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


