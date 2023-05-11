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
import com.mygdx.game.ActionState;
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
import com.mygdx.game.state.GameNode;
import com.mygdx.game.state.GameState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainGameScreen extends ScreenAdapter implements OutfitterObserver, NextEncounterObserver, FightObserver, AbilityUsedObserver,
         CombatRewardSelectedObserver, ProceedObserver {
    private static final Logger logger = LogManager.getLogger(MainGameScreen.class);
    private Stage stage;
    private Viewport viewport;
    private Table pathSelectContainer;
    private NextEncounter  pathSelectWindow;
    private Table encounterContainer;
    private GameNode encounterWindow;
    private DragAndDrop abilitySelectDragAndDrop = new DragAndDrop();
    private EntireInGameScreenTable entireInGameScreenTable;
    private Array<TurnObserver> turnObservers = new Array<>();
    private TurnCarousel turnCarousel = new TurnCarousel();
    private CombatProcessor combatProcessor;
    private FightNode fight;
    private CombatRewardsWindow combatRewardsWindow;


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
        InventoryUi.getInstance().setKeepWithinStage(false);
        entireInGameScreenTable = new EntireInGameScreenTable();

        pathSelectWindow = new NextEncounter();
        pathSelectContainer = new Table();
        pathSelectContainer.setFillParent(true);
        pathSelectContainer.setVisible(false);
        pathSelectContainer.add(pathSelectWindow).expand().bottom().right().padBottom(20);

        encounterWindow = GameState.getInstance().getCurrentNode();
        encounterContainer = new Table();
        encounterContainer.setFillParent(true);
        encounterContainer.add(encounterWindow).expand().bottom().right().padBottom(20);

        stage.addActor(entireInGameScreenTable);
        stage.addActor(pathSelectContainer);
        stage.addActor(encounterContainer);
        stage.addActor(InventoryUi.getInstance());

        for(Actor actor : InventoryUi.getInstance().getInventoryActors()){
            stage.addActor(actor);
        }
        stage.addActor(HudTooltip.getInstance());
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
        Array<InventoryItemLocation> itemLocations = new Array<>();
        itemLocations.add(new InventoryItemLocation(0, itemSelected.getItemTypeId(),1, itemSelected.getDisplayName()));
        InventoryUi.populateInventory(itemLocations);
        displayPathSelectWindow();
    }




    private void displayPathSelectWindow(){
        pathSelectWindow = new NextEncounter();
        pathSelectContainer.clearChildren();
        pathSelectContainer.add(pathSelectWindow).expand().bottom().right().padBottom(20);
        pathSelectContainer.setVisible(true);
        pathSelectContainer.pack();
    }

    @Override
    public void onNotify(GameNode.NodeType nodeSelected, NextEncounterEvent event) {
        if(nodeSelected == GameNode.NodeType.BASIC_FIGHT
                || nodeSelected == GameNode.NodeType.BOSS_FIGHT
                || nodeSelected == GameNode.NodeType.ELITE_FIGHT){
            processCombat();

            encounterWindow = GameState.getInstance().getCurrentNode();
            encounterContainer = new Table();
            encounterContainer.setFillParent(true);
            encounterContainer.pack();
            encounterWindow.pack();
            encounterContainer.add(encounterWindow).expand().bottom().right().padBottom(100);
            stage.addActor(encounterContainer);
            pathSelectContainer.setVisible(false);
        } else {

            encounterWindow = GameState.getInstance().getCurrentNode();
            encounterContainer = new Table();
            encounterContainer.setFillParent(true);
            encounterContainer.pack();
            encounterWindow.pack();
            encounterContainer.add(encounterWindow).expand().fill().pad(150);
            stage.addActor(encounterContainer);
            pathSelectContainer.setVisible(false);
        }
    }



    private void processCombat() {
        entireInGameScreenTable.displayAbilitySelectPanel();
        displayTurnCarousel();
        fight = (FightNode) GameState.getInstance().getCurrentNode();
        combatProcessor = new CombatProcessor(fight);
        onNotify("", FightEvent.FIGHT_STARTED);
    }


    @Override
    public void onNotify(CharacterPanel targetCharacter, Ability ability, CharacterPanel sourceCharacter) {
        logger.info("DEBUG THIS target: " + targetCharacter.getName() + " "+ ability.name() + " "  + sourceCharacter.getName());
        ActionState.playerStateValue = ActionState.StateValue.NOT_ALLOWED_TO_ACT;
        ability.execute(targetCharacter, sourceCharacter, fight);
        sourceCharacter.getCharacter().resetChargeTime();
        combatProcessor.increaseChargeTimeExcept(sourceCharacter);
        combatProcessor.processEndOfTurnEvents();
    }


    @Override
    public void onNotify(String text, FightEvent event) {
        switch(event){
            case TURN_OVER ->{
                logger.info("Turn over main game screen is now executing a new turn.");
                executeTurn();
            }
            case FIGHT_STARTED -> {
                logger.info("Fight has begun main game screen is executing a new turn.");
                executeTurn();
            }
        }
    }

    private void executeTurn(){
        updateScreenMidCombat();
        if(combatProcessor.lost()) {
            logger.info("You lost!");
            ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
        } else if(combatProcessor.won()){
            displayCombatRewards();
            turnCarousel.remove();
            return;
        }
        incrementTurnCarousel();
        CharacterPanel activeCharacter = combatProcessor.calculateActiveTurn();
        logger.info(activeCharacter.getName() + "'s turn. They have " + activeCharacter.getChargeTime() + " charge time.");
        if(activeCharacter.getCharacter().isFriendly()){
            abilitySelectDragAndDrop = new DragAndDrop();
            entireInGameScreenTable.populateAbilities(activeCharacter, abilitySelectDragAndDrop, fight.getEnemyTeam());
            ActionState.playerStateValue = ActionState.StateValue.ALLOWED_TO_ACT;
            logger.info("Waiting for user input.");
        } else {
            combatProcessor.processFoeTurn(activeCharacter);
        }
    }

    private void displayTurnCarousel(){
        turnCarousel.setVisible(true);
    }
    private void incrementTurnCarousel(){
        java.util.List<CharacterPanel> futureTurns = combatProcessor.projectFutureTurnOrder();
        turnCarousel.remove();
        turnCarousel = new TurnCarousel();
        turnCarousel.populateCarousel(futureTurns);
        stage.addActor(turnCarousel);
    }

    public void updateScreenMidCombat(){
        System.out.println("Updating screen");
        fight.update();
        entireInGameScreenTable.update();
    }

    private void displayCombatRewards() {
        logger.info("you won the fight here are the rewards");
        encounterContainer.setVisible(false);
        entireInGameScreenTable.hideAbilitySelectPanel();
        combatRewardsWindow = new CombatRewardsWindow();
        stage.addActor(combatRewardsWindow);
    }



    // Notified after a combat reward option is chosen
    @Override
    public void onNotify(CombatRewardSelectedObserver.RewardType type) {
        combatRewardsWindow.remove();
        displayPathSelectWindow();
    }



    @Override
    public void onNotify(ProceedEvent event) {
        encounterContainer.setVisible(false);
        displayPathSelectWindow();
    }
}