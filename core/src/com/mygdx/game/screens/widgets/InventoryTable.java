package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Assets;
import com.mygdx.game.encounters.state.CharacterSlots;
import com.mygdx.game.encounters.state.GameState;
import com.mygdx.game.encounters.state.gear.EquippedGear;
import com.mygdx.game.encounters.state.Character;

public class InventoryTable extends Table {

    private CharacterSlots characterSlots;
    private Skin skin;
    public InventoryTable(){
        characterSlots = GameState.getInstance().getCharacterSlots();
        skin = Assets.skin();


        Table table = new Table();
        table.setName("entireScreen");
        table.setFillParent(true);

        Table table1 = new Table();
        table1.setName("inventory");
        table1.setBackground(skin.getDrawable("button-over"));
        table1.pad(25.0f);

        Table table2 = new Table();
        table2.setName("inventoryPanel");
        table2.setBackground(Assets.skin().getDrawable("button-down"));
        table1.add(table2).padBottom(25.0f).grow().align(Align.top);

        table1.row();
        table2 = new Table();
        table2.pad(0.0f);

        Table gearPanel = gearPanel(characterSlots.firstCharacter());
        table2.add(gearPanel).spaceRight(25.0f);


        gearPanel = gearPanel(characterSlots.secondCharacter());
        table2.add(gearPanel);
        table1.add(table2).grow();

        table1.row();
        table2 = new Table();
        table2.pad(0.0f);

        gearPanel = gearPanel(characterSlots.thirdCharacter());
        table2.add(gearPanel).spaceRight(25.0f);

        gearPanel = gearPanel(characterSlots.fourthCharacter());
        table2.add(gearPanel);
        table1.add(table2).spaceTop(25.0f).grow();
        table.add(table1).pad(100.0f).grow();
        this.add(table).grow();
        this.setVisible(false);
        this.setDebug(true);
    }

    private Window inventoryPanel(){
        Window inventoryPanel = new Window("Inventory", Assets.skin());

        return inventoryPanel;
    }

    private Window gearPanel(Character character) {
        if (character == null) {
            return null;
        } else {
            Table gearPanelTable = new Table();
            EquippedGear gear = character.equippedGear();
            InventoryButton helmet = new InventoryButton("Helmet: " + gear.helmet);
            TextButton necklace = new InventoryButton("Necklace: " + gear.necklace);
            TextButton chestplate = new InventoryButton("Chestplate: " + gear.chestplate);
            TextButton gloves = new InventoryButton("Gloves: " + gear.gloves);
            TextButton legs = new InventoryButton("Legs: " + gear.necklace);
            TextButton feet = new InventoryButton("Feet: " + gear.legs);
            TextButton ring = new InventoryButton("Ring: " + gear.ring);
            TextButton earring = new InventoryButton("Earring: " + gear.earring);
            TextButton rightHandWeapon = new InventoryButton("Weapon 1: " + gear.rightHandWeapon);
            TextButton leftHandWeapon = new InventoryButton("Weapon 2: " + gear.leftHandWeapon);
            gearPanelTable.add(helmet).expand().growX().space(10);
            gearPanelTable.add(necklace).expand().growX().space(10);
            gearPanelTable.add(chestplate).expand().growX().space(10);
            gearPanelTable.row();

            gearPanelTable.add(gloves).expand().growX().space(10);
            gearPanelTable.add(legs).expand().growX().space(10);
            gearPanelTable.add(feet).expand().growX().space(10);
            gearPanelTable.row();

            gearPanelTable.add(ring).expand().growX().space(10);
            gearPanelTable.add(earring).expand().growX().space(10);
            gearPanelTable.add(rightHandWeapon).expand().growX().space(10);

            gearPanelTable.row();
            gearPanelTable.add(leftHandWeapon).expand().growX().space(10);
            gearPanelTable.row();
            Window entireGearPanelWindow = new Window(character.name() + "  hp: " + character.hp(), Assets.skin());
            entireGearPanelWindow.add(gearPanelTable).pad(15);
            return entireGearPanelWindow;
        }
    }

}
