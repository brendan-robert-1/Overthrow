package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.CharacterSpriteFetcher;
import com.mygdx.game.state.Character;
import com.mygdx.game.state.CharacterSlots;
import com.mygdx.game.state.GameState;
import com.mygdx.game.screens.widgets.InventoryItem.ItemTypeId;
import com.mygdx.game.state.items.InventoryItemFactory;

public class InventoryUi extends Window {

    public static final int TOTAL_SLOTS = 50;
    private static final int ROW_LENGTH = 10;
    private static final int SLOT_WIDTH = 52;
    private static final int SLOT_HEIGHT = 52;
    public static final String PLAYER_INVENTORY = "Player_Inventory";
    private CharacterSlots characterSlots;
    private Table inventorySlotTable;
    private Table character1EquipSlots = new Table();
    private Table character2EquipSlots = new Table();
    private Table character3EquipSlots = new Table();
    private Table character4EquipSlots = new Table();
    private InventorySlotTooltip inventorySlotTooltip;
    private Array<Actor> inventoryActors;
    private DragAndDrop dragAndDrop;

    public InventoryUi(){
        super("Inventory", Assets.skin());
        characterSlots = GameState.getInstance().getCharacterSlots();
        dragAndDrop = new DragAndDrop();
        inventorySlotTable = new Table();
        inventoryActors = new Array<>();
        inventorySlotTooltip = new InventorySlotTooltip(Assets.skin());
        inventoryActors.add(inventorySlotTooltip);
        populate();
        this.add(inventorySlotTable).colspan(characterSlots.numberOfActiveCharacters()).pad(20);
        this.row();
        if(characterSlots.firstCharacter() != null){
            this.add(character1EquipSlots).padBottom(20).padLeft(20).padRight(20);
        }
        if(characterSlots.secondCharacter() != null){
            this.add(character2EquipSlots).padBottom(20).padLeft(20).padRight(20);
        }
        if(characterSlots.thirdCharacter() != null){
            this.add(character3EquipSlots).padBottom(20).padLeft(20).padRight(20);
        }
        if(characterSlots.fourthCharacter() != null){
            this.add(character4EquipSlots).padBottom(20).padLeft(20).padRight(20);
        }
        this.setMovable(false);
        this.setVisible(false);
        this.populateInventory();
        this.pack();
        this.setPosition(Gdx.graphics.getWidth()/2f - this.getWidth()/2f, Gdx.graphics.getHeight()/2f - this.getHeight()/2f);

    }

    private void populate(){
        buildInventoryPanel();

        buildGearPanels();
        populateGearPanels();
    }

    private void buildGearPanels(){
        CharacterSlots characterSlots = GameState.getInstance().getCharacterSlots();
        if(characterSlots.firstCharacter() != null){
            character1EquipSlots = buildGearPanel(characterSlots.firstCharacter());
        }
        if(characterSlots.secondCharacter() != null){
            character2EquipSlots = buildGearPanel(characterSlots.secondCharacter());
        }
        if(characterSlots.thirdCharacter() != null){
            character3EquipSlots = buildGearPanel(characterSlots.thirdCharacter());
        }
        if(characterSlots.fourthCharacter() != null){
            character4EquipSlots = buildGearPanel(characterSlots.fourthCharacter());
        }
    }

    private void populateGearPanels(){}

    private Table buildGearPanel(Character character){
        Table table = new Table();
        Image image = new Image(Assets.skin().getPatch("opaque-background"));
        TextureAtlas atlas = Assets.getAssetManager().get("overthrow.atlas", TextureAtlas.class);
        TextureRegionDrawable trd = new TextureRegionDrawable(atlas.findRegion(CharacterSpriteFetcher.smallSpriteFrom(character.getCharacterType())));
        Image characterSprite = new Image(trd);
        table.setBackground(image.getDrawable());
        table.pad(30);
        Label label = new Label(character.getName(), Assets.skin());
        InventorySlot headSlot = new InventorySlot(new Image(atlas.findRegion("helmet-splash")));
        InventorySlot capeSlot = new InventorySlot(new Image(atlas.findRegion("cape-splash")));
        InventorySlot earringSlot = new InventorySlot(new Image(atlas.findRegion("earring-splash")));
        InventorySlot weapon1Slot = new InventorySlot(new Image(atlas.findRegion("weapon-splash")));
        InventorySlot chestSlot = new InventorySlot(new Image(atlas.findRegion("chest-splash")));
        InventorySlot weapon2Slot = new InventorySlot(new Image(atlas.findRegion("shield-splash")));
        InventorySlot necklaceSlot = new InventorySlot(new Image(atlas.findRegion("necklace-splash")));
        InventorySlot gloveSlot = new InventorySlot(new Image(atlas.findRegion("gloves-splash")));
        InventorySlot feetSlot = new InventorySlot(new Image(atlas.findRegion("feet-splash")));
        InventorySlot ringSlot = new InventorySlot(new Image(atlas.findRegion("ring-splash")));
        InventorySlot legSlot = new InventorySlot(new Image(atlas.findRegion("legs-splash")));
        headSlot.addListener(new InventorySlotTooltipListener(inventorySlotTooltip));
        table.add(label).colspan(2);
        table.add(characterSprite);
        table.row();
        table.add();
        table.add(headSlot).size(SLOT_WIDTH, SLOT_HEIGHT);
        table.add();
        table.row();

        table.add(capeSlot).size(SLOT_WIDTH, SLOT_HEIGHT);
        table.add(necklaceSlot).size(SLOT_WIDTH, SLOT_HEIGHT);
        table.add(earringSlot).size(SLOT_WIDTH, SLOT_HEIGHT);
        table.row();

        table.add(weapon1Slot).size(SLOT_WIDTH, SLOT_HEIGHT);
        table.add(chestSlot).size(SLOT_WIDTH, SLOT_HEIGHT);
        table.add(weapon2Slot).size(SLOT_WIDTH, SLOT_HEIGHT);
        table.row();
        table.add();
        table.add(legSlot).size(SLOT_WIDTH, SLOT_HEIGHT);
        table.add();
        table.row();

        table.add(gloveSlot).size(SLOT_WIDTH, SLOT_HEIGHT);
        table.add(feetSlot).size(SLOT_WIDTH, SLOT_HEIGHT);
        table.add(ringSlot).size(SLOT_WIDTH, SLOT_HEIGHT);

        return table;
    }


    public void populateInventory(){
        clearInventoryItems(inventorySlotTable);
        Array<InventoryItemLocation> itemLocations = new Array<>();
        java.util.List<InventoryItem> inventoryContents = GameState.getInstance().getInventory().getInventoryList();
        for(int i = 0; i < inventoryContents.size(); i++){
            itemLocations.add(new InventoryItemLocation(i, inventoryContents.get(i).getItemTypeId(), 1, InventoryUi.PLAYER_INVENTORY));
        }
        Array<Cell> cells = inventorySlotTable.getCells();
        for(int i = 0; i <  itemLocations.size; i++){
            InventoryItemLocation itemLocation = itemLocations.get(i);
            InventorySlot inventorySlot = ((InventorySlot)cells.get(itemLocation.getLocationIndex()).getActor());

            for( int index = 0; index < itemLocation.getNumberItemsAtLocation(); index++ ){
                InventoryItem item = InventoryItemFactory.getInstance().of(itemLocation.getItemTypeAtLocation());
                String itemName =  itemLocation.getItemNameProperty();
                if( itemName == null || itemName.isEmpty() ){
                    item.setName("Player_Inventory");
                }else{
                    item.setName(itemName);
                }

                inventorySlot.add(item);
                if( item.getName().equalsIgnoreCase("Player_Inventory") ){
                    dragAndDrop.addSource(new InventorySlotSource(inventorySlot, dragAndDrop));
                }
            }
        }
    }



    public static void clearInventoryItems(Table targetTable){
        Array<Cell> cells = targetTable.getCells();
        for( int i = 0; i < cells.size; i++){
            InventorySlot inventorySlot = (InventorySlot)cells.get(i).getActor();
            if( inventorySlot == null ) continue;
            inventorySlot.clearAllInventoryItems(false);
        }
    }
    private void buildInventoryPanel(){
        Table table = new Table();
        table.setBackground(new Image(
                Assets.skin().getPatch("inventory-button-up")
        ).getDrawable());
        for(int i = 1; i <= TOTAL_SLOTS; i++){
            InventorySlot slot = new InventorySlot();
           // slot.addListener(new InventorySlotTooltipListener(inventorySlotTooltip));
            table.add(slot).size(SLOT_WIDTH, SLOT_HEIGHT);
            if(i % ROW_LENGTH == 0){
                table.row();
            }
        }
        inventorySlotTable = table;
    }

    public Array<Actor> getInventoryActors(){
        return inventoryActors;
    }


}
