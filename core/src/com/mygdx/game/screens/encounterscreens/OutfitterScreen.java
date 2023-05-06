package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;
import com.mygdx.game.encounters.Outfitter;
import com.mygdx.game.screens.widgets.*;
import com.mygdx.game.screens.HoverClickListener;
import com.mygdx.game.state.GameState;
import com.mygdx.game.state.items.ItemSlot;
import org.checkerframework.checker.units.qual.C;

import java.util.List;

public class OutfitterScreen extends ScreenAdapter {
    private GameState gameState = GameState.getInstance();
    private Stage stage;
    private Viewport viewport;
    @Override
    public void show() {
        stage = new Stage();
        viewport = new ScreenViewport();
        Table entireScreen = new EntireInGameScreenTable();
        Outfitter outfitter = (Outfitter) gameState.getCurrentNode();
        OutfitterTable outfitterTable = new OutfitterTable(outfitter, stage);
        InventoryUi inventoryUi = new InventoryUi();
        entireScreen.add(new TopBar(inventoryUi)).expand().fillX().colspan(2).top();
        entireScreen.row();
        entireScreen.add(new Team()).expand().bottom().left().pad(40);
        entireScreen.add(outfitterTable).expand().bottom().right().padBottom(20);
        stage.addActor(entireScreen);
        stage.addActor(inventoryUi);
        Gdx.input.setInputProcessor(stage);
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