package com.mygdx.game.screens.widgets.fight;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.Assets;
import com.mygdx.game.encounters.OverthrowActs;
import com.mygdx.game.screens.encounterscreens.MainGameScreen;
import com.mygdx.game.screens.widgets.InspectBox;
import com.mygdx.game.screens.widgets.nextencounter.PathSelectedObserver;
import com.mygdx.game.state.Character;
import com.mygdx.game.state.EnemySlots;
import com.mygdx.game.state.GameNode;

import java.util.List;

public abstract class FightNode extends GameNode implements FightSubject, PathSelectedObserver {
    Array<FightObserver> observers;

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
        this.add(getFightTable());
        this.pack();
    }



    public abstract List<OverthrowActs.ActType> actTypeEncounterable();
    public abstract EnemySlots startingUnits();



    private Table getFightTable() {
        Table entireFightTable = new Table();
        entireFightTable.setFillParent(true);
        entireFightTable.add(enemyTeam()).expand().bottom().right();
        return entireFightTable;
    }


    private Table enemyTeam(){
        Table table = new Table(Assets.skin());
        Character firstEnemy = startingUnits().firstCharacter();
        Character secondEnemy = startingUnits().secondCharacter();
        Character thirdEnemy = startingUnits().thirdCharacter();
        Character fourthEnemy = startingUnits().fourthCharacter();
        table.add(addEnemyPanel(firstEnemy)).expand();
        table.add(addEnemyPanel( secondEnemy)).expand();
        table.add( addEnemyPanel( thirdEnemy)).expand();
        table.add(addEnemyPanel( fourthEnemy)).expand();
        table.pack();
        return table;
    }


    private Table addEnemyPanel(Character character) {
        if(character == null){
            return emptyEnemyPanel();
        }
        Table characterPanel = new Table(Assets.skin());
        characterPanel.add(new Label("hp: " + character.getHp(), Assets.skin(), "title")).expand().fill().pad(20).align(Align.center);

        characterPanel.row();
        TextureAtlas atlas = Assets.getAssetManager().get("overthrow.atlas", TextureAtlas.class);
        TextureRegionDrawable trd = new TextureRegionDrawable(atlas.findRegion("enemy-placeholder-medium"));
        Image enemySplash = new Image(trd);
        enemySplash.setScaling(Scaling.fit);
        enemySplash.setSize(200,250);
        characterPanel.add(enemySplash).expand();
        InspectBox characterInspectBox = new InspectBox(character.getName(),
                "hp: " + character.getHp() + "\n" +
                        "armor: " + character.getHp() + "\n"+
                        "magic resistance: " + character.getHp() + "\n"+
                        "physical damage: " + character.getHp() + "\n"+
                        "magic damage: " + character.getHp() + "\n"+
                        "speed: " + character.getHp() + "\n"
        );
       // characterPanel.addListener(new RightClickInspectListener(stage, characterInspectBox));
        characterPanel.row();

        characterPanel.defaults().expand().fill();
        return characterPanel;
    }

    private Table emptyEnemyPanel() {
        Table empty = new Table(Assets.skin());
        empty.defaults().expand().fill();
        empty.add(new Label("    ", Assets.skin()));
        return empty;
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
    public void onNotify(PathSelectedEvent event){
        notify("Fight started", FightObserver.FightEvent.FIGHT_STARTED);
    }
}
