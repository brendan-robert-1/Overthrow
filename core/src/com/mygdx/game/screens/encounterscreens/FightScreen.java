package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.screens.encounterscreens.combat.CombatProcessor;
import com.mygdx.game.state.EnemySlots;
import com.mygdx.game.state.GameState;

public class FightScreen extends ScreenAdapter {
    private GameState gameState = GameState.getInstance();
    private CombatProcessor combatProcessor;
    private EnemySlots enemySlots;
    private Stage stage;
    private Viewport viewport;

    @Override
    public void show() {
//        stage = new Stage();
//        viewport = new ScreenViewport();
//        Table entireScreen = new EntireInGameScreenTable();
//        InventoryUi inventoryUi = new InventoryUi();
//        entireScreen.add(new TopBar(inventoryUi)).expand().fillX().colspan(2).top();
//        entireScreen.row();
//        entireScreen.add(new Team()).expand().bottom().left().pad(40);
//        Table enemyTeam = enemyTeam();
//        entireScreen.add(enemyTeam).expand().bottom().right();
//        stage.addActor(entireScreen);
//        stage.addActor(inventoryUi);
//        for(Actor actor : inventoryUi.getInventoryActors()){
//            stage.addActor(actor);
//        }
//        Gdx.input.setInputProcessor(stage);
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
