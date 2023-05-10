package com.mygdx.game.screens.widgets.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.widgets.CharacterSprite;
import com.mygdx.game.state.Character;
import com.mygdx.game.state.CharacterSlots;
import com.mygdx.game.state.GameState;
import com.mygdx.game.screens.widgets.inventory.InventoryItem.ItemTypeId;
import com.mygdx.game.screens.widgets.inventory.InventoryItem.ItemUseType;
import com.mygdx.game.state.items.InventoryItemFactory;

import java.util.Iterator;

public class InventoryUi extends Window{

    public static final int TOTAL_SLOTS = 50;
    private static final int ROW_LENGTH = 10;
    public static final int SLOT_WIDTH = 52;
    public static final int SLOT_HEIGHT = 52;
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

    private static InventoryUi instance;

    public static InventoryUi getInstance(){
        if(instance == null){
            instance = new InventoryUi();
        }
        return instance;
    }

    private InventoryUi(){
        super("Inventory", Assets.skin());
        characterSlots = GameState.getInstance().getCharacterSlots();
        dragAndDrop = new DragAndDrop();
        dragAndDrop.setDragTime(100);
        dragAndDrop.setKeepWithinStage(false);
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
        Image characterSprite = new CharacterSprite(character.getCharacterType());
        table.setBackground(image.getDrawable());
        table.pad(30);
        Label label = new Label(character.getName(), Assets.skin());
        InventorySlot headSlot = new InventorySlot(ItemUseType.ARMOR_HEAD, new InventorySplash(ItemUseType.WEAPON_ONEHAND));
        InventorySlot capeSlot = new InventorySlot(ItemUseType.ARMOR_CAPE,new InventorySplash(ItemUseType.WEAPON_ONEHAND));
        InventorySlot earringSlot = new InventorySlot(ItemUseType.ARMOR_EARRING, new InventorySplash(ItemUseType.WEAPON_ONEHAND));
        InventorySlot weapon1Slot = new InventorySlot(ItemUseType.WEAPON_ONEHAND, new InventorySplash(ItemUseType.WEAPON_ONEHAND));
        InventorySlot chestSlot = new InventorySlot(ItemUseType.ARMOR_CHEST,new InventorySplash(ItemUseType.WEAPON_ONEHAND));
        InventorySlot weapon2Slot = new InventorySlot(ItemUseType.WEAPON_TWOHAND, new InventorySplash(ItemUseType.WEAPON_ONEHAND));
        InventorySlot necklaceSlot = new InventorySlot(ItemUseType.ARMOR_NECKLACE, new InventorySplash(ItemUseType.WEAPON_ONEHAND));
        InventorySlot gloveSlot = new InventorySlot(ItemUseType.ARMOR_GLOVES, new InventorySplash(ItemUseType.WEAPON_ONEHAND));
        InventorySlot feetSlot = new InventorySlot(ItemUseType.ARMOR_FEET, new InventorySplash(ItemUseType.WEAPON_ONEHAND));
        InventorySlot ringSlot = new InventorySlot(ItemUseType.ARMOR_RING, new InventorySplash(ItemUseType.WEAPON_ONEHAND));
        InventorySlot legSlot = new InventorySlot(ItemUseType.ARMOR_LEGS,new InventorySplash(ItemUseType.WEAPON_ONEHAND));

        headSlot.addListener(new InventorySlotTooltipListener(inventorySlotTooltip));
        capeSlot.addListener(new InventorySlotTooltipListener(inventorySlotTooltip));
        earringSlot.addListener(new InventorySlotTooltipListener(inventorySlotTooltip));
        weapon1Slot.addListener(new InventorySlotTooltipListener(inventorySlotTooltip));
        chestSlot.addListener(new InventorySlotTooltipListener(inventorySlotTooltip));
        weapon2Slot.addListener(new InventorySlotTooltipListener(inventorySlotTooltip));
        necklaceSlot.addListener(new InventorySlotTooltipListener(inventorySlotTooltip));
        gloveSlot.addListener(new InventorySlotTooltipListener(inventorySlotTooltip));
        feetSlot.addListener(new InventorySlotTooltipListener(inventorySlotTooltip));
        ringSlot.addListener(new InventorySlotTooltipListener(inventorySlotTooltip));
        legSlot.addListener(new InventorySlotTooltipListener(inventorySlotTooltip));

        dragAndDrop.addTarget(new InventorySlotTarget(headSlot));
        dragAndDrop.addTarget(new InventorySlotTarget(capeSlot));
        dragAndDrop.addTarget(new InventorySlotTarget(earringSlot));
        dragAndDrop.addTarget(new InventorySlotTarget(weapon1Slot));
        dragAndDrop.addTarget(new InventorySlotTarget(weapon1Slot));
        dragAndDrop.addTarget(new InventorySlotTarget(weapon2Slot));
        dragAndDrop.addTarget(new InventorySlotTarget(necklaceSlot));
        dragAndDrop.addTarget(new InventorySlotTarget(gloveSlot));
        dragAndDrop.addTarget(new InventorySlotTarget(feetSlot));
        dragAndDrop.addTarget(new InventorySlotTarget(ringSlot));
        dragAndDrop.addTarget(new InventorySlotTarget(legSlot));


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



    public DragAndDrop getDragAndDrop() {
        return dragAndDrop;
    }


}
