package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Assets;
import com.mygdx.game.state.CharacterSlots;
import com.mygdx.game.state.GameState;
import com.mygdx.game.state.Inventory;
import com.mygdx.game.state.items.ItemSlot;

public class InventoryUi extends Window {

    public static final int TOTAL_SLOTS = 50;
    private static final int ROW_LENGTH = 10;
    private static final int SLOT_WIDTH = 52;
    private static final int SLOT_HEIGHT = 52;
    private CharacterSlots characterSlots;
    private Skin skin;
    private Table inventorySlotTable;
    private Table character1EquipSlots;
    private Table character2EquipSlots;
    private Table character3EquipSlots;
    private Table character4EquipSlots;

    public InventoryUi(){
        super("Inventory", Assets.skin());
        characterSlots = GameState.getInstance().getCharacterSlots();
        skin = Assets.skin();
        inventorySlotTable = new Table();
        populate();
        this.add(inventorySlotTable).colspan(4).padBottom(20).padTop(20);
        this.row();
        this.add(character1EquipSlots).padBottom(20).padLeft(20).padRight(20);
        this.add(character2EquipSlots).padBottom(20).padLeft(20).padRight(20);

        this.add(character3EquipSlots).padBottom(20).padLeft(20).padRight(20);
        this.add(character4EquipSlots).padBottom(20).padLeft(20).padRight(20);
        this.pack();
    }

    private void populate(){
        buildInventoryPanel();
        populateInventory();

        buildGearPanels();
        populateGearPanels();
    }

    private void buildGearPanels(){
        character1EquipSlots = buildGearPanel(1);
        character2EquipSlots = buildGearPanel(2);
        character3EquipSlots = buildGearPanel(3);
        character4EquipSlots = buildGearPanel(4);
    }
    private void populateGearPanels(){}

    private Table buildGearPanel(int characterNumber){
        Table table = new Table();
        Label label = new Label("Character: " + characterNumber, Assets.skin());
        InventorySlot headSlot = new InventorySlot();
        InventorySlot capeSlot = new InventorySlot();
        InventorySlot earringSlot = new InventorySlot();
        InventorySlot weapon1Slot = new InventorySlot();
        InventorySlot chestSlot = new InventorySlot();
        InventorySlot weapon2Slot = new InventorySlot();
        InventorySlot necklaceSlot = new InventorySlot();
        InventorySlot gloveSlot = new InventorySlot();
        InventorySlot feetSlot = new InventorySlot();
        InventorySlot ringSlot = new InventorySlot();
        InventorySlot legSlot = new InventorySlot();
        table.add(label).colspan(3);
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


    private void populateInventory(){
        Inventory inventory = GameState.getInstance().getInventory();
        clearInventoryItems(inventorySlotTable);
        for(ItemSlot item : inventory.getInventoryList()){

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
            table.add(slot).size(SLOT_WIDTH, SLOT_HEIGHT);
            if(i % ROW_LENGTH == 0){
                table.row();
            }
        }
        inventorySlotTable = table;
    }

}
