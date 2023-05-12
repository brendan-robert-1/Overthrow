package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.widgets.PixelProTextButton;

import static com.mygdx.game.Assets.MASTER_VOLUME;

public class MainMenuScreen extends ScreenAdapter {

    private Stage stage;
    private Viewport viewport;

    @Override
    public void show() {
        stage = new Stage();
        viewport = new ScreenViewport();
        Table table = new Table();
        TextureAtlas atlas = Assets.getAssetManager().get("overthrow.atlas", TextureAtlas.class);
        TextureRegion trd = new TextureRegion(atlas.findRegion("messing-around"));
        table.setBackground(new TextureRegionDrawable(trd));
        stage.addActor(table);
        table.setFillParent(true);
        addMainMenuButtons(table);
        Gdx.input.setInputProcessor(stage);
    }

    private void addMainMenuButtons(Table table) {
        addButton(table, "New Run", new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                final Sound sound1 = Assets.getInstance().getSoundAsset("select-option.mp3");
                sound1.play(MASTER_VOLUME);
                System.out.println("New run has been clicked... Good Luck!");
                ((Game) Gdx.app.getApplicationListener()).setScreen(new NewRunScreen());
            }
        },40);

        addButton(table, "Unlocks",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Unlocks has been clicked.");
            }
        });
        addButton(table, "Options",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Options has been clicked.");
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuOptionsScreen());
            }
        });
        addButton(table, "Match History",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Match History has been clicked.");
            }
        });
        addButton(table, "Quit to desktop",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Quit has been clicked. Thanks for playing!");
                Gdx.app.exit();
            }
        });
    }

    private void addButton(Table table, String name, ClickListener listener){
        PixelProTextButton button = new PixelProTextButton(name, Assets.skin());
        button.getLabel().setAlignment(Align.left);
        button.addListener(listener);
        table.add(button).fillX().padBottom(10);
        table.row();
    }

    private void addButton(Table table, String name, ClickListener listener, float pad){
        PixelProTextButton button = new PixelProTextButton(name, Assets.skin());
        button.getLabel().setAlignment(Align.left);
        button.addListener(listener);
        table.add(button).fillX().padBottom(pad);
        table.row();
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
