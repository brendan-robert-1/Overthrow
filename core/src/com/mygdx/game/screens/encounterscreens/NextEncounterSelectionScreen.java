package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.GameStateScreen;
import com.mygdx.game.screens.widgets.*;
import com.mygdx.game.screens.widgets.inventory.InventoryUi;
import com.mygdx.game.state.GameNode;
import com.mygdx.game.state.GameState;

import java.util.Set;

public class NextEncounterSelectionScreen extends ScreenAdapter {
    private GameState gameState = GameState.getInstance();
    private Stage stage;
    private Viewport viewport;
    private InventoryUi inventoryUi;
    @Override
    public void show() {
        stage = new Stage();
        viewport = new ScreenViewport();
        Table entireScreen = new EntireInGameScreenTable();
        Table encounterOptions = populateEncounterOptions();
        inventoryUi = new InventoryUi();
        inventoryUi.setKeepWithinStage(false);
        entireScreen.add(new TopBar(inventoryUi)).expand().fillX().colspan(2).top();
        entireScreen.row();
        entireScreen.add(new Team()).expand().bottom().left().pad(40);
        entireScreen.add(encounterOptions).expand().bottom().right().padBottom(20);
        stage.addActor(entireScreen);
        stage.addActor(inventoryUi);
        for(Actor actor : inventoryUi.getInventoryActors()){
            stage.addActor(actor);
        }
        Gdx.input.setInputProcessor(stage);
    }

    private Table populateEncounterOptions(){
        Table table = new Table();
        Label label = new Label("Where to next?", Assets.skin(), "title");
        table.row();
        table.add(label).padRight(18);
        GameNode currentNode = GameState.getInstance().getCurrentNode();
        Set<GameNode> nextEncounterOptions = GameState.getInstance().getMapGraph().getGraph().successors(currentNode);
        for(GameNode encounter : nextEncounterOptions){
            TextButton button = new TextButton(encounter.getDisplayName(), Assets.skin());
            button.pad(25);

            button.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println("Choose encounter: " + encounter.getDisplayName());
                    GameState.getInstance().setCurrentNode(encounter);
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new GameStateScreen());
                }
            });
            table.add(button).pad(10);
        }
        return table;
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
}
