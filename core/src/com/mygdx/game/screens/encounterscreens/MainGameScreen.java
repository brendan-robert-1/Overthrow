package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.screens.widgets.*;
import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.screens.widgets.inventory.InventoryItemLocation;
import com.mygdx.game.screens.widgets.inventory.InventoryUi;
import com.mygdx.game.screens.widgets.nextencounter.NextEncounter;
import com.mygdx.game.screens.widgets.nextencounter.NextEncounterObserver;
import com.mygdx.game.screens.widgets.outfitter.OutfitterObserver;
import com.mygdx.game.state.GameNode;
import com.mygdx.game.state.GameState;

public class MainGameScreen extends ScreenAdapter implements OutfitterObserver, NextEncounterObserver {
    private GameState gameState = GameState.getInstance();
    private InventoryUi inventoryUi;
    private Stage stage;
    private Viewport viewport;
    private NextEncounter nextEncounterWindow;
    private Table nextEncounterContainer;

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
        Table entireScreen = new EntireInGameScreenTable();
        GameNode encounterWindow = GameState.getInstance().getCurrentNode();
        encounterWindow.setInventoryUi(inventoryUi);

        entireScreen.add(new TopBar(inventoryUi)).expand().fillX().colspan(2).top();
        entireScreen.row();
        entireScreen.add(new Team()).expand().bottom().left().pad(40);
        entireScreen.add(encounterWindow).expand().bottom().right().padBottom(20);
        nextEncounterWindow = new NextEncounter();

        nextEncounterContainer = new Table();
        nextEncounterContainer.setFillParent(true);
        nextEncounterContainer.setVisible(false);
        nextEncounterContainer.add(nextEncounterWindow).expand().bottom().right().padBottom(20);
        stage.addActor(entireScreen);
        stage.addActor(nextEncounterContainer);
        stage.addActor(inventoryUi);
        for(Actor actor : inventoryUi.getInventoryActors()){
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
        itemLocations.add(new InventoryItemLocation(0, itemSelected.getItemTypeId(), 1, itemSelected.getDisplayName()));
        InventoryUi.populateInventory(inventoryUi.getInventorySlotTable(),itemLocations,inventoryUi.getDragAndDrop());
        displayNextEncounterWindow();
    }

    private void displayNextEncounterWindow(){
        System.out.println("Displaying next encounter selection window");
        nextEncounterContainer.setVisible(true);
        nextEncounterContainer.setDebug(true);
        nextEncounterContainer.pack();
    }



    @Override
    public void onNotify(GameNode.NodeType nodeSelected, NextEncounterEvent event) {
        System.out.println("Main screen is being notified of the next encounter being selected: " + nodeSelected);
        nextEncounterContainer.setVisible(false);
        nextEncounterContainer.pack();
    }
}