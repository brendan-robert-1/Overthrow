package com.mygdx.game.screens.widgets.fight;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.Assets;
import com.mygdx.game.encounters.OverthrowActs;
import com.mygdx.game.screens.encounterscreens.MainGameScreen;
import com.mygdx.game.screens.widgets.CharacterSprite;
import com.mygdx.game.screens.widgets.InspectBox;
import com.mygdx.game.screens.widgets.nextencounter.PathSelectedObserver;
import com.mygdx.game.state.Character;
import com.mygdx.game.state.EnemySlots;
import com.mygdx.game.state.GameNode;

import java.util.List;

public abstract class FightNode extends GameNode implements FightSubject, PathSelectedObserver {
    Array<FightObserver> observers;
    private EnemyTeam enemyTeam;


    public FightNode(NodeType nodeType, String displayName) {
        super(nodeType, displayName);
        init();
    }
    public FightNode() {
        super(NodeType.BASIC_FIGHT, "");
        init();
    }

    private void init(){
        observers = new Array<>();
        observers.add(MainGameScreen.getInstance());
        enemyTeam = new EnemyTeam(startingUnits());
        this.add(getFightTable(enemyTeam));
        this.pack();
    }

    public void update(){
        this.clearChildren();
        enemyTeam.update();
        this.add(getFightTable(enemyTeam));
    }



    public abstract List<OverthrowActs.ActType> actTypeEncounterable();
    public abstract EnemySlots startingUnits();



    private Table getFightTable(EnemyTeam enemyTeam) {
        Table entireFightTable = new Table();
        entireFightTable.setFillParent(true);
        entireFightTable.add(enemyTeam).expand().bottom().right();
        return entireFightTable;
    }


    public EnemyTeam getEnemyTeam(){
        return enemyTeam;
    }

    @Override
    public void addObserver(FightObserver observer) {
        observers.add(observer);
    }



    @Override
    public void removeObserver(FightObserver observer) {
        observers.removeValue(observer, true);
    }



    @Override
    public void removeAllObservers() {
        for(FightObserver observer : observers){
            observers.removeValue(observer,true);
        }
    }



    @Override
    public void notify(String eventd, FightObserver.FightEvent event) {
        for(FightObserver observer : observers){
            observer.onNotify(eventd, event);
        }
    }

    @Override
    public void onNotify(PathSelectedEvent event) {
        notify("Fight started", FightObserver.FightEvent.FIGHT_STARTED);
    }
}
