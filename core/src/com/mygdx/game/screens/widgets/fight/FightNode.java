package com.mygdx.game.screens.widgets.fight;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.encounters.OverthrowActs;
import com.mygdx.game.screens.encounterscreens.MainGameScreen;
import com.mygdx.game.screens.widgets.nextencounter.PathSelectedObserver;
import com.mygdx.game.encounters.GameNode;

import java.util.List;

public abstract class FightNode extends GameNode implements PathSelectedObserver {
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
        enemyTeam = startingUnits();
        this.add(getFightTable(enemyTeam));
        this.pack();
    }

    public void update(){
        resolveDeaths();
        enemyTeam.pack();
        this.pack();
    }

    public void resolveDeaths() {
        enemyTeam.streamNonNull().forEach(enemyPanel -> {
            if(enemyPanel.getHp() <= 0){
                enemyTeam.removeDead(enemyPanel);
            }
        });
    }

    public abstract List<OverthrowActs.ActType> actTypeEncounterable();
    public abstract EnemyTeam startingUnits();


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
    public void onNotify(PathSelectedEvent event) {

    }

}
