package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.encounterscreens.InGameEncounterScreen;
import com.mygdx.game.state.GameNode;
import com.mygdx.game.state.GameState;

import java.util.Set;

public class NodeSelectionScreen extends ScreenAdapter {
    private GameState gameState = GameState.getInstance();
    private Stage stage;
    private Viewport viewport;

    @Override
    public void show() {
        stage = new Stage();
        viewport = new ScreenViewport();
        Table entireScreen = new Table();
        entireScreen.setFillParent(true);
        Table nextSelection = nextNodeSelection();
        entireScreen.add(nextSelection);
        stage.addActor(entireScreen);
        Gdx.input.setInputProcessor(stage);
    }

    private Table nextNodeSelection(){
        Table table = new Table();
        Label label = new Label("Choose the next floor", Assets.skin());
        table.add(label);
        Table floors = floors();
        table.add(floors);
        table.row();
        return table;
    }

    private Table floors(){
        Table table = new Table();
        Set<GameNode> nextEncounters = gameState.getMapGraph().getGraph().successors(gameState.getCurrentNode());
        for(GameNode encounter : nextEncounters){
            table.add(encounterButton(encounter));
        }
        return table;
    }

    private TextButton encounterButton(GameNode encounter){
        TextButton outfitterOption =  new TextButton(encounter.getNodeType().toString(), Assets.skin());
        outfitterOption.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                InGameEncounterScreen.redirectNextNode();
            }
        });
        return outfitterOption;
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
