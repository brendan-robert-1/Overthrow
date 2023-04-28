package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.ScreenAdapter;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenuScreen extends ScreenAdapter {
    private Stage stage;
    private Viewport viewport;
    private AssetManager assetManager;
    private Skin skin;

    public MainMenuScreen(AssetManager assetManager){
        this.assetManager = assetManager;
        skin = assetManager.get(Assets.SKIN);

    }

    @Override
    public void show() {
        viewport = new ExtendViewport(1280, 720);
        stage = new Stage(viewport);
        Table table = new Table();
        stage.addActor(table);
        table.setFillParent(true); //Table is maxium size of stage
        addMainMenuButtons(table);
        Gdx.input.setInputProcessor(stage); //stage being used for button listening
    }



    private void addMainMenuButtons(Table table) {
        addButton(table, "Overthrow");
        addButton(table, "New Run", new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("New run has been clicked... Good Luck!");
                ((Game) Gdx.app.getApplicationListener()).setScreen(new NewRunScreen(assetManager));
            }
        });
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
                ((Game) Gdx.app.getApplicationListener()).setScreen(new OptionsScreen(assetManager));
            }
        });
        addButton(table, "Match History",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Match History has been clicked.");
            }
        });
        addButton(table, "Quit",new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Quit has been clicked. Thanks for playing!");
                Gdx.app.exit();
            }
        });
    }



    private void addButton(Table table, String name, ClickListener listener){
        TextButton button = new TextButton(name, skin);
        button.addListener(listener);
        table.add(button).fillX().padBottom(10);
        table.row();
    }

    private void addButton(Table table, String name){
        TextButton button = new TextButton(name, skin);
        button.setTouchable(Touchable.disabled);
        table.add(button).fillX().padBottom(10);
        table.row();
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(.1f,.1f, .15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height){
        viewport.update(width, height);
    }
}
