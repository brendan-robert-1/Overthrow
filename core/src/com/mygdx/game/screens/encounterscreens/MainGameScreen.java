package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.screens.encounterscreens.combat.CombatProcessor;
import com.mygdx.game.screens.widgets.*;
import com.mygdx.game.screens.widgets.fight.*;
import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.screens.widgets.inventory.InventoryItemLocation;
import com.mygdx.game.screens.widgets.inventory.InventoryUi;
import com.mygdx.game.screens.widgets.nextencounter.NextEncounter;
import com.mygdx.game.screens.widgets.nextencounter.NextEncounterObserver;
import com.mygdx.game.screens.widgets.outfitter.OutfitterObserver;
import com.mygdx.game.state.Character;
import com.mygdx.game.state.GameNode;
import com.mygdx.game.state.GameState;

import java.util.List;

public class MainGameScreen extends ScreenAdapter implements OutfitterObserver, NextEncounterObserver, FightObserver, TurnSubject {
    private GameState gameState = GameState.getInstance();
    private InventoryUi inventoryUiContainer;
    private Stage stage;
    private Viewport viewport;
    private Table pathSelectContainer;

    private Table encounterContainer;
    private GameNode encounterWindow;
    private AbilitySelectPanel  abilitySelectPanel = new AbilitySelectPanel();
    private Array<TurnObserver> turnObservers = new Array<>();
    private TurnCarousel turnCarousel = new TurnCarousel();
    private CombatProcessor combatProcessor;

    private static MainGameScreen instance;
    private MainGameScreen(){
    }

    public static MainGameScreen getInstance(){
        if(instance == null){
            instance = new MainGameScreen();
        }
        return instance;
    }

    @Override
    public void show() {
        stage = new Stage();
        viewport = new ScreenViewport();
        addObserver(turnCarousel);
        inventoryUiContainer = new InventoryUi();
        inventoryUiContainer.setKeepWithinStage(false);
        Table hudContainer = new EntireInGameScreenTable();


        hudContainer.add(new TopBar(inventoryUiContainer)).expand().fillX().colspan(2).top();
        hudContainer.row();
        hudContainer.add(new Team()).expand().bottom().left().pad(40);

        NextEncounter pathSelectWindow; pathSelectWindow = new NextEncounter();
        pathSelectContainer = new Table();
        pathSelectContainer.setFillParent(true);
        pathSelectContainer.setVisible(false);
        pathSelectContainer.add(pathSelectWindow).expand().bottom().right().padBottom(20);

        encounterWindow = GameState.getInstance().getCurrentNode();
        encounterWindow.setInventoryUi(inventoryUiContainer);
        encounterContainer = new Table();
        encounterContainer.setFillParent(true);
        encounterContainer.add(encounterWindow).expand().bottom().right().padBottom(20);

        abilitySelectPanel.setVisible(false);
        turnCarousel.setVisible(false);
        hudContainer.row();
        hudContainer.add(abilitySelectPanel);

        stage.addActor(hudContainer);
        stage.addActor(pathSelectContainer);
        stage.addActor(encounterContainer);
        stage.addActor(inventoryUiContainer);
        stage.addActor(turnCarousel);

        for(Actor actor : inventoryUiContainer.getInventoryActors()){
            stage.addActor(actor);
        }
        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.1f,.1f, .15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }


    @Override
    public void onNotify(InventoryItem itemSelected, OutfitterEvent event) {
        System.out.println("The main game screen is being notified of an outfit being selected.");
        Array<InventoryItemLocation> itemLocations = new Array<>();
        itemLocations.add(new InventoryItemLocation(0, itemSelected.getItemTypeId(),1, itemSelected.getDisplayName()));
        InventoryUi.populateInventory(inventoryUiContainer.getInventorySlotTable(),itemLocations, inventoryUiContainer.getDragAndDrop());
        displayPathSelectWindow();
    }

    private void displayPathSelectWindow(){
        System.out.println("Displaying next encounter selection window");
        pathSelectContainer.setVisible(true);
        pathSelectContainer.setDebug(true);
        pathSelectContainer.pack();
    }

    @Override
    public void onNotify(GameNode.NodeType nodeSelected, NextEncounterEvent event) {
        if(nodeSelected == GameNode.NodeType.BASIC_FIGHT
                || nodeSelected == GameNode.NodeType.BOSS_FIGHT
                || nodeSelected == GameNode.NodeType.ELITE_FIGHT){
            processCombat(nodeSelected, event);
        }
        System.out.println("Main screen is being notified of the next encounter being selected: " + nodeSelected);
        encounterWindow = GameState.getInstance().getCurrentNode();
        encounterWindow.setInventoryUi(inventoryUiContainer);
        encounterContainer = new Table();
        encounterContainer.setFillParent(true);
        encounterContainer.pack();
        encounterWindow.pack();
        encounterContainer.add(encounterWindow).expand().bottom().right().padBottom(100);
        stage.addActor(encounterContainer);
        pathSelectContainer.setVisible(false);
    }



    private void processCombat(GameNode.NodeType nodeSelected, NextEncounterEvent event) {
        displayAbilitySelectPanel();
        displayTurnCarousel();
        FightNode fight = (FightNode) GameState.getInstance().getCurrentNode();
        combatProcessor = new CombatProcessor(fight);
        java.util.List<Character> futureTurns = combatProcessor.projectFutureTurnOrder();
        notify("turn started", futureTurns, TurnObserver.TurnEvent.TURN_STARTED);
        Character activeCharacter = combatProcessor.calculateActiveTurn();
        if(activeCharacter.isFriendly()){
            populateAbilityPanel(activeCharacter);
            //Wait for ability used  fire event
        }else {
            //display box with ability, source, target and effect for 1.5 seconds
            //process ability damage
            //fire turn ended
        }


        /*
          ##  Fire turn started event
           ## determine first active character
            if enemy show a 3 second box with what the attack is
            change game state for attack
            fire turn ended event

            if friendly populate ability panel
            set up drag and drop source and target with the enemy panels
            when drag and drop to target is selected check if viable target
            if viable target process ability stage change
            first turn end event

         */


    }



    private void populateAbilityPanel(Character activeCharacter) {
        abilitySelectPanel.populateAbillites(activeCharacter);
        abilitySelectPanel.pack();
    }



    @Override
    public void onNotify(String text, FightEvent event) {
        System.out.println("Main game screen is being notified of fight event");
        switch(event){
            case ENEMY_TURN_TAKEN -> {}
            case FIGHT_OVER -> {
                hideTurnCarousel();
                hideAbilitySelectPanel();
            }
        }
    }

    private void displayAbilitySelectPanel(){
        System.out.println("Displaying ability panel.");
        abilitySelectPanel.setVisible(true);
        abilitySelectPanel.pack();
    }
    private void displayTurnCarousel(){
        System.out.println("Displaying turn carousel.");
        turnCarousel.setVisible(true);
        abilitySelectPanel.pack();
    }

    private void hideAbilitySelectPanel(){
        abilitySelectPanel.setVisible(true);
    }
    private void hideTurnCarousel(){
        turnCarousel.setVisible(true);
    }



    @Override
    public void addObserver(TurnObserver observer) {
        turnObservers.add(observer);
    }



    @Override
    public void removeObserver(TurnObserver observer) {
        turnObservers.removeValue(observer, true);
    }



    @Override
    public void removeAllObservers() {
        for(TurnObserver turnObserver : turnObservers){
            turnObservers.removeValue(turnObserver, true);
        }
    }




    @Override
    public void notify(String eventd, List<Character> futureTurns,TurnObserver.TurnEvent event) {
        for(TurnObserver turnObserver : turnObservers){
            turnObserver.onNotify(eventd, futureTurns, event);
        }
    }
}