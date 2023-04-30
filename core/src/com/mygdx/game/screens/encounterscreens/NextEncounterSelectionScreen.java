package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Assets;
import com.mygdx.game.encounters.Encounter;
import com.mygdx.game.screens.GameStateScreen;
import com.mygdx.game.state.GameNode;
import com.mygdx.game.state.GameState;

import java.util.Set;

public class NextEncounterSelectionScreen extends InGameEncounterScreen{
    public NextEncounterSelectionScreen(GameState gameState) {
        super(gameState);
    }

    @Override
    public void show() {
        Table table = new Table();
        table.right().padRight(200);
        stage.addActor(table);
        table.setFillParent(true);
        populateEncounterOptions(table);
        Gdx.input.setInputProcessor(stage);
    }

    private void populateEncounterOptions(Table table){
        Label label = new Label("Where to next?", Assets.skin());
        table.row();
        table.add(label).padRight(18);
        GameNode currentNode = getGameState().currentNode();
        Set<GameNode> nextEncounterOptions = getGameState().mapGraph().getGameMap().successors(currentNode);
        for(GameNode encounter : nextEncounterOptions){
            TextButton button = new TextButton(encounter.getNodeType().toString(), Assets.skin());
            button.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println("Choose encounter: " + encounter.getNodeType());
                    GameState withNextEncounter = getGameState().withCurrentNode(encounter);
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new GameStateScreen(withNextEncounter));
                }
            });
            table.add(button).pad(10);
        }
    }
}
