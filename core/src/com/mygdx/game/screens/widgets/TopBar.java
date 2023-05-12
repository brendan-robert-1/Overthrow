package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.InGameOptionsScreen;
import com.mygdx.game.screens.widgets.inventory.InventoryUi;
import com.mygdx.game.state.GameState;

import static com.mygdx.game.Assets.MASTER_VOLUME;

public class TopBar extends Table {
    private static TopBar instance;

    public static TopBar getInstance(){
        if(instance == null){
            instance = new TopBar();
        }
        return instance;
    }

    private TopBar(){
       update();
    }

    public void update(){
        this.clearChildren();
        Window window = new Window("", Assets.skin(), "top-bar");
        PixelProTextButton coins = new PixelProTextButton("Coins: " + GameState.getInstance().getCoin(), Assets.skin(), "top-bar");
        PixelProTextButton inventory = new PixelProTextButton("Inventory", Assets.skin(), "top-bar");
        inventory.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                final Sound sound1 = Assets.getInstance().getSoundAsset("select-option.mp3");
                sound1.play(MASTER_VOLUME);
                showInventory();
            }
        });
        this.addListener(inventoryKeyboardListener());
        inventory.padTop(30);
        PixelProTextButton options = new PixelProTextButton("Options", Assets.skin(), "top-bar");
        options.padTop(30);
        coins.padTop(30);
        Table topLeft = new Table();
        topLeft.align(Align.left);
        topLeft.add(options);
        options.addListener(optionClickListener());
        Table topRight = new Table();
        topRight.align(Align.right);

        topRight.add(inventory);
        topRight.add(coins).spaceLeft(5);
        window.add(topLeft).left().expand().growX();
        window.add(topRight).right().expand().growX();
        this.add(window).growX();
    }



    private InputListener inventoryKeyboardListener() {
        return new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode == Input.Keys.I){
                    showInventory();
                }
                return super.keyDown(event, keycode);
            }
        };
    }



    private void showInventory(){
        InventoryUi inventoryUi = InventoryUi.getInstance();
        if(inventoryUi.isVisible()){
            inventoryUi.setZIndex(getStage().getActors().size);
            inventoryUi.setVisible(false);
        }else{
            inventoryUi.setZIndex(getStage().getActors().size);
            inventoryUi.setVisible(true);
            inventoryUi.pack();
        }
    }

    public ClickListener optionClickListener(){
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                openOptions();
            }
        };
    }
    private void openOptions(){
        ((Game) Gdx.app.getApplicationListener()).setScreen(new InGameOptionsScreen());
    }

}
