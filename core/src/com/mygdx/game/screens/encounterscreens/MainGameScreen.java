package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;
import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.screens.MainMenuScreen;
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

public class MainGameScreen extends ScreenAdapter implements OutfitterObserver, NextEncounterObserver, FightObserver, AbilityUsedObserver, TurnSubject {
    private GameState gameState = GameState.getInstance();
    private InventoryUi inventoryUi;
    private Stage stage;
    private Viewport viewport;
    private Table pathSelectContainer;
    private Table encounterContainer;
    private GameNode encounterWindow;
    private DragAndDrop abilitySelectDragAndDrop = new DragAndDrop();
    private EntireInGameScreenTable entireInGameScreenTable;
    private Array<TurnObserver> turnObservers = new Array<>();
    private TurnCarousel turnCarousel = new TurnCarousel();
    private CombatProcessor combatProcessor;
    private HudTooltip hudTooltip = new HudTooltip(Assets.skin());
    private FightNode fight;


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
        inventoryUi = new InventoryUi();
        inventoryUi.setKeepWithinStage(false);
        entireInGameScreenTable = new EntireInGameScreenTable(inventoryUi, hudTooltip);

        NextEncounter pathSelectWindow; pathSelectWindow = new NextEncounter();
        pathSelectContainer = new Table();
        pathSelectContainer.setFillParent(true);
        pathSelectContainer.setVisible(false);
        pathSelectContainer.add(pathSelectWindow).expand().bottom().right().padBottom(20);

        encounterWindow = GameState.getInstance().getCurrentNode();
        encounterWindow.setInventoryUi(inventoryUi);
        encounterContainer = new Table();
        encounterContainer.setFillParent(true);
        encounterContainer.add(encounterWindow).expand().bottom().right().padBottom(20);

        stage.addActor(entireInGameScreenTable);
        stage.addActor(pathSelectContainer);
        stage.addActor(encounterContainer);
        stage.addActor(inventoryUi);

        for(Actor actor : inventoryUi.getInventoryActors()){
            stage.addActor(actor);
        }
        stage.addActor(hudTooltip);
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
        InventoryUi.populateInventory(inventoryUi.getInventorySlotTable(),itemLocations, inventoryUi.getDragAndDrop());
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
        encounterWindow.setInventoryUi(inventoryUi);
        encounterContainer = new Table();
        encounterContainer.setFillParent(true);
        encounterContainer.pack();
        encounterWindow.pack();
        encounterContainer.add(encounterWindow).expand().bottom().right().padBottom(100);
        stage.addActor(encounterContainer);
        pathSelectContainer.setVisible(false);
    }



    private void processCombat(GameNode.NodeType nodeSelected, NextEncounterEvent event) {
        entireInGameScreenTable.displayAbilitySelectPanel();
        displayTurnCarousel();
        fight = (FightNode) GameState.getInstance().getCurrentNode();
        fight.getEnemyTeam().setHudTooltip(hudTooltip);
        combatProcessor = new CombatProcessor(fight);
        executeTurn();
    }


    @Override
    public void onNotify(Character targetCharacter, Ability ability, Character sourceCharacter) {
        ability.execute(targetCharacter, sourceCharacter, fight);
        sourceCharacter.resetChargeTime();
        combatProcessor.increaseChargeTimeExcept(sourceCharacter);
        combatProcessor.processEndOfTurnEvents();
        entireInGameScreenTable.pack();
        updateScreenMidCombat();
        executeTurn();
    }

    private void executeTurn(){
        if(combatProcessor.lost()) {
            System.out.println("You lost!");
            ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
        } else if(combatProcessor.won()){
            displayCombatRewards();
            return;
        }
        java.util.List<Character> futureTurns = combatProcessor.projectFutureTurnOrder();
        turnCarousel = new TurnCarousel();
        turnCarousel.populateCarousel(futureTurns);
        stage.addActor(turnCarousel);
        //notify("turn started", futureTurns, TurnObserver.TurnEvent.TURN_STARTED);
        Character activeCharacter = combatProcessor.calculateActiveTurn();
        System.out.println("executing turn for: " + activeCharacter.getName());
        if(activeCharacter.isFriendly()){
            abilitySelectDragAndDrop = new DragAndDrop();
            entireInGameScreenTable.populateAbilities(activeCharacter, abilitySelectDragAndDrop, fight.getEnemyTeam());
            //Wait for ability used fire event
        }else {
           combatProcessor.executeEnemyTurn(activeCharacter);
            combatProcessor.processEndOfTurnEvents();
            updateScreenMidCombat();
            executeTurn();
        }
    }

    public void updateScreenMidCombat(){
        encounterContainer.remove();
        encounterContainer = new Table();
        encounterContainer.setFillParent(true);
        fight.update();
        entireInGameScreenTable.update();
        encounterContainer.add(fight).expand().bottom().right().padBottom(100);
        encounterContainer.pack();
        stage.addActor(encounterContainer);
    }

    private void displayCombatRewards() {
        System.out.println("you won the fight here are the rewards");
    }



    @Override
    public void onNotify(String text, FightEvent event) {
        System.out.println("Main game screen is being notified of fight event");
        switch(event){
            case ENEMY_TURN_TAKEN -> {}
            case FIGHT_OVER -> {
                hideTurnCarousel();
                entireInGameScreenTable.hideAbilitySelectPanel();
            }
        }
    }

    private void displayTurnCarousel(){
        System.out.println("Displaying turn carousel.");
        turnCarousel.setVisible(true);
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