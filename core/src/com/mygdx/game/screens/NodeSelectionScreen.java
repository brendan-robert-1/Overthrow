package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Assets;
import com.mygdx.game.OverthrowScreenAdapter;
import com.mygdx.game.state.GameNode;
import com.mygdx.game.state.GameState;

import java.util.Set;

public class NodeSelectionScreen extends OverthrowScreenAdapter {
    private GameState gameState = GameState.getInstance();

    @Override
    public void show() {
        Table market = new Table();
        stage.addActor(market);
        market.setFillParent(true);
        populateOutfitter(market);
    }

    private void populateOutfitter(Table table){
        Label label = new Label("Choose the next floor", Assets.skin());
        table.add(label);
        addFloors(table);
        table.row();
        table.setFillParent(true);
    }
    private void addFloors(Table table){
        Set<GameNode> nextEncounters = gameState.getMapGraph().getGraph().successors(gameState.getCurrentNode());
        for(GameNode encounter : nextEncounters){
            addEncounterButton(encounter);
        }
    }
    private TextButton addEncounterButton(GameNode encounter){
        TextButton outfitterOption =  new TextButton(encounter.getNodeType().toString(), Assets.skin());
        outfitterOption.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                redirectNextNode();
            }
        });
        return outfitterOption;
    }

    private void redirectNextNode(){

    }
}
