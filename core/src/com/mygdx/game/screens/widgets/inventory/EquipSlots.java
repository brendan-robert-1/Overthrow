package com.mygdx.game.screens.widgets.inventory;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.widgets.CharacterSprite;
import com.mygdx.game.state.Character;
import static com.mygdx.game.screens.widgets.inventory.InventoryUi.SLOT_HEIGHT;
import static com.mygdx.game.screens.widgets.inventory.InventoryUi.SLOT_WIDTH;

public class EquipSlots extends Table {
    private InventorySlot headSlot = new InventorySlot(InventoryItem.ItemUseType.ARMOR_HEAD, new InventorySplash(InventoryItem.ItemUseType.ARMOR_HEAD));
    private InventorySlot capeSlot = new InventorySlot(InventoryItem.ItemUseType.ARMOR_CAPE,new InventorySplash(InventoryItem.ItemUseType.ARMOR_CAPE));
    private InventorySlot earringSlot = new InventorySlot(InventoryItem.ItemUseType.ARMOR_EARRING, new InventorySplash(InventoryItem.ItemUseType.ARMOR_EARRING));
    private InventorySlot weapon1Slot = new InventorySlot(InventoryItem.ItemUseType.WEAPON_ONEHAND, new InventorySplash(InventoryItem.ItemUseType.WEAPON_ONEHAND));
    private InventorySlot chestSlot = new InventorySlot(InventoryItem.ItemUseType.ARMOR_CHEST,new InventorySplash(InventoryItem.ItemUseType.ARMOR_CHEST));
    private InventorySlot weapon2Slot = new InventorySlot(InventoryItem.ItemUseType.ARMOR_SHIELD, new InventorySplash(InventoryItem.ItemUseType.ARMOR_SHIELD));
    private InventorySlot necklaceSlot = new InventorySlot(InventoryItem.ItemUseType.ARMOR_NECKLACE, new InventorySplash(InventoryItem.ItemUseType.ARMOR_NECKLACE));
    private InventorySlot gloveSlot = new InventorySlot(InventoryItem.ItemUseType.ARMOR_GLOVES, new InventorySplash(InventoryItem.ItemUseType.ARMOR_GLOVES));
    private InventorySlot feetSlot = new InventorySlot(InventoryItem.ItemUseType.ARMOR_FEET, new InventorySplash(InventoryItem.ItemUseType.ARMOR_FEET));
    private InventorySlot ringSlot = new InventorySlot(InventoryItem.ItemUseType.ARMOR_RING, new InventorySplash(InventoryItem.ItemUseType.ARMOR_RING));
    private InventorySlot legSlot = new InventorySlot(InventoryItem.ItemUseType.ARMOR_LEGS,new InventorySplash(InventoryItem.ItemUseType.ARMOR_LEGS));


    public EquipSlots(Character character, InventorySlotTooltip inventorySlotTooltip, DragAndDrop dragAndDrop){
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
        dragAndDrop.addTarget(new InventorySlotTarget(chestSlot));
        dragAndDrop.addTarget(new InventorySlotTarget(gloveSlot));
        dragAndDrop.addTarget(new InventorySlotTarget(feetSlot));
        dragAndDrop.addTarget(new InventorySlotTarget(ringSlot));
        dragAndDrop.addTarget(new InventorySlotTarget(legSlot));

        Image image = new Image(Assets.skin().getPatch("opaque-background"));
        this.setBackground(image.getDrawable());
        Image characterSprite = new CharacterSprite(character.getCharacterType());
        Label label = new Label(character.getName(), Assets.skin());
        this.add(label).colspan(2);
        this.add(characterSprite);
        this.row();
        this.add();
        this.add(headSlot).size(SLOT_WIDTH, SLOT_HEIGHT);
        this.add();
        this.row();

        this.add(capeSlot).size(SLOT_WIDTH, SLOT_HEIGHT);
        this.add(necklaceSlot).size(SLOT_WIDTH, SLOT_HEIGHT);
        this.add(earringSlot).size(SLOT_WIDTH, SLOT_HEIGHT);
        this.row();

        this.add(weapon1Slot).size(SLOT_WIDTH, SLOT_HEIGHT);
        this.add(chestSlot).size(SLOT_WIDTH, SLOT_HEIGHT);
        this.add(weapon2Slot).size(SLOT_WIDTH, SLOT_HEIGHT);
        this.row();
        this.add();
        this.add(legSlot).size(SLOT_WIDTH, SLOT_HEIGHT);
        this.add();
        this.row();

        this.add(gloveSlot).size(SLOT_WIDTH, SLOT_HEIGHT);
        this.add(feetSlot).size(SLOT_WIDTH, SLOT_HEIGHT);
        this.add(ringSlot).size(SLOT_WIDTH, SLOT_HEIGHT);


    }

    public InventorySlot getHeadSlot() {
        return headSlot;
    }



    public EquipSlots setHeadSlot(InventorySlot headSlot) {
        this.headSlot = headSlot;
        return this;
    }



    public InventorySlot getCapeSlot() {
        return capeSlot;
    }



    public EquipSlots setCapeSlot(InventorySlot capeSlot) {
        this.capeSlot = capeSlot;
        return this;
    }



    public InventorySlot getEarringSlot() {
        return earringSlot;
    }



    public EquipSlots setEarringSlot(InventorySlot earringSlot) {
        this.earringSlot = earringSlot;
        return this;
    }



    public InventorySlot getWeapon1Slot() {
        return weapon1Slot;
    }



    public EquipSlots setWeapon1Slot(InventorySlot weapon1Slot) {
        this.weapon1Slot = weapon1Slot;
        return this;
    }



    public InventorySlot getChestSlot() {
        return chestSlot;
    }



    public EquipSlots setChestSlot(InventorySlot chestSlot) {
        this.chestSlot = chestSlot;
        return this;
    }



    public InventorySlot getWeapon2Slot() {
        return weapon2Slot;
    }



    public EquipSlots setWeapon2Slot(InventorySlot weapon2Slot) {
        this.weapon2Slot = weapon2Slot;
        return this;
    }



    public InventorySlot getNecklaceSlot() {
        return necklaceSlot;
    }



    public EquipSlots setNecklaceSlot(InventorySlot necklaceSlot) {
        this.necklaceSlot = necklaceSlot;
        return this;
    }



    public InventorySlot getGloveSlot() {
        return gloveSlot;
    }



    public EquipSlots setGloveSlot(InventorySlot gloveSlot) {
        this.gloveSlot = gloveSlot;
        return this;
    }



    public InventorySlot getFeetSlot() {
        return feetSlot;
    }



    public EquipSlots setFeetSlot(InventorySlot feetSlot) {
        this.feetSlot = feetSlot;
        return this;
    }



    public InventorySlot getRingSlot() {
        return ringSlot;
    }



    public EquipSlots setRingSlot(InventorySlot ringSlot) {
        this.ringSlot = ringSlot;
        return this;
    }



    public InventorySlot getLegSlot() {
        return legSlot;
    }



    public EquipSlots setLegSlot(InventorySlot legSlot) {
        this.legSlot = legSlot;
        return this;
    }


}
