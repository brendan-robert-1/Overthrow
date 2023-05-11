package com.mygdx.game.screens.widgets.fight;

import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.screens.widgets.outfitter.OutfitterObserver;

public interface FightSubject {
    public void notify(final String eventd, FightObserver.FightEvent event);
}
