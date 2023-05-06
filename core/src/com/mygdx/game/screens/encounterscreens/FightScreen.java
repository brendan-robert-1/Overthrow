package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.CharacterSpriteFetcher;
import com.mygdx.game.screens.encounterscreens.combat.CombatProcessor;
import com.mygdx.game.encounters.fights.Fight;
import com.mygdx.game.screens.widgets.*;
import com.mygdx.game.state.EnemySlots;
import com.mygdx.game.state.GameState;
import com.mygdx.game.state.Character;

import javax.swing.text.View;

public class FightScreen extends ScreenAdapter {
    private GameState gameState = GameState.getInstance();
    private CombatProcessor combatProcessor;
    private EnemySlots enemySlots;
    private Stage stage;
    private Viewport viewport;

    @Override
    public void show() {
        stage = new Stage();
        viewport = new ScreenViewport();
        Table entireScreen = new EntireInGameScreenTable();
        InventoryUi inventoryUi = new InventoryUi();
        entireScreen.add(new TopBar(inventoryUi)).expand().fillX().colspan(2).top();
        entireScreen.row();
        entireScreen.add(new Team()).expand().bottom().left().pad(40);
        Table enemyTeam = enemyTeam();
        entireScreen.add(enemyTeam).expand().bottom().right();
        stage.addActor(entireScreen);
        stage.addActor(inventoryUi);
        Gdx.input.setInputProcessor(stage);
    }

    /*
      stage = new Stage();
        viewport = new ScreenViewport();
        Table entireScreen = new Table();
        entireScreen.setFillParent(true);

        Fight fight = (Fight) gameState.getCurrentNode();
        enemySlots = fight.startingUnits();
        combatProcessor = new CombatProcessor(fight);
        Character activeCharacter = combatProcessor.calculateActiveTurn();
        if(enemySlots.contains(activeCharacter)){
            System.out.println("Taking enemy turn... " + activeCharacter.getName());
            combatProcessor.executeEnemyTurn(activeCharacter);
        }
        entireScreen.add(enemyTeam()).expand().right().bottom().padBottom(150);
        entireScreen.add(new AbilitiesPanel(combatProcessor.getNextActiveFriendly()));
        stage.addActor(entireScreen);
        combatProcessor.resetChargeTime();
     */


    private Table enemyTeam(){
        Table table = new Table(Assets.skin());
        Fight fightNode = (Fight) gameState.getCurrentNode();
        Character firstEnemy = fightNode.startingUnits().firstCharacter();
        Character secondEnemy = fightNode.startingUnits().secondCharacter();
        Character thirdEnemy = fightNode.startingUnits().thirdCharacter();
        Character fourthEnemy = fightNode.startingUnits().fourthCharacter();
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
        characterPanel.add(new Label("hp: " + character.getHp(), Assets.skin(), "title")).expandX();
        characterPanel.row();
        characterPanel.add(new Image(Assets.skin().getRegion("enemy-placeholder-medium"))).expand();
        InspectBox characterInspectBox = new InspectBox(character.getName(),
                "hp: " + character.getHp() + "\n" +
                        "armor: " + character.getHp() + "\n"+
                        "magic resistance: " + character.getHp() + "\n"+
                        "physical damage: " + character.getHp() + "\n"+
                        "magic damage: " + character.getHp() + "\n"+
                        "speed: " + character.getHp() + "\n"
        );
        characterPanel.addListener(new RightClickInspectListener(stage, characterInspectBox));
        characterPanel.row();

        characterPanel.defaults().expandX();
        return characterPanel;
    }

    private Table emptyEnemyPanel() {
        Table empty = new Table(Assets.skin());
        empty.defaults().expand().fill();
        empty.add(new Label("    ", Assets.skin()));
        return empty;
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
