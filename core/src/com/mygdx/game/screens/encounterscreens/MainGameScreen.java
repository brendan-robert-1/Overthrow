package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ActionState;
import com.mygdx.game.Assets;
import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.render.AnimatedActor;
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
import com.mygdx.game.encounters.GameNode;
import com.mygdx.game.sfx.ScreenTransitionAction;
import com.mygdx.game.sfx.ScreenTransitionActor;
import com.mygdx.game.state.GameState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.mygdx.game.Assets.MASTER_VOLUME;

public class MainGameScreen extends ScreenAdapter implements OutfitterObserver, NextEncounterObserver, FightObserver, AbilityUsedObserver,
         CombatRewardSelectedObserver, ProceedObserver, BackgroundChangeObserver {
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
    private AnimatedActor background;
    private ScreenTransitionActor transitionActor;
    private Sound encounterBackgroundSound;
    private AbilitySelectPanel abilitySelectPanel;


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
        abilitySelectPanel = new AbilitySelectPanel(HudTooltip.getInstance());
        pathSelectWindow = new NextEncounter();
        pathSelectContainer = new Table();
        pathSelectContainer.setFillParent(true);
        pathSelectContainer.setVisible(false);
        pathSelectContainer.add(pathSelectWindow).expand().bottom().right().padBottom(20);

        encounterWindow = GameState.getInstance().getCurrentNode();
        playAmbient(encounterWindow.ambientSounds());

        encounterContainer = new Table();
        encounterContainer.setFillParent(true);
        encounterContainer.add(encounterWindow).expand().bottom().right().padBottom(20);

        transitionActor = new ScreenTransitionActor();
        transitionActor.setVisible(false);
        addTransitionToScreen(2);


        TextureAtlas atlas = Assets.getAssetManager().get("overthrow.atlas", TextureAtlas.class);
        Animation<TextureRegion> farms = new Animation(0.1f, atlas.findRegions("town"), Animation.PlayMode.LOOP);
        background = new AnimatedActor(farms);
        background.setScaling(Scaling.fit);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage.addActor(background);
        stage.addActor(abilitySelectPanel);
        stage.addActor(transitionActor);
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
            addTransitionToScreen();
            encounterWindow = GameState.getInstance().getCurrentNode();
            updateBackgroundForEncounter(encounterWindow.backgroundAsset());
            playAmbient(encounterWindow.ambientSounds());
            encounterContainer = new Table();
            encounterContainer.setFillParent(true);
            encounterContainer.pack();
            encounterWindow.pack();
            encounterContainer.add(encounterWindow).expand().bottom().right().padBottom(100);
            stage.addActor(encounterContainer);
            pathSelectContainer.setVisible(false);
        } else {
            addTransitionToScreen();
            encounterWindow = GameState.getInstance().getCurrentNode();
            updateBackgroundForEncounter(encounterWindow.backgroundAsset());
            playAmbient(encounterWindow.ambientSounds());
            encounterContainer = new Table();
            encounterContainer.setFillParent(true);
            encounterContainer.pack();
            encounterWindow.pack();
            encounterContainer.add(encounterWindow).expand().fill().pad(150);
            stage.addActor(encounterContainer);
            pathSelectContainer.setVisible(false);
        }
    }



    private void playAmbient(String ambientSoundAsset) {
        if(encounterBackgroundSound != null)  encounterBackgroundSound.stop();
        if(ambientSoundAsset != null){
            encounterBackgroundSound = Assets.getInstance().getSoundAsset(ambientSoundAsset);
            encounterBackgroundSound.loop(MASTER_VOLUME);
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
        ActionState.playerStateValue = ActionState.StateValue.NOT_ALLOWED_TO_ACT;
        ability.execute(targetCharacter, sourceCharacter, fight);
        updateScreenMidCombat();
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
            populateAbilities(activeCharacter, abilitySelectDragAndDrop);
            ActionState.playerStateValue = ActionState.StateValue.ALLOWED_TO_ACT;
            logger.info("Waiting for user input.");
        } else {
            combatProcessor.processFoeTurn(activeCharacter);
        }
    }

    private void populateAbilities(CharacterPanel activeCharacter, DragAndDrop dragAndDrop) {
        abilitySelectPanel.populateAbilities(activeCharacter, dragAndDrop, fight.getEnemyTeam());
        abilitySelectPanel.setVisible(true);
        abilitySelectPanel.setPosition(Gdx.graphics.getWidth()/2 - abilitySelectPanel.getWidth()/2, Gdx.graphics.getHeight()/2 - abilitySelectPanel.getHeight()/2);
        abilitySelectPanel.toFront();

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
        abilitySelectPanel.update();
        fight.update();
        entireInGameScreenTable.update();
    }

    public void updateScreenInventoryChange(){
        abilitySelectPanel.update();
        entireInGameScreenTable.update();
    }

    public void updateScreenMidCombatFoeTurn(){
        fight.update();
        entireInGameScreenTable.update();
    }

    private void displayCombatRewards() {
        logger.info("you won the fight here are the rewards");
        abilitySelectPanel.setVisible(false);
        encounterContainer.setVisible(false);
        entireInGameScreenTable.hideAbilitySelectPanel();
        combatRewardsWindow = new CombatRewardsWindow(fight.getEnemyTeam().getCombatRewards());
        Sound combatRewardSound = Assets.getInstance().getSoundAsset("coin-reward.mp3");
        combatRewardSound.play(MASTER_VOLUME);
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



    @Override
    public void onNotify(String backgroundAsset, BackgroundChange event) {
        if(background != null){
            background.remove();
            TextureAtlas atlas = Assets.getAssetManager().get("overthrow.atlas", TextureAtlas.class);
            Animation<TextureRegion> farms = new Animation<TextureRegion>(0.1f, atlas.findRegions(backgroundAsset), Animation.PlayMode.LOOP);
            background = new AnimatedActor(farms);
            background.setScaling(Scaling.fit);
            background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            stage.addActor(background);
            background.toBack();
        }

    }


    private void updateBackgroundForEncounter(String backgroundAsset) {
        if(background != null){
            background.remove();
            if(backgroundAsset == null){backgroundAsset = "farms-fire";}
            TextureAtlas atlas = Assets.getAssetManager().get("overthrow.atlas", TextureAtlas.class);
            Animation<TextureRegion> backgroundRegions = new Animation<TextureRegion>(0.1f, atlas.findRegions(backgroundAsset), Animation.PlayMode.LOOP);
            background = new AnimatedActor(backgroundRegions);
            background.setScaling(Scaling.fit);
            background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            stage.addActor(background);
            background.toBack();
        }
    }
    public void addTransitionToScreen(int duration){
        transitionActor.setVisible(true);
        stage.addAction(
                Actions.sequence(
                        Actions.addAction(ScreenTransitionAction.transition(ScreenTransitionAction.ScreenTransitionType.FADE_IN, duration), transitionActor)));
    }

    public void addTransitionToScreen(){
        addTransitionToScreen(1);
    }


}