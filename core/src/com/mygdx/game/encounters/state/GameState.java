package com.mygdx.game.encounters.state;

import java.util.UUID;

public class GameState {
    private UUID runSeed;
    private CharacterSlots characterSlots;
    private int coin;
    private Inventory inventory;
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



    public CharacterSlots getCharacterSlots() {
        return characterSlots;
    }



    public GameState setCharacterSlots(CharacterSlots characterSlots) {
        this.characterSlots = characterSlots;
        return this;
    }



    public int getCoin() {
        return coin;
    }


    public GameState setCoin(int coin) {
        this.coin = coin;
        return this;
    }

    public void increaseCoinBy(int increaseBy){
        this.coin = coin + increaseBy;
    }

    public void decreaseCoinBy(int decreaseCoinBy){
        if(decreaseCoinBy > coin){
            throw new IllegalArgumentException("cant have negative coin dummy");
        }
        this.coin = coin - decreaseCoinBy;
    }



    public Inventory getInventory() {
        return inventory;
    }



    public GameState setInventory(Inventory inventory) {
        this.inventory = inventory;
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