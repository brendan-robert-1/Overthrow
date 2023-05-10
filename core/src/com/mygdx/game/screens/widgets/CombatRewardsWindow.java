package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.encounterscreens.combat.CombatRewards;
import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.state.items.InventoryItemFactory;

public class CombatRewardsWindow extends Table {

    private CombatRewards combatRewards;
    private HudTooltip hudTooltip = HudTooltip.getInstance();

    public CombatRewardsWindow(){
        this.add(rewardsWindow()).expand().fill();
        this.setSize(Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 100);
        this.pack();
        this.setPosition(Gdx.graphics.getWidth()/2 - this.getWidth()/2, Gdx.graphics.getHeight()/2 - this.getHeight()/2);


    }

    private Table rewardsWindow(){
        Table table = new Table(Assets.skin());
        table.setBackground(Assets.skin().getDrawable("button-up"));
        Label label = new Label("Choose a reward", Assets.skin(), "title");
        table.add(label).expand().colspan(3).pad(30);
        table.row();

        InventoryItem item1 = InventoryItemFactory.getInstance().of(InventoryItem.ItemTypeId.SNUG_SANDALS);
        item1.setCombatRewardsDisplayText("???");
        InventoryItem item3 = InventoryItemFactory.getInstance().of(InventoryItem.ItemTypeId.LEATHER_PANTS);
        InventoryItem item4 = InventoryItemFactory.getInstance().of(InventoryItem.ItemTypeId.MINER_HAT);


        CombatRewardOptionTable combatRewardOptionTable1 = new CombatRewardOptionTable(item1, true);
        CombatRewardOptionTable combatRewardOptionTable2 = new CombatRewardOptionTable(item3, false);
        CombatRewardOptionTable combatRewardOptionTable3 = new CombatRewardOptionTable(item4, false);

        table.add(combatRewardOptionTable1).expand().height(128).width(128).fill().pad(20);
        table.add(combatRewardOptionTable2).expand().height(128).width(128).fill().pad(20);
        table.add(combatRewardOptionTable3).expand().height(128).width(128).fill().pad(20);

        combatRewardOptionTable1.addListener(new HudTooltipListener());
        combatRewardOptionTable2.addListener(new HudTooltipListener());
        combatRewardOptionTable3.addListener(new HudTooltipListener());
        return table;
    }


}
