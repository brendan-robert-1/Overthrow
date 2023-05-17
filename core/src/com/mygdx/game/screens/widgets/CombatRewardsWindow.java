package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.Gdx;
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
import com.mygdx.game.screens.encounterscreens.combat.CombatRewards;
import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.state.items.InventoryItemFactory;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CombatRewardsWindow extends Table {

    private CombatRewards combatRewards;
    private HudTooltip hudTooltip = HudTooltip.getInstance();
    private Table rewardsTable;
    private static final int DEFAULT_HIDDEN_OPTIONS = 1;
    private static final int DEFAULT_OPTIONS_AMOUNT = 3;

    public CombatRewardsWindow(CombatRewards combatRewards){
        this.combatRewards = combatRewards;
        rewardsTable = coinRewards();
        this.add(rewardsTable).expand().fill();
        this.setSize(Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 100);
        this.pack();
        this.setPosition(Gdx.graphics.getWidth()/2 - this.getWidth()/2, Gdx.graphics.getHeight()/2 - this.getHeight()/2);
    }


    private Table coinRewards() {
        Table table = new Table();
        table.setBackground(Assets.skin().getDrawable("button-up"));

        Label label = new Label("You have won the fight! You have been rewarded with: ", Assets.skin());
        table.add(label).pad(20);
        table.row();

        TextureAtlas atlas = Assets.getAssetManager().get("overthrow.atlas", TextureAtlas.class);
        TextureRegionDrawable trd = new TextureRegionDrawable(atlas.findRegion("coin"));
        Image image = new Image(trd);
        image.setScaling(Scaling.fill);
        table.add(image).minSize(96).maxSize(96).pad(20);
        table.row();
        TextButton coinsButton = new TextButton(combatRewards.coins + " coins",Assets.skin());
        coinsButton.pad(10);
        coinsButton.addListener(coinsButtonListener());
        table.add(coinsButton).pad(20);
        return table;
    }

    private Table itemChoice(){
        Table table = new Table(Assets.skin());
        table.setBackground(Assets.skin().getDrawable("button-up"));
        Label label = new Label("Choose a reward", Assets.skin(), "title");
        table.add(label).expand().colspan(3).pad(30);
        table.row();
        addOptionTables(DEFAULT_HIDDEN_OPTIONS, combatRewards.itemRewards, table);
        return table;
    }

    private void addOptionTables(int amountHidden, List<InventoryItem> options, Table table){
        if(amountHidden > options.size()){
            amountHidden = options.size();
        }
        int optionsNumber = DEFAULT_OPTIONS_AMOUNT;
        if(options.size() > optionsNumber){
            optionsNumber = options.size();
        }

        List<Integer> indexesUsed = new ArrayList<>();
        for(int i = 0 ; i < optionsNumber; i++){
            String displayName = "";
            boolean displayImage = false;
            int randomIndex = new Random().nextInt(options.size());
            while(indexesUsed.contains(randomIndex)){
                randomIndex = new Random().nextInt(options.size());
            }
            indexesUsed.add(randomIndex);
            InventoryItem item = options.get(randomIndex);
            if(amountHidden > 0){
                displayName = "???";
                displayImage = true;
                item.setCombatRewardsDisplayText(displayName);
                amountHidden--;
            }
            CombatRewardOptionTable combatRewardOptionTable = new CombatRewardOptionTable(item, displayImage);
            combatRewardOptionTable.addListener(new HudTooltipListener());
            table.add(combatRewardOptionTable).expand().height(128).width(128).fill().pad(20);
        }
    }




    private EventListener coinsButtonListener(){
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                TopBar.getInstance().increaseCoinBy(combatRewards.coins);
                rewardsTable.remove();
                CombatRewardsWindow.this.clearChildren();
                rewardsTable = itemChoice();
                CombatRewardsWindow.this.add(rewardsTable).expand().fill();
                CombatRewardsWindow.this.setSize(Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 100);
                CombatRewardsWindow.this.pack();
                CombatRewardsWindow.this.setPosition(Gdx.graphics.getWidth()/2 - CombatRewardsWindow.this.getWidth()/2,
                        Gdx.graphics.getHeight()/2 - CombatRewardsWindow.this.getHeight()/2);
                super.clicked(event, x, y);
            }
        };
    }


}
