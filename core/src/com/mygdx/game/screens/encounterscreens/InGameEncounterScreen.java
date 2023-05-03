package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Assets;
import com.mygdx.game.OverthrowScreenAdapter;
import com.mygdx.game.screens.*;
import com.mygdx.game.screens.widgets.AbilityButton;
import com.mygdx.game.screens.widgets.HoverBox;
import com.mygdx.game.screens.widgets.InventoryTable;
import com.mygdx.game.screens.widgets.PixelProTextButton;
import com.mygdx.game.encounters.state.Character;
import com.mygdx.game.encounters.state.GameState;

public abstract class InGameEncounterScreen extends OverthrowScreenAdapter {

    private GameState gameState = GameState.getInstance();
    private Table entireScreen;
    private Table team;
    private Table encounter;
    private InventoryTable inventoryTable;
    private PixelProTextButton coins;
    private InputListener escapeKeyboardListener;

    public InGameEncounterScreen(){
        renderScreen();
    }

    public void renderScreen(){
        escapeKeyboardListener = escapeKeyboardListener();
        stage.addListener(escapeKeyboardListener);
        stage.addListener(inventoryKeyboardListener());
        TextureRegionDrawable trd = new TextureRegionDrawable(Assets.skin().getRegion("farms"));
        entireScreen = new Table(Assets.skin());
        entireScreen.setBackground(trd);
        entireScreen.setFillParent(true);
        inventoryTable = new InventoryTable();
        encounter = new Table();
        inventoryTable.setFillParent(true);
        encounter.setFillParent(true);
        populateTopBar(entireScreen);
        populateTeam(entireScreen);
        stage.addActor(entireScreen);
        stage.addActor(encounter);
        stage.addActor(inventoryTable);
        Gdx.input.setInputProcessor(stage);
    }

    private void populateTopBar(Table entireScreen){
        Window window = new Window("",Assets.skin(), "top-bar");
        coins = new PixelProTextButton("Coins: " + gameState.getCoin(), Assets.skin(), "top-bar");
        PixelProTextButton inventory = new PixelProTextButton("Inventory", Assets.skin(), "top-bar");
        inventory.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                inventoryTable.setVisible(!inventoryTable.isVisible());
            }
        });
        inventory.padTop(30);
        PixelProTextButton options = new PixelProTextButton("Options", Assets.skin(), "top-bar");
        options.addListener(optionClickListener());
        options.padTop(30);
        coins.padTop(30);
        Table topLeft = new Table();
        topLeft.align(Align.left);
        topLeft.add(options);


        Table topRight = new Table();
        topRight.align(Align.right);

        topRight.add(inventory);
        topRight.add(coins).spaceLeft(5);
        window.add(topLeft).left().expand().growX();
        window.add(topRight).right().expand().growX();
        entireScreen.add(window).growX();
        entireScreen.row();
    }
    private void populateTeam(Table entireScreen) {
        team = new Table();
        Table team = new Table();
        team.add(characterPanel(gameState.getCharacterSlots().firstCharacter())).expand().fill();
        team.add(characterPanel(gameState.getCharacterSlots().secondCharacter())).expand().fill();
        team.add(characterPanel(gameState.getCharacterSlots().thirdCharacter())).expand().fill();
        team.add(characterPanel(gameState.getCharacterSlots().fourthCharacter())).expand().fill();
        team.pack();
        this.team.add(team).expand().bottom().left().padBottom(40);
        entireScreen.add(this.team).expand().fill();
        entireScreen.row();
    }

    public void populateEncounter(Table toPopulate){
        encounter.add(toPopulate).expand().bottom().right().padBottom(100).padRight(100);
        System.out.println("replaced encounter");
    }

    private ClickListener optionClickListener(){
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                openOptions();
            }
        };
    }

    private Table characterPanel(Character character) {
        if(character == null){
            return emptyCharacterPanel();
        }
        Table characterPanel = new Table(Assets.skin());
        characterPanel.add(new Label(character.name() + "        hp: " + character.hp(), Assets.skin(), "title")).expandX();
        characterPanel.row();
        characterPanel.add(new Image(Assets.skin().getRegion(imageFrom(character.characterType())))).expand();
        characterPanel.addListener(new HoverClickListener(stage, new HoverBox()));
        characterPanel.row();

        Table abilityPanels = new Table();
        abilityPanels.setBackground(Assets.skin().getDrawable("button-over"));
        Table abilityPanel = new Table();
        abilityPanel.defaults().space(10F);
        abilityPanel.add(new AbilityButton("AB1")).space(10);
        abilityPanel.add(new AbilityButton("AB2")).space(10);
        abilityPanel.add(new AbilityButton("ULT")).space(10);
        Table weaponAbilityPanel = new Table();
        weaponAbilityPanel.defaults().space(10F);
        weaponAbilityPanel.add(new AbilityButton("W1-AB")).space(10);
        weaponAbilityPanel.add(new AbilityButton("W2-AB")).space(10);
        abilityPanels.add(abilityPanel);
        abilityPanels.row();
        abilityPanels.add(weaponAbilityPanel);
        characterPanel.add(abilityPanels);
        characterPanel.defaults().expandX();
        return characterPanel;
    }

    private String imageFrom(Character.CharacterType characterType){
        switch(characterType){
            case PLAGUE_DOCTOR -> { return "plauge_doctor";}
            case LEPER -> { return "plauge_doctor";}
            case INVENTOR -> { return "plauge_doctor";}
            case KNIGHT -> { return "knight";}
            default -> throw new IllegalStateException("Unexpected value: " + characterType);
        }
    }

    private Table emptyCharacterPanel(){
        Table empty = new Table(Assets.skin());
        empty.defaults().expand().fill();
        empty.add(new Label("Empty slot.....", Assets.skin()));
        return empty;
    }


    private InputListener escapeKeyboardListener (){
        return new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode == Input.Keys.ESCAPE){
                    openOptions();
                }
                return super.keyDown(event, Input.Keys.ESCAPE);
            }
        };
    }

    private InputListener inventoryKeyboardListener(){
        return new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode == Input.Keys.I){
                    inventoryTable.setVisible(!inventoryTable.isVisible());
                }
                return super.keyDown(event, Input.Keys.I);
            }
        };
    }

    public void updateCoins(){
        coins.setText("Coins: " + gameState.getCoin());
    }



    private void openOptions(){
        ((Game) Gdx.app.getApplicationListener()).setScreen(new InGameOptionsScreen());
    }



    protected GameState getGameState() {
        return gameState;
    }

    protected void redirectNextNode(){
        gameState.setCurrentFloor(gameState.getCurrentFloor() + 1);
        ((Game) Gdx.app.getApplicationListener()).setScreen(new NextEncounterSelectionScreen());
    }
}
