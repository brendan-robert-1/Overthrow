package com.mygdx.game.screens.widgets.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.widgets.CharacterSprite;
import com.mygdx.game.screens.widgets.Team;
import com.mygdx.game.state.Character;
import com.mygdx.game.screens.widgets.inventory.InventoryItem.ItemTypeId;
import com.mygdx.game.screens.widgets.inventory.InventoryItem.ItemUseType;
import com.mygdx.game.state.items.InventoryItemFactory;

public class InventoryUi extends Window{

    public static final int TOTAL_SLOTS = 50;
    private static final int ROW_LENGTH = 10;
    public static final int SLOT_WIDTH = 52;
    public static final int SLOT_HEIGHT = 52;
    public static final String PLAYER_INVENTORY = "Player_Inventory";
    private Team team = Team.getInstance();


    private Table inventorySlotTable;
    private EquipSlots character1EquipSlots;
    private EquipSlots character2EquipSlots;
    private EquipSlots character3EquipSlots;
    private EquipSlots character4EquipSlots;
    private InventorySlotTooltip inventorySlotTooltip;
    private Array<Actor> inventoryActors;
    private DragAndDrop dragAndDrop;

    private static InventoryUi instance;

    public static InventoryUi getInstance(){
        if(instance == null){
            instance = new InventoryUi();
        }
        return instance;
    }

    private InventoryUi(){
        super("Inventory", Assets.skin());
        dragAndDrop = new DragAndDrop();
        dragAndDrop.setDragTime(100);
        dragAndDrop.setKeepWithinStage(false);
        inventorySlotTable = new Table();
        inventoryActors = new Array<>();
        inventorySlotTooltip = new InventorySlotTooltip(Assets.skin());
        inventoryActors.add(inventorySlotTooltip);
        populate();
        this.add(inventorySlotTable).colspan(team.activeCharacters()).pad(20);
        this.row();
        if(team.getFirstCharacterPanel() != null){
            this.add(character1EquipSlots).padBottom(20).padLeft(20).padRight(20);
        }
        if(team.getSecondCharacterPanel() != null){
            this.add(character2EquipSlots).padBottom(20).padLeft(20).padRight(20);
        }
        if(team.getThirdCharacterPanel() != null){
            this.add(character3EquipSlots).padBottom(20).padLeft(20).padRight(20);
        }
        if(team.getFourthCharacterPanel() != null){
            this.add(character4EquipSlots).padBottom(20).padLeft(20).padRight(20);
        }

        this.setVisible(false);
        this.pack();
        this.setPosition((Gdx.graphics.getWidth() - this.getWidth())/2, ((Gdx.graphics.getHeight() - this.getHeight())/2));
    }

    public boolean inventoryFull(){
        for(Actor actor : inventorySlotTable.getChildren()){
            InventorySlot inventorySlot = (InventorySlot) actor;
            if(!inventorySlot.hasItem()){
                return false;
            }
        }
        return true;
    }




    private void populate(){
        buildInventoryPanel();
        buildGearPanels();
    }

    private void buildGearPanels(){
        if(team.getFirstCharacterPanel() != null){
            character1EquipSlots = new EquipSlots(team.getFirstCharacterPanel().getCharacter(), inventorySlotTooltip, dragAndDrop);
        }
        if(team.getSecondCharacterPanel() != null){
            character2EquipSlots =new EquipSlots(team.getSecondCharacterPanel().getCharacter(), inventorySlotTooltip, dragAndDrop);
        }
        if(team.getThirdCharacterPanel() != null){
            character3EquipSlots = new EquipSlots(team.getThirdCharacterPanel().getCharacter(), inventorySlotTooltip, dragAndDrop);
        }
        if(team.getFourthCharacterPanel() != null){
            character4EquipSlots = new EquipSlots(team.getFourthCharacterPanel().getCharacter(), inventorySlotTooltip, dragAndDrop);
        }
    }


    //mainly used for loading from disk
    public static  void populateInventory(Array<InventoryItemLocation> inventoryItems){
        clearInventoryItems(instance.inventorySlotTable);
        Array<Cell> cells = instance.inventorySlotTable.getCells();
        for(int i = 0; i < inventoryItems.size; i++){
            InventoryItemLocation itemLocation = inventoryItems.get(i);
            ItemTypeId itemTypeId = itemLocation.getItemTypeAtLocation();
            InventorySlot inventorySlot =  ((InventorySlot)cells.get(itemLocation.getLocationIndex()).getActor());

            for( int index = 0; index < itemLocation.getNumberItemsAtLocation(); index++ ){
                InventoryItem item = InventoryItemFactory.getInstance().of(itemTypeId);
                String itemName =  itemLocation.getItemNameProperty();
                if( itemName == null || itemName.isEmpty() ){
                    item.setName("default_item_name");
                }else{
                    item.setName(itemName);
                }
                inventorySlot.add(item);
                instance.dragAndDrop.addSource(new InventorySlotSource(inventorySlot, instance.dragAndDrop));
            }
        }
        instance.inventorySlotTable.pack();
    }

    private void addItemAt(InventoryItem inventoryItem, int index){
       InventorySlot slot = (InventorySlot) inventorySlotTable.getChildren().get(index);
       slot.add(inventoryItem);
       instance.dragAndDrop.addSource(new InventorySlotSource(slot, instance.dragAndDrop));
    }


    public void addToFirstOpenSlot(InventoryItem combatRewardOption) {
        for(int i = 0; i < inventorySlotTable.getChildren().size; i++){
            InventorySlot inventorySlot = (InventorySlot)inventorySlotTable.getChildren().get(i) ;
            if(!inventorySlot.hasItem()){
                addItemAt(combatRewardOption, i);
                return;
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
            dragAndDrop.addTarget(new InventorySlotTarget(slot));
            slot.addListener(new InventorySlotTooltipListener(inventorySlotTooltip));
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


    public Table getInventorySlotTable() {
        return inventorySlotTable;
    }



    public EquipSlots getCharacter1EquipSlots() {
        return character1EquipSlots;
    }



    public EquipSlots getCharacter2EquipSlots() {
        return character2EquipSlots;
    }



    public EquipSlots getCharacter3EquipSlots() {
        return character3EquipSlots;
    }



    public EquipSlots getCharacter4EquipSlots() {
        return character4EquipSlots;
    }




    public DragAndDrop getDragAndDrop() {
        return dragAndDrop;
    }


}
