package com.mygdx.game.screens.widgets.inventory;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.state.Stats;

public class InventoryItem extends Image {

    private ItemAttribute itemAttribute;
    private ItemUseType itemUseType;
    private ItemTypeId itemTypeId;
    private String displayName;
    private String combatRewardsDisplayName;
    private String itemShortDescription;
    private String combatRewardsDisplayText;
    private int coinValue;
    private Stats statModifiers;
    private ConsumableDetails consumableDetails;

    public InventoryItem(TextureRegion textureRegion, ItemAttribute itemAttribute, ItemTypeId itemTypeId, ItemUseType itemUseType, int coinValue, Stats statModifiers){
        super(textureRegion);
        this.itemTypeId = itemTypeId;
        this.itemAttribute = itemAttribute;
        this.itemUseType = itemUseType;
        this.coinValue = coinValue;
        this.statModifiers = statModifiers;
    }

    public InventoryItem(){
        super();
    }

    public InventoryItem(InventoryItem inventoryItem){
        super();
        this.itemTypeId = inventoryItem.getItemTypeId();
        this.itemAttribute = inventoryItem.getItemAttribute();
        this.itemUseType = inventoryItem.getItemUseType();
        this.itemShortDescription = inventoryItem.getItemShortDescription();
        this.combatRewardsDisplayText = inventoryItem.getItemShortDescription();
        this.statModifiers = inventoryItem.getStatModifiers();
        this.combatRewardsDisplayName = inventoryItem.getDisplayName();
        this.coinValue = inventoryItem.getCoinValue();
        this.displayName = inventoryItem.getDisplayName();
        this.consumableDetails = inventoryItem.consumableDetails;
    }

    public String getDisplayName() {
        return displayName;
    }



    public String getCombatRewardsDisplayText() {
        return combatRewardsDisplayText;
    }



    public InventoryItem setCombatRewardsDisplayText(String combatRewardsDisplayText) {
        this.combatRewardsDisplayText = combatRewardsDisplayText;
        this.combatRewardsDisplayName = combatRewardsDisplayText;
        return this;
    }



    public String getCombatRewardsDisplayName() {
        return combatRewardsDisplayName;
    }



    public Stats getStatModifiers() {
        return statModifiers;
    }



    public InventoryItem setStatModifiers(Stats statModifiers) {
        this.statModifiers = statModifiers;
        return this;
    }



    public enum ItemAttribute{
        CONSUMABLE(1),
        EQUIPPABLE(2),
        QUEST(4);

        private int attribute;

        ItemAttribute(int attribute){
            this.attribute = attribute;
        }

        public int getValue(){
            return attribute;
        }
    }

    public enum ItemTypeId {
        HIDE_SHIELD, HEALTH_POT,
        RUSTY_DAGGER, QUESTION_MARK,
        SNUG_SANDALS,
        ARMOR_HAT,
        LEATHER_PANTS, MINER_HAT,
        SWAMP_KEY, FENCE_KEY, WAR_CHEST_KEY,
        TANKARD, SPOILED_MILK,HOMESTEADERS_SUNHAT, PITCHFORK, STALE_BREAD, LANTERN, SHORT_SWORD, BRASS_CROZIER, FEZ, RED_DRESS, BROKEN_BOTTLE,
        FRESHMAN_SPELLCRAFT_NOTEBOOK, RAT_TAIL, BAREFISTS, FIRE_POT;
    }

    public enum ItemUseType{
        ITEM_RESTORE_HEALTH(1),
        ITEM_DAMAGE(4),
        WEAPON_ONEHAND(8),
        WEAPON_TWOHAND(16),
        ARMOR_SHIELD(128),
        ARMOR_HEAD(256),
        ARMOR_EARRING(12),
        ARMOR_NECKLACE(162),
        ARMOR_CAPE(123),
        ARMOR_RING(14),
        ARMOR_GLOVES(15),
        ARMOR_CHEST(512),
        ARMOR_LEGS(10241),
        ARMOR_FEET(1024),
        QUEST_ITEM(2048);

        private int itemUseType;

        ItemUseType(int itemUseType){
            this.itemUseType = itemUseType;
        }

        public int getValue(){
            return itemUseType;
        }
    }


    public int getCoinValue() {
        return coinValue;
    }

    public void setCoinValue(int coinValue) {
        this.coinValue = coinValue;
    }

    public ItemTypeId getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(ItemTypeId itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public ItemAttribute getItemAttribute() {
        return itemAttribute;
    }

    public void setItemAttribute(ItemAttribute itemAttribute) {
        this.itemAttribute = itemAttribute;
    }

    public ItemUseType getItemUseType() {
        return itemUseType;
    }

    public void setItemUseType(ItemUseType itemUseType) {
        this.itemUseType = itemUseType;
    }

    public String getItemShortDescription() {
        return itemShortDescription;
    }

    public void setItemShortDescription(String itemShortDescription) {
        this.itemShortDescription = itemShortDescription;
    }

    public boolean isSameItemType(InventoryItem candidateInventoryItem){
        return itemTypeId == candidateInventoryItem.getItemTypeId();
    }

    public static boolean doesRestoreHP(int itemUseType){
        return ((itemUseType & ItemUseType.ITEM_RESTORE_HEALTH.getValue()) == ItemUseType.ITEM_RESTORE_HEALTH.getValue());
    }

    public ConsumableDetails getConsumableDetails() {
        return consumableDetails;
    }


    public int getTradeValue(){
        //For now, we will set the trade in value of items at about one third their original value
        if( coinValue >= 0 ) {
            return MathUtils.floor(coinValue * .33f) + 2;
        }else{
            return 0;
        }
    }

}

