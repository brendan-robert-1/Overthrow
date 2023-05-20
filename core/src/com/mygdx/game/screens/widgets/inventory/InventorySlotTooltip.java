package com.mygdx.game.screens.widgets.inventory;


import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class InventorySlotTooltip extends Window {

    private Skin skin;
    private Label description;

    public InventorySlotTooltip(final Skin skin){
        super("", skin);
        this.skin = skin;

        description = new Label("", skin);

        this.add(description).pad(20);
        this.pad(5);
        this.pack();
        this.setVisible(false);
    }

    public void setVisible(InventorySlot inventorySlot, boolean visible) {
        super.setVisible(visible);

        if( inventorySlot == null ){
            return;
        }

        if (!inventorySlot.hasItem()) {
            super.setVisible(false);
        }
    }

    public void updateDescription(InventorySlot inventorySlot){
        if( inventorySlot.hasItem() ){
            InventoryItem item = inventorySlot.getTopInventoryItem();
            String desc = getDescription(item);
            description.setText(desc);
            this.pack();
        }else{
            description.setText("");
            this.pack();
        }
    }

    private String getDescription(InventoryItem inventoryItem){
        StringBuilder sb = new StringBuilder();
        switch(inventoryItem.getItemAttribute()){
            case CONSUMABLE -> {
                ConsumableDetails cd = inventoryItem.getConsumableDetails();
                sb.append(inventoryItem.getDisplayName());
                sb.append(System.getProperty("line.separator"));
                sb.append(inventoryItem.getItemShortDescription());
                sb.append(System.getProperty("line.separator"));
                sb.append(consumablePotencyTypeDesc(cd.getPotencyType())); //Damages/Heals
                sb.append(" ").append(cd.getPotency())
                    .append(consumablePotencyTypeValueDesc(cd.getPotencyValueType())) //25 percent
                        .append(" hp");
                sb.append(System.getProperty("line.separator"));
                sb.append(splashDesc(cd.getSplashType()));
                return sb.toString();
            }
            case EQUIPPABLE -> {
                return inventoryItem.getDisplayName();
            }
            case QUEST -> {
                return inventoryItem.getDisplayName();
            }
            default -> throw new IllegalStateException("Unexpected value: " + inventoryItem.getItemAttribute());
        }
    }

    private String consumablePotencyTypeDesc(ConsumableDetails.PotencyType potencyType){
        switch(potencyType){
            case DAMAGE -> {return "Damages";}
            case HEAL -> {return "Heals";}
            default -> throw new IllegalStateException("Unexpected value: " + potencyType);
        }
    }

    private String consumablePotencyTypeValueDesc(ConsumableDetails.PotencyValueType potencyValueType){
        switch(potencyValueType){
            case PERCENT -> {return "%";}
            case FLAT -> {return " flat";}
            default -> throw new IllegalStateException("Unexpected value: " + potencyValueType);
        }
    }
    private String splashDesc(ConsumableDetails.SplashType splashType){
        switch(splashType){
            case SURROUNDING -> {return "Splashes units surrounding target";}
            case NONE -> {return "Single target";}
            case ENTIRE -> {return "Splashes entire team";}
            default -> throw new IllegalStateException("Unexpected value: " + splashType);
        }
    }
}

