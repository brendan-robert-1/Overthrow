package com.mygdx.game.state;

import java.util.UUID;

public record GameState (
    UUID runSeed,
    CharacterSlots characterSlots,
    int coin,
    Inventory inventory,
    MapGraph mapGraph,
    GameNode currentNode,
    int currentFloor
){
    public GameState withCoin(int coinValue){
        return new GameState(
                runSeed,
                characterSlots,
                coinValue,
                inventory,
                mapGraph,
                currentNode,
                currentFloor
        );
    }
    public GameState withCurrentNode(GameNode newCurrentNode){
        return new GameState(
                runSeed,
                characterSlots,
                coin,
                inventory,
                mapGraph,
                newCurrentNode,
                currentFloor
        );
    }
    public GameState withCurrentFloor(int newCurrentFloor){
        return new GameState(
                runSeed,
                characterSlots,
                coin,
                inventory,
                mapGraph,
                currentNode,
                newCurrentFloor
        );
    }
}