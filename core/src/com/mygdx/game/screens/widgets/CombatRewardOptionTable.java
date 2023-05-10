package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.encounterscreens.MainGameScreen;
import com.mygdx.game.screens.widgets.fight.CombatRewardSelectedObserver;
import com.mygdx.game.screens.widgets.fight.CombatRewardSubject;
import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.screens.widgets.inventory.InventoryItem.ItemTypeId;
import com.mygdx.game.screens.widgets.inventory.InventoryUi;

public class CombatRewardOptionTable extends Table implements CombatRewardSubject {

    private InventoryItem combatRewardOption;

    public CombatRewardOptionTable(InventoryItem combatRewardOption, boolean isQuestionMark){
        this.combatRewardOption = combatRewardOption;
        ItemSprite itemSprite;
        if(isQuestionMark){
            itemSprite = new ItemSprite(ItemTypeId.QUESTION_MARK);
        }else {
            itemSprite = new ItemSprite(combatRewardOption.getItemTypeId());
        }
        itemSprite.setScaling(Scaling.fit);
        itemSprite.setSize(128,128);
        this.add(itemSprite).expand().fill();
        this.addListener(clickedListener());
    }

    private InputListener clickedListener(){
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                InventoryUi.getInstance().addToFirstOpenSlot(combatRewardOption);
                CombatRewardOptionTable.this.notify(CombatRewardSelectedObserver.RewardType.SELECTED);
                super.clicked(event, x, y);
            }
        };
    }



    public InventoryItem getCombatRewardOption() {
        return combatRewardOption;
    }



    @Override
    public void notify(CombatRewardSelectedObserver.RewardType type) {
        MainGameScreen.getInstance().onNotify(CombatRewardSelectedObserver.RewardType.SELECTED);
    }
}
