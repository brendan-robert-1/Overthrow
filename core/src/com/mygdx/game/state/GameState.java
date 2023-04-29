package com.mygdx.game.state;

import java.util.UUID;

public record GameState (
    UUID runSeed,
    CharacterSlots characterSlots,
    int coin,
    Inventory inventory,
    MapGraph mapGraph,
    GameNode currentNode
){
    public GameState withCoin(int coinValue){
        return new GameState(
                runSeed,
                characterSlots,
                coinValue,
                inventory,
                mapGraph,
                currentNode
        );
    }
    public GameState withCurrentNode(GameNode newCurrentNode){
        return new GameState(
                runSeed,
                characterSlots,
                coin,
                inventory,
                mapGraph,
                newCurrentNode
        );
    }
}