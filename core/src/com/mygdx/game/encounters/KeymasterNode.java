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
import com.mygdx.game.screens.widgets.ProceedButton;
import com.mygdx.game.screens.widgets.ProceedObserver;
import com.mygdx.game.screens.widgets.ProceedSubject;
import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.screens.widgets.inventory.InventoryUi;
import com.mygdx.game.state.items.InventoryItemFactory;

import java.util.Random;

import static com.mygdx.game.Assets.MASTER_VOLUME;

public class KeymasterNode extends GameNode implements ProceedSubject {

    private Table options;
    private Table explanation;
    private Table rewards = new Table();

    @Override
    public String backgroundAsset() {
        return "farms-fire";
    }


    @Override
    public String ambientSounds() {
        return "keys.mp3";
    }

    public KeymasterNode(){
        super(NodeType.KEYMASTER, "Keymaster");
        options = optionsTable();
        explanation = new Table();
        explanation.setBackground(Assets.skin().getDrawable("button-up"));
        explanation.add(new Label("The Keymaster offers you a key in exchange for a sacrifice.", Assets.skin())).pad(20);
        this.add(explanation).expand();
        //options.setPosition(Gdx.graphics.getWidth()/2 - options.getWidth()/2, Gdx.graphics.getHeight()/2 - options.getHeight()/2);
        this.add(options).expand().fill();
//        CharacterSprite sprite = new CharacterSprite(Character.CharacterType.KEYMASTER);
//        sprite.setScaling(Scaling.fit);
//        this.add(sprite).expand().bottom().right().height(300).width(300);

        TextureAtlas atlas = Assets.getAssetManager().get("overthrow.atlas", TextureAtlas.class);
        Animation<TextureRegion> idleKnight = new Animation<TextureRegion>(0.1f, atlas.findRegions("keymaster"), Animation.PlayMode.LOOP);
        AnimatedActor animatedActor = new AnimatedActor(idleKnight);
        animatedActor.setScaling(Scaling.fit);
        this.add(animatedActor).expand().bottom().right().height(300).width(300);
    }

    private Table optionsTable(){
        Table table = new Table();
        TextButton purchase = new TextButton("Purchase", Assets.skin());
        purchase.pad(20);
        purchase.addListener(purchaseKeyListener());

        TextButton bloodOffer = new TextButton("Blood offer", Assets.skin());
        bloodOffer.addListener(bloodOfferKeyListener());
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



    private ClickListener purchaseKeyListener(){
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                explanation.remove();
                options.clearChildren();
                options.add(keyReward());
                final Sound sound1 = Assets.getInstance().getSoundAsset("keys.mp3");
                sound1.play(MASTER_VOLUME);
                super.clicked(event, x, y);
            }
        };
    }
    private ClickListener bloodOfferKeyListener(){
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                explanation.remove();
                options.clearChildren();
                options.add(keyReward());
                final Sound sound1 = Assets.getInstance().getSoundAsset("keys.mp3");
                sound1.play(MASTER_VOLUME);
                super.clicked(event, x, y);
            }
        };
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


    private Table keyReward() {
        Table keyRewards = new Table();
        keyRewards.setBackground(Assets.skin().getDrawable("button-up"));
        InventoryItem key = InventoryItemFactory.getInstance().of(randomKey());
        TextureAtlas atlas = Assets.getAssetManager().get("overthrow.atlas", TextureAtlas.class);
        TextureRegionDrawable trd = new TextureRegionDrawable(atlas.findRegion(key.getItemTypeId().toString()));
        Image image = new Image(trd);
        image.setScaling(Scaling.fill);
        keyRewards.add(new Label("You are rewarded with a " +key.getDisplayName()+" .", Assets.skin())).pad(20);
        keyRewards.row();
        keyRewards.add(image).minSize(96).maxSize(96).pad(20);
        keyRewards.row();
        ProceedButton proceedButton = new ProceedButton();
        proceedButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                KeymasterNode.this.notify(ProceedObserver.ProceedEvent.PROCEED);
                InventoryUi.getInstance().addToFirstOpenSlot(key);
                super.clicked(event, x, y);
            }
        });
        keyRewards.add(proceedButton).pad(20);
        return keyRewards;
    }
    
    private InventoryItem.ItemTypeId randomKey(){
        switch(new Random().nextInt(0,3)){
            case 0 -> {return InventoryItem.ItemTypeId.FENCE_KEY;}
            case 1 -> {return InventoryItem.ItemTypeId.SWAMP_KEY;}
            case 2 -> {return InventoryItem.ItemTypeId.WAR_CHEST_KEY;}
            default -> throw new IllegalStateException("Unexpected value: " + new Random().nextInt(0, 2));
        }
    }


    private Table ignoreReward() {
        Table ignoreRewards = new Table();
        ignoreRewards.add(new Label("You ignore the Keymaster.", Assets.skin()));
        ProceedButton proceedButton = new ProceedButton();
        proceedButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                KeymasterNode.this.notify(ProceedObserver.ProceedEvent.PROCEED);
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
}
