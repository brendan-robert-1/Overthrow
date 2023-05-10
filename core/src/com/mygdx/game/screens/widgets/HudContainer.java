package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.screens.widgets.fight.AbilitySelectPanel;
import com.mygdx.game.screens.widgets.inventory.InventoryUi;

public class HudContainer extends Table {
    private Array<Actor> hudToolTipActors;
    private Team team;
    private AbilitySelectPanel abilitySelectPanel;

    public HudContainer(InventoryUi inventoryUi){

    }



    public Array<Actor> getHudToolTipActors() {
        return hudToolTipActors;
    }
}
