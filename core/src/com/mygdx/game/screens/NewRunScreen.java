package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;
import com.mygdx.game.render.AnimatedActor;
import com.mygdx.game.screens.widgets.CharacterSprite;
import com.mygdx.game.screens.widgets.PixelProTextButton;
import com.mygdx.game.screens.widgets.ProceedButton;
import com.mygdx.game.sfx.ScreenTransitionAction;
import com.mygdx.game.sfx.ScreenTransitionActor;
import com.mygdx.game.state.Character.CharacterType;
import com.mygdx.game.state.NewGameGenerator;
import org.checkerframework.checker.units.qual.A;

import java.util.stream.Stream;

import static com.mygdx.game.Assets.MASTER_VOLUME;

public class NewRunScreen extends ScreenAdapter {
    private Stage stage;
    private Viewport viewport;
    CharacterSprite splash;
    private CharacterType characterTypeSelected;
    private ScreenTransitionActor transitionActor;


    @Override
    public void show() {

        stage = new Stage();
        viewport = new ScreenViewport();
        transitionActor = new ScreenTransitionActor();
        transitionActor.setVisible(false);
        stage.addActor(transitionActor);
        TextureAtlas atlas = Assets.getAssetManager().get("overthrow.atlas", TextureAtlas.class);
        TextureRegionDrawable trd = new TextureRegionDrawable(atlas.findRegion("messing-around2"));
        Table table = new Table();
        table.setBackground(trd);
        table.bottom().left();
        table.padLeft(70);
        table.padBottom(70);
        stage.addActor(table);
        table.setFillParent(true);
        populateCharacterList(table);
        Gdx.input.setInputProcessor(stage);
    }

    private void populateCharacterList(Table characters){
        Label label = new Label("Select a starting character", Assets.skin(), "title");
        characters.add(label).padBottom(20);
        characters.row();
        characters.row();
        addButton(characters, "Plague Doctor",characterButtonClickListener(CharacterType.PLAGUE_DOCTOR));
        addButton(characters, "Knight",characterButtonClickListener(CharacterType.KNIGHT));
        addButton(characters, "Inventor",characterButtonClickListener(CharacterType.INVENTOR));
        addButton(characters, "Leper",characterButtonClickListener(CharacterType.LEPER));
        addBackToMainMenu(characters);
    }

    private ClickListener characterButtonClickListener(CharacterType characterType){
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                final Sound sound1 = Assets.getInstance().getSoundAsset("select-option.mp3");
                sound1.play(MASTER_VOLUME);
                if(splash != null){

                    splash.remove();
                }
                System.out.println(characterType.toString() + " has been selected.");
                //splash = new CharacterSprite(characterType);
                //splash.scaleBy(15);
                characterTypeSelected = characterType;
                TextureAtlas atlas = Assets.getAssetManager().get("overthrow.atlas", TextureAtlas.class);
                Animation<TextureRegion> idleKnight = new Animation<TextureRegion>(0.1f, atlas.findRegions("KNIGHT"), Animation.PlayMode.LOOP);
                AnimatedActor animatedActor = new AnimatedActor(idleKnight);
                animatedActor.setScaling(Scaling.fit);
                animatedActor.scaleBy(7);
                animatedActor.setPosition(Gdx.graphics.getWidth()/2 - animatedActor.getWidth()/2, 0);
                stage.addActor(animatedActor);
                stage.addActor(proceedTable());
            }
        };
    }

    private Table proceedTable(){
        Table proceedTable = new Table();
        proceedTable.bottom().right();
        proceedTable.padRight(70);
        proceedTable.padBottom(70);
        proceedTable.setFillParent(true);
        ProceedButton button = new ProceedButton();
        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                final Sound sound1 = Assets.getInstance().getSoundAsset("select-option.mp3");
                sound1.play(MASTER_VOLUME);
                addTransitionToScreen();
                NewGameGenerator.generateNewGame(characterTypeSelected);
                ((Game) Gdx.app.getApplicationListener()).setScreen(
                        new GameStateScreen( )
                );
            }
        });
        button.getLabel().setAlignment(Align.center);
        proceedTable.add(button);
        return proceedTable;
    }

    private void addBackToMainMenu(Table table){
        PixelProTextButton button = new PixelProTextButton("Back to main menu...", Assets.skin());
        button.getLabel().setAlignment(Align.left);
        button.addListener(
                new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        System.out.println("Back to main menu.");
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
                    }
                }
        );
        table.add(button).fillX().padBottom(10).padTop(20);
        table.row();
    }

    private void addButton(Table table, String name, ClickListener... listener){
        PixelProTextButton button = new PixelProTextButton(name, Assets.skin());
        button.getLabel().setAlignment(Align.left);
        Stream.of(listener).forEach(button::addListener);
        table.add(button).fillX().padBottom(10);
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

    public void addTransitionToScreen(){
        transitionActor.setVisible(true);
        stage.addAction(
                Actions.sequence(
                        Actions.addAction(ScreenTransitionAction.transition(ScreenTransitionAction.ScreenTransitionType.FADE_OUT, 2), transitionActor)));
    }
}
