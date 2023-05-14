package com.mygdx.game.state;

import com.mygdx.game.encounters.GameNode;

import java.util.UUID;

public class GameState {
    private UUID runSeed;
    private MapGraph mapGraph;
    private GameNode currentNode;
    private int currentFloor;

    private static GameState instance;

    private GameState(){}

    public static GameState getInstance(){
        if(instance == null){
            instance = new GameState();
        }
        return instance;
    }

    public UUID getRunSeed() {
        return runSeed;
    }



    public GameState setRunSeed(UUID runSeed) {
        this.runSeed = runSeed;
        return this;
    }




    public MapGraph getMapGraph() {
        return mapGraph;
    }



    public GameState setMapGraph(MapGraph mapGraph) {
        this.mapGraph = mapGraph;
        return this;
    }



    public GameNode getCurrentNode() {
        return currentNode;
    }



    public GameState setCurrentNode(GameNode currentNode) {
        this.currentNode = currentNode;
        return this;
    }



    public int getCurrentFloor() {
        return currentFloor;
    }



    public GameState setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
        return this;
    }
}