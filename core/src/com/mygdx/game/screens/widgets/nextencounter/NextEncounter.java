package com.mygdx.game.screens.widgets.nextencounter;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.GameStateScreen;
import com.mygdx.game.screens.encounterscreens.MainGameScreen;
import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.screens.widgets.outfitter.OutfitterObserver;
import com.mygdx.game.state.GameNode;
import com.mygdx.game.state.GameState;
import org.checkerframework.checker.units.qual.A;

import java.util.Set;

public class NextEncounter extends GameNode implements NextEncounterSubject{
    Array<NextEncounterObserver> observers;

    public NextEncounter(){
        super(NodeType.PATH_SELECTION, "Choose the next encounter");
        this.observers = new Array<>();
        observers.add(MainGameScreen.getInstance());
        this.add(getNextNodeTable());
        this.pack();
    }

    private Table getNextNodeTable(){
        Table table = new Table();
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
                    NextEncounter.this.notify(encounter.getNodeType(), NextEncounterObserver.NextEncounterEvent.NODE_SELECTED);
                }
            });
            table.add(button).pad(10);
        }
        return table;
    }


    @Override
    public void addObserver(NextEncounterObserver observer) {
        observers.add(observer);
    }



    @Override
    public void removeObserver(NextEncounterObserver observer) {
        observers.removeValue(observer, true);
    }



    @Override
    public void removeAllObservers() {
        for(NextEncounterObserver observer : observers){
            observers.removeValue(observer,true);
        }
    }



    @Override
    public void notify(NodeType nodeType, NextEncounterObserver.NextEncounterEvent event) {
        System.out.println("The outfitter window is notifying its observers..");
        for(NextEncounterObserver observer : observers){
            observer.onNotify(nodeType, event);
        }
    }
}
