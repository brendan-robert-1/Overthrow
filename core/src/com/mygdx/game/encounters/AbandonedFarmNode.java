package com.mygdx.game.encounters;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.Assets;
import com.mygdx.game.render.AnimatedActor;
import com.mygdx.game.screens.encounterscreens.MainGameScreen;
import com.mygdx.game.screens.widgets.*;
import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.screens.widgets.inventory.InventoryUi;
import com.mygdx.game.state.items.InventoryItemFactory;

import java.util.Random;

import static com.mygdx.game.Assets.MASTER_VOLUME;

public class AbandonedFarmNode extends GameNode implements ProceedSubject, BackgroundChangeSubject {

    private Table options;
    private Table explanation;
    private Table rewards = new Table();



    @Override
    public String backgroundAsset() {
        return "abandoned-farmhouse2";
    }



    @Override
    public String ambientSounds() {
        return null;
    }



    public AbandonedFarmNode(){
        super(NodeType.ABANDONED_FARMHOUSE, "Abandoned Farmhouse");

        options = optionsTable();
        explanation = new Table();
        explanation.setBackground(Assets.skin().getDrawable("button-up"));
        explanation.add(new Label("You stumble on a farmhouse, maybe it's abandoned?", Assets.skin())).pad(20);
        explanation.row();
        explanation.add(new Label("Maybe there is something useful inside.", Assets.skin())).pad(20);
        this.add(explanation).expand();
        //options.setPosition(Gdx.graphics.getWidth()/2 - options.getWidth()/2, Gdx.graphics.getHeight()/2 - options.getHeight()/2);
        this.add(options).expand().fill();
        //        CharacterSprite sprite = new CharacterSprite(Character.CharacterType.KEYMASTER);
        //        sprite.setScaling(Scaling.fit);
        //        this.add(sprite).expand().bottom().right().height(300).width(300);


    }

    public Table optionsTable(){
        Table table = new Table();
        TextButton purchase = new TextButton("Ransack Basement (Dangerous)", Assets.skin());
        purchase.pad(20);
        purchase.addListener(ransackBasement());

        TextButton bloodOffer = new TextButton("Rummage Pantry (Safe)", Assets.skin());
        bloodOffer.addListener(rummagePantry());
        bloodOffer.pad(20);

        TextButton dismiss = new TextButton("Ignore", Assets.skin());
        dismiss.addListener(ignoreListener());
        dismiss.pad(20);

        table.add(purchase).pad(20);
        table.row();
        table.add(bloodOffer).pad(20);
        table.row();
        table.add(dismiss).pad(20);
        return table;
    }



    private ClickListener ransackBasement(){
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                explanation.remove();
                options.clearChildren();
                options.add(basementReward());
                super.clicked(event, x, y);
            }
        };
    }
    private ClickListener rummagePantry(){
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                explanation.remove();
                options.clearChildren();
                options.add(pantryReward());
                super.clicked(event, x, y);
            }
        };
    }

    private Table pantryReward() {
        Table keyRewards = new Table();
        keyRewards.setBackground(Assets.skin().getDrawable("button-up"));
        InventoryItem pantryItem = InventoryItemFactory.getInstance().of(randomPantryItem());
        TextureAtlas atlas = Assets.getAssetManager().get("overthrow.atlas", TextureAtlas.class);
        TextureRegionDrawable trd = new TextureRegionDrawable(atlas.findRegion(pantryItem.getItemTypeId().toString()));
        Image image = new Image(trd);
        image.setScaling(Scaling.fill);
        keyRewards.add(new Label("You find " +pantryItem.getDisplayName()+" in the pantry.", Assets.skin())).pad(20);
        keyRewards.row();
        keyRewards.add(image).minSize(96).maxSize(96).pad(20);
        keyRewards.row();
        ProceedButton proceedButton = new ProceedButton();
        proceedButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AbandonedFarmNode.this.notify(ProceedObserver.ProceedEvent.PROCEED);
                InventoryUi.getInstance().addToFirstOpenSlot(pantryItem);
                super.clicked(event, x, y);
            }
        });
        keyRewards.add(proceedButton).pad(20);
        return keyRewards;
    }

    private Table basementReward() {
        Table keyRewards = new Table();
        keyRewards.setBackground(Assets.skin().getDrawable("button-up"));
        InventoryItem basementReward = InventoryItemFactory.getInstance().of(randomBasementItem());
        TextureAtlas atlas = Assets.getAssetManager().get("overthrow.atlas", TextureAtlas.class);
        TextureRegionDrawable trd = new TextureRegionDrawable(atlas.findRegion(basementReward.getItemTypeId().toString()));
        Image image = new Image(trd);
        image.setScaling(Scaling.fill);
        keyRewards.add(new Label("You find " +basementReward.getDisplayName()+" in the pantry.", Assets.skin())).pad(20);
        keyRewards.row();
        keyRewards.add(image).minSize(96).maxSize(96).pad(20);
        keyRewards.row();
        ProceedButton proceedButton = new ProceedButton();
        proceedButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AbandonedFarmNode.this.notify(ProceedObserver.ProceedEvent.PROCEED);
                InventoryUi.getInstance().addToFirstOpenSlot(basementReward);
                super.clicked(event, x, y);
            }
        });
        keyRewards.add(proceedButton).pad(20);
        return keyRewards;
    }


    private InventoryItem.ItemTypeId randomPantryItem(){
        switch(new Random().nextInt(0,3)){
            case 0 -> {return InventoryItem.ItemTypeId.STALE_BREAD;}
            case 1 -> {return InventoryItem.ItemTypeId.TANKARD;}
            case 2 -> {return InventoryItem.ItemTypeId.SPOILED_MILK;}
            default -> throw new IllegalStateException("Unexpected value: " + new Random().nextInt(0, 2));
        }
    }

    private InventoryItem.ItemTypeId randomBasementItem(){
        switch(new Random().nextInt(0,3)){
            case 0 -> {return InventoryItem.ItemTypeId.HOMESTEADERS_SUNHAT;}
            case 1 -> {return InventoryItem.ItemTypeId.PITCHFORK;}
            case 2 -> {return InventoryItem.ItemTypeId.LANTERN;}
            default -> throw new IllegalStateException("Unexpected value: " + new Random().nextInt(0, 2));
        }
    }

    private ClickListener ignoreListener(){
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                explanation.remove();
                options.clearChildren();
                options.add(ignoreReward());
                super.clicked(event, x, y);
            }
        };
    }

    private Table ignoreReward() {
        Table ignoreRewards = new Table();
        ignoreRewards.add(new Label("You ignore the abandoned farmhouse.", Assets.skin()));
        ProceedButton proceedButton = new ProceedButton();
        proceedButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AbandonedFarmNode.this.notify(ProceedObserver.ProceedEvent.PROCEED);
                super.clicked(event, x, y);
            }
        });
        ignoreRewards.add(proceedButton);
        return ignoreRewards;
    }

    @Override
    public void notify(ProceedObserver.ProceedEvent event) {
        MainGameScreen.getInstance().onNotify(event);
    }



    @Override
    public void notify(String backgroundAsset, BackgroundChangeObserver.BackgroundChange event) {
        MainGameScreen.getInstance().onNotify("abandoned-farmhouse2", BackgroundChangeObserver.BackgroundChange.ChangeBackground);
    }
}
