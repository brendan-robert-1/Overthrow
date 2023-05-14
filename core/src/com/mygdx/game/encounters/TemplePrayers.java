package com.mygdx.game.encounters;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.encounterscreens.MainGameScreen;
import com.mygdx.game.screens.widgets.ProceedButton;
import com.mygdx.game.screens.widgets.ProceedObserver;
import com.mygdx.game.screens.widgets.ProceedSubject;
import com.mygdx.game.screens.widgets.Team;
import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.screens.widgets.inventory.InventoryUi;
import com.mygdx.game.state.items.InventoryItemFactory;

import java.util.Random;

public class TemplePrayers extends GameNode implements ProceedSubject {
    private static final int PARTY_MAX_HP_INCREASE = 5;
    private static final int PARTY_HEAL = 25;

    private Table options;
    private Table explanation;
    private Table rewards = new Table();

    public TemplePrayers(){
        super(NodeType.TEMPLE_PRAYERS, "Temple Prayers");
        options = optionsTable();
        explanation = new Table();
        explanation.setBackground(Assets.skin().getDrawable("button-up"));
        explanation.add(new Label("You arrive at the steps of the local temple.\n" +
                "Pray at the shrine to demonstrate your piety to the local clergy.", Assets.skin())).pad(20);
        this.add(explanation).expand();
         this.add(options).expand().fill();
    }



    private Table optionsTable() {
        Table optionsTable = new Table();
        TextButton partyMaxHp = new TextButton("+" + PARTY_MAX_HP_INCREASE + " party max hp ", Assets.skin());
        partyMaxHp.pad(20);
        partyMaxHp.addListener(maxHpListener());

        TextButton partyHeal = new TextButton("+" + PARTY_HEAL + " party heal", Assets.skin());
        partyHeal.pad(20);
        partyHeal.addListener(healListener());

        TextButton randomItem = new TextButton("Obtain a random equippable item", Assets.skin());
        randomItem.pad(20);
        randomItem.addListener(randomItemListener());

        optionsTable.add(partyMaxHp).pad(20);
        optionsTable.row();
        optionsTable.add(partyHeal).pad(20);
        optionsTable.row();
        optionsTable.add(randomItem).pad(20);
        return optionsTable;
    }



    private EventListener healListener() {
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                explanation.remove();
                healParty();
                options.clearChildren();
                options.add(healReward());
                super.clicked(event, x, y);
            }
        };
    }



    private void healParty() {
        Team.getInstance().streamNonNull().forEach(c -> {
            c.increaseHpBy(PARTY_HEAL);
        });
    }



    private Table healReward() {
        Table table = new Table();
        table.setBackground(Assets.skin().getDrawable("button-up"));
        Label label = new Label("Your party has been healed by " + PARTY_HEAL, Assets.skin());
        table.add(label).pad(20);
        table.row();
        ProceedButton proceedButton = new ProceedButton();
        proceedButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                TemplePrayers.this.notify(ProceedObserver.ProceedEvent.PROCEED);
            }
        });
        table.add(proceedButton).pad(20);
        return table;
    }



    private EventListener randomItemListener() {
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                explanation.remove();
                options.clearChildren();
                options.add(randomItemReward());
                super.clicked(event, x, y);
            }
        };
    }



    private Table randomItemReward() {
        Table table = new Table();
        table.setBackground(Assets.skin().getDrawable("button-up"));
        InventoryItem item = InventoryItemFactory.getInstance().of(randomItem());
        TextureAtlas atlas = Assets.getAssetManager().get("overthrow.atlas", TextureAtlas.class);
        TextureRegionDrawable trd = new TextureRegionDrawable(atlas.findRegion(item.getItemTypeId().toString()));
        Image image = new Image(trd);
        image.setScaling(Scaling.fill);
        table.add(new Label("Your piety awards you a " +item.getDisplayName()+" .", Assets.skin())).pad(20);
        table.row();
        table.add(image).minSize(96).maxSize(96).pad(20);
        table.row();
        ProceedButton proceedButton = new ProceedButton();
        proceedButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                TemplePrayers.this.notify(ProceedObserver.ProceedEvent.PROCEED);
                InventoryUi.getInstance().addToFirstOpenSlot(item);
                super.clicked(event, x, y);
            }
        });
        table.add(proceedButton).pad(20);
        return table;
    }

    private InventoryItem.ItemTypeId randomItem(){
        switch(new Random().nextInt(0,3)){
            case 0 -> {return InventoryItem.ItemTypeId.SHORT_SWORD;}
            case 1 -> {return InventoryItem.ItemTypeId.BRASS_CROZIER;}
            case 2 -> {return InventoryItem.ItemTypeId.FEZ;}
            default -> throw new IllegalStateException("Unexpected value: " + new Random().nextInt(0, 2));
        }
    }



    private EventListener maxHpListener() {
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                explanation.remove();
                increasePartyMaxHp();
                options.clearChildren();
                options.add(maxHpReward());
                super.clicked(event, x, y);
            }
        };
    }



    private void increasePartyMaxHp() {
        Team.getInstance().streamNonNull().forEach(c -> {
            c.increaseMaxHpBy(PARTY_MAX_HP_INCREASE);
        });
    }



    private Table maxHpReward() {
        Table table = new Table();
        table.setBackground(Assets.skin().getDrawable("button-up"));
        Label label = new Label("Your party has increased their max hp by: " + PARTY_MAX_HP_INCREASE, Assets.skin());
        table.add(label).pad(20);
        table.row();
        ProceedButton proceedButton = new ProceedButton();
        proceedButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                TemplePrayers.this.notify(ProceedObserver.ProceedEvent.PROCEED);
            }
        });
        table.add(proceedButton).pad(20);
        return table;
    }



    @Override
    public String backgroundAsset() {
        return "town";
    }



    @Override
    public String ambientSounds() {
        return "church.mp3";
    }



    @Override
    public void notify(ProceedObserver.ProceedEvent event) {
        MainGameScreen.getInstance().onNotify(ProceedObserver.ProceedEvent.PROCEED);
    }
}
