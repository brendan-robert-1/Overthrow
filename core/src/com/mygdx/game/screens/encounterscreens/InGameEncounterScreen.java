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
import com.mygdx.game.screens.widgets.InspectBox;
import com.mygdx.game.screens.widgets.InventoryUi;
import com.mygdx.game.screens.widgets.PixelProTextButton;
import com.mygdx.game.state.Character;
import com.mygdx.game.state.GameState;

public abstract class InGameEncounterScreen extends OverthrowScreenAdapter {

    private GameState gameState = GameState.getInstance();
    private Table entireScreen;
    private Table team;
    private Table encounter;
    private InventoryUi inventoryUi;
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

        inventoryUi = new InventoryUi();
        inventoryUi.setMovable(false);
        inventoryUi.setVisible(false);
        inventoryUi.setPosition(Gdx.graphics.getWidth()/2 - inventoryUi.getWidth()/2, Gdx.graphics.getHeight()/2 - inventoryUi.getHeight()/2);

        encounter = new Table();
        encounter.setFillParent(true);
        populateTopBar(entireScreen);
        populateTeam(entireScreen);

        Image image = new Image();
        image.setDrawable(Assets.skin().getDrawable("health-pot"));
        Image image2 = new Image();
        image2.setDrawable(Assets.skin().getDrawable("hide-shield"));
        Table spriteTest = new Table();
        spriteTest.add(image).grow();
        spriteTest.add(image2).grow();
        spriteTest.setWidth(208);
        spriteTest.setHeight(104);
        spriteTest.setPosition(Gdx.graphics.getWidth()/2 - spriteTest.getWidth()/2, Gdx.graphics.getHeight()/2 - spriteTest.getHeight()/2);
        spriteTest.setDebug(true);
//        stage.addActor(spriteTest);
        stage.addActor(entireScreen);
        stage.addActor(encounter);
        stage.addActor(inventoryUi);
        Gdx.input.setInputProcessor(stage);
    }

    private void populateTopBar(Table entireScreen){
        Window window = new Window("",Assets.skin(), "top-bar");
        coins = new PixelProTextButton("Coins: " + gameState.getCoin(), Assets.skin(), "top-bar");
        coins.setDisabled(true);
        PixelProTextButton inventory = new PixelProTextButton("Inventory", Assets.skin(), "top-bar");
        inventory.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                inventoryUi.setVisible(!inventoryUi.isVisible());
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
        characterPanel.add(new Label(character.getName() + "        hp: " + character.getHp(), Assets.skin(), "title")).expandX();
        characterPanel.row();
        characterPanel.add(new Image(Assets.skin().getRegion(imageFrom(character.getCharacterType())))).expand();
        InspectBox characterInspectBox = new InspectBox("character stats", "character stats");
        Table listOfStats = new Table();
        Label hp = new Label("hp: " + character.getHp(), Assets.skin());
        Label armor = new Label("armor: " + character.getStats().getArmor(), Assets.skin());
        Label mr = new Label("magic resistance: " + character.getStats().getMagicResistance(), Assets.skin());
        Label pd = new Label("physical damage: " + character.getStats().getPhysicalDamage(), Assets.skin());
        Label md = new Label("magic damage: " + character.getStats().getMagicalDamage(), Assets.skin());
        listOfStats.add(hp).pad(7); listOfStats.row();
        listOfStats.add(armor).pad(7); listOfStats.row();
        listOfStats.add(mr).pad(7); listOfStats.row();
        listOfStats.add(pd).pad(7); listOfStats.row();
        listOfStats.add(md).pad(7); listOfStats.row();
        characterInspectBox.add(listOfStats);
        characterPanel.addListener(new RightClickInspectListener(stage, characterInspectBox));
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
        abilityPanels.add(abilityPanel).pad(7);
        abilityPanels.row();
        abilityPanels.add(weaponAbilityPanel).pad(7);
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
                    inventoryUi.setVisible(!inventoryUi.isVisible());
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
