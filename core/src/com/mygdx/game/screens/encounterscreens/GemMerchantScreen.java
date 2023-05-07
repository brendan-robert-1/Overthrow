package com.mygdx.game.screens.encounterscreens;

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
import com.mygdx.game.screens.widgets.EntireInGameScreenTable;
import com.mygdx.game.state.GameState;

import javax.swing.text.View;

public class GemMerchantScreen extends ScreenAdapter {
    private GameState gameState = GameState.getInstance();
    private Stage stage;
    private Viewport viewport;
    @Override
    public void show() {
        stage = new Stage();
        viewport = new ScreenViewport();
        Table entireScreen = new EntireInGameScreenTable();

        Table market = market();
        entireScreen.add(market);

        stage.addActor(entireScreen);
        Gdx.input.setInputProcessor(stage);
    }

    private Table market(){
        Table market = new Table();
        Label label = new Label("Market", Assets.skin());
        market.add(label);
        market.row();
        TextButton healthPot = new TextButton("Poison Resist Opal", Assets.skin());
        market.add(healthPot).fillX();
        market.row();
        TextButton hideShield = new TextButton("Poison Damage Sapphire", Assets.skin());
        market.add(hideShield).fillX();;
        market.row();
        TextButton bluntDagger = new TextButton("Physical Damage Amber", Assets.skin());
        market.add(bluntDagger).fillX();;
        market.row();
        TextButton proceed = new TextButton("Next Encounter ->", Assets.skin());
        proceed.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Proceeding to next encounter...");
            }
        });
        market.add(proceed).padTop(25);
        market.row();
        return market;
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