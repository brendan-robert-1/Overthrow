package com.mygdx.game.screens.widgets.nextencounter;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.encounterscreens.MainGameScreen;
import com.mygdx.game.encounters.GameNode;
import com.mygdx.game.state.GameState;

import java.util.Set;

import static com.mygdx.game.Assets.MASTER_VOLUME;

public class NextEncounter extends GameNode implements NextEncounterSubject, PathSelectedSubject{

    @Override
    public String ambientSounds() {
        return null;
    }
    @Override
    public String backgroundAsset() {
        return "farms-fire";
    }
    Array<NextEncounterObserver> observers;
    Array<PathSelectedObserver> pathSelectedObservers = new Array<>();

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
            TextButton button = new TextButton(nextEncounterDisplay(encounter.getNodeType()), Assets.skin());
            button.pad(25);

            button.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    System.out.println("Chose encounter: " + encounter.getNodeType());
                    GameState.getInstance().setCurrentNode(encounter);
                    final Sound sound1 = Assets.getInstance().getSoundAsset("select-option.mp3");
                    sound1.play(MASTER_VOLUME);
                    NextEncounter.this.notify(encounter.getNodeType(), NextEncounterObserver.NextEncounterEvent.NODE_SELECTED);
                }
            });
            table.add(button).pad(10);
        }
        return table;
    }
    
    private String nextEncounterDisplay(NodeType nodeType){
        switch(nodeType){
            case KEYMASTER -> {return "Keymaster";}
            case OUTFITTER -> {return "Outfitter";}
            case BASIC_FIGHT -> {return "Fight";}
            case ELITE_FIGHT -> {return "Elite Fight";}
            case BOSS_FIGHT -> {return "Boss Fight";}
            case MARKET -> {return "Merchant";}
            case ARMOR_MERCHANT -> {return "Merchant";}
            case WEAPON_MERCHANT -> {return "Merchant";}
            case BLACKSMITH -> {return "BLACKSMITH";}
            case WISHING_WELL -> {return "WISHING_WELL";}
            case SAUNA -> {return "SAUNA";}
            case ABILITY_TRAINER -> {return "ABILITY_TRAINER";}
            case GEM_MERCHANT -> {return "Merchant";}
            case QUESTION_MARK -> {return "QUESTION_MARK";}
            case CHEST -> {return "CHEST";}
            case PATH_SELECTION -> {return "";}
            case ABANDONED_FARMHOUSE -> {return "Farmhouse";}


            default -> throw new IllegalStateException("Unexpected value: " + nodeType);
        }
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
    public void addObserver(PathSelectedObserver observer) {
        pathSelectedObservers.add(observer);
    }



    @Override
    public void removeObserver(PathSelectedObserver observer) {
        pathSelectedObservers.removeValue(observer, true);
    }



    @Override
    public void removeAllObservers() {
        for(NextEncounterObserver observer : observers){
            observers.removeValue(observer,true);
        }
        for(PathSelectedObserver observer : pathSelectedObservers){
            pathSelectedObservers.removeValue(observer,true);
        }
    }



    @Override
    public void notify(PathSelectedObserver.PathSelectedEvent event) {
        for(PathSelectedObserver observer : pathSelectedObservers){
            observer.onNotify(event);
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
