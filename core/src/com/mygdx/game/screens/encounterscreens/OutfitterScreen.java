package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Assets;
import com.mygdx.game.encounters.Outfitter;
import com.mygdx.game.screens.HoverClickListener;
import com.mygdx.game.screens.widgets.*;
import com.mygdx.game.state.GameState;
import com.mygdx.game.state.items.InventoryItemFactory;

import java.util.List;

public class OutfitterScreen extends ScreenAdapter {
    private GameState gameState = GameState.getInstance();
    private InventoryUi inventoryUi;
    private Stage stage;
    private Viewport viewport;
    @Override
    public void show() {
        stage = new Stage();
        viewport = new ScreenViewport();
        Table entireScreen = new EntireInGameScreenTable();
        Outfitter outfitter = (Outfitter) gameState.getCurrentNode();
        Table outfitterTable = getOutfitterTable(outfitter);
        inventoryUi = new InventoryUi();
        inventoryUi.setKeepWithinStage(false);
        entireScreen.add(new TopBar(inventoryUi)).expand().fillX().colspan(2).top();
        entireScreen.row();
        entireScreen.add(new Team()).expand().bottom().left().pad(40);
        entireScreen.add(outfitterTable).expand().bottom().right().padBottom(20);
        stage.addActor(entireScreen);
        stage.addActor(inventoryUi);
        for(Actor actor : inventoryUi.getInventoryActors()){
            stage.addActor(actor);
        }
        stage.setDebugAll(true);
        Gdx.input.setInputProcessor(stage);
    }



    public Table getOutfitterTable(Outfitter outfitter){
        Table table = new Table();
        List<InventoryItem> outfitterItems = outfitter.buildOutfitterItems();
        Label label = new Label("Choose an item to start the run.", Assets.skin(), "title");
        table.add(label).colspan(outfitterItems.size());
        table.row();
        table.row();
        for(InventoryItem item : outfitterItems){
            table.add(getOptionTable(item)).space(20.0f).minSize(150.0f);
        }
        table.pack();
        return table;
    }

    private Table getOptionTable(InventoryItem inventoryItem){
        Table table1 = new Table();
        table1.setBackground(Assets.skin().getDrawable("button-up"));

        TextureAtlas atlas = Assets.getAssetManager().get("overthrow.atlas", TextureAtlas.class);
        TextureRegionDrawable trd = new TextureRegionDrawable(atlas.findRegion(inventoryItem.getItemTypeId().toString()));
        Image image = new Image(trd);
        image.setScaling(Scaling.fill);
        table1.addListener(new HoverClickListener(stage, new InspectBox(inventoryItem.getDisplayName(), inventoryItem.getItemShortDescription())));
        table1.addListener(clickOption(inventoryItem));
        table1.add(image).minSize(96).maxSize(96);
        return table1;
    }

    private ClickListener clickOption(InventoryItem item){
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                InventoryItem item = InventoryItemFactory.getInstance().of(InventoryItem.ItemTypeId.RUSTY_DAGGER);
                Array<InventoryItemLocation> inventoryItems = new Array<>();
                inventoryItems.add(new InventoryItemLocation(0, InventoryItem.ItemTypeId.RUSTY_DAGGER, 5, "defaultaroo"));
                InventoryUi.populateInventory(inventoryUi.getInventorySlotTable(), inventoryItems, inventoryUi.getDragAndDrop());
               // InGameEncounterScreen.redirectNextNode();
                super.clicked(event, x, y);
            }
        };
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