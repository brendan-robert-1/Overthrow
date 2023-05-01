package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Assets;
import com.mygdx.game.OverthrowScreenAdapter;
import com.mygdx.game.screens.InGameOptionsScreen;
import com.mygdx.game.screens.NextEncounterSelectionScreen;
import com.mygdx.game.state.Character;
import com.mygdx.game.state.GameState;
import com.mygdx.game.state.items.ItemSlot;
import org.w3c.dom.Text;

import java.util.List;

public abstract class InGameEncounterScreen extends OverthrowScreenAdapter {

    private GameState gameState = GameState.getInstance();
    private Table entireScreen;
    private Table teamAndEncounter;
    private Table encounter;
    private Label coins;

    public InGameEncounterScreen(){
        renderScreen();
    }

    public void renderScreen(){
        stage.addListener(escapeKeyboardListener());
        entireScreen = new Table();
      //s entireScreen.setDebug(true);
        entireScreen.setFillParent(true);
        entireScreen.defaults().pad(10F);
        populateTopBar(entireScreen);
        populateTeamAndEncounter(entireScreen);
        populateGearAndInventory(entireScreen);
        stage.addActor(entireScreen);
    }

    private void populateTopBar(Table entireScreen){
        Table topBar = new Table();
        topBar.defaults().pad(10F);
        coins = new Label("Coins: " + gameState.getCoin(), Assets.skin());
        TextButton options = optionsButton();
        topBar.add(options).expandX().left();
        topBar.add(coins).expandX().right();
        entireScreen.add(topBar).growX();
        entireScreen.row();
    }
    private void populateTeamAndEncounter(Table entireScreen) {
        teamAndEncounter = new Table();
        Table team = new Table();
        team.add(characterPanel(gameState.getCharacterSlots().firstCharacter())).expand().fillX();
        team.add(characterPanel(gameState.getCharacterSlots().secondCharacter())).expand().fillX();
        team.add(characterPanel(gameState.getCharacterSlots().thirdCharacter())).expand().fillX();
        team.add(characterPanel(gameState.getCharacterSlots().fourthCharacter())).expand().fillX();
        team.pack();
        teamAndEncounter.add(team).expandX().fillX().bottom().padBottom(40);
        encounter = new Table();
        encounter.add( new Label("Choose an item to begin the run with", Assets.skin()));
        teamAndEncounter.add(encounter);
       // teamAndEncounter.setDebug(true);
        entireScreen.add(teamAndEncounter).expand().fill();
        entireScreen.row();
    }

    public void populateEncounter(Table toPopulate){
       teamAndEncounter.removeActor(encounter);
       teamAndEncounter.add(toPopulate).expand();
        System.out.println("replaced encounter");
    }

    private void populateGearAndInventory(Table entireScreen) {
        Table gearAndInventory = new Table();
        Table gear = new Table();
        gear.defaults();
        TextButton gearPanel =  new TextButton("Gear", Assets.skin());
        gear.add(gearPanel).expandX().fillX().height(120);
        Table inventory = new Table();
        TextButton inventoryPanel =  new TextButton("Inventory", Assets.skin());
        inventory.add(inventoryPanel).expandX().fillX().height(120);

        gearAndInventory.add(gear).expand().fill();
        gearAndInventory.add(inventory).expand().fill();
        entireScreen.add(gearAndInventory).fillX().expandX().left();
        entireScreen.row();
    }
    private TextButton optionsButton(){
        TextButton options=  new TextButton("Options", Assets.skin());
        options.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                openOptions();
            }
        });
        return options;
    }

    private Table characterPanel(Character character) {
        if(character == null){
            return emptyCharacterPanel();
        }
        Table characterPanel = new Table(Assets.skin());
        characterPanel.add(new Label(character.name() + "        hp: " + character.hp(), Assets.skin())).expandX();
        characterPanel.row();
        characterPanel.add(new Image(Assets.getAssetManager().get("pd.jpg", Texture.class))).expand();
        characterPanel.row();
        Table abilityPanel = new Table();
        abilityPanel.defaults().space(10F);
        abilityPanel.add(new TextButton("Gear", Assets.skin())).expandX();
        abilityPanel.add(new TextButton("AB1", Assets.skin())).expandX();
        abilityPanel.add(new TextButton("AB2", Assets.skin())).expandX();
        abilityPanel.add(new TextButton("ULT", Assets.skin())).expandX();
        characterPanel.add(abilityPanel).expand();
        Table weaponAbilityPanel = new Table();
        weaponAbilityPanel.defaults().space(10F);
        weaponAbilityPanel.add(new TextButton("W1-AB", Assets.skin())).expandX();
        weaponAbilityPanel.add(new TextButton("W2-AB", Assets.skin())).expandX();
        characterPanel.row();
        characterPanel.add(weaponAbilityPanel).expand();
     //  characterPanel.setDebug(true);
        characterPanel.defaults().expandX();
        return characterPanel;
    }

    private Table emptyCharacterPanel(){
        Table empty = new Table();
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
    public void updateCoins(){
        coins.setText("Coins: " + gameState.getCoin());
    }
    private void populateFloorCount(){
        Table table = new Table(Assets.skin());
        table.top();
        table.padTop(20);
        Label floorLabel = new Label("Floor: " + gameState.getCurrentFloor(), Assets.skin());
        table.add(floorLabel);
        table.setFillParent(true);
        stage.addActor(table);
    }


    private void displayInventoryTable(Table table){
        List<ItemSlot> inventory = gameState.getInventory().getInventoryList();
        Label label = new Label("Inventory", Assets.skin());
        label.setAlignment(Align.center);
        table.add(label).colspan(9).fillX();
        TextButton close = new TextButton("Close", Assets.skin());
        close.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                table.remove();
            }
        });
        table.add(close);
        table.row();
        int counter = 0;
        for(int i = 0 ; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(counter <= inventory.size() - 1){
                    Label item = new Label(inventory.get(counter).getName(), Assets.skin());
                    table.add(item).fillX();
                }else {
                  table.add(new Label("_______", Assets.skin()));
                }
                counter++;
            }
            table.row();
        }
        table.setFillParent(true);
     //   table.setDebug(true);
        stage.addActor(table);
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
