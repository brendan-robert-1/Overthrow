package com.mygdx.game.screens.widgets.inventory;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.encounterscreens.combat.ConsumableManager;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;

import java.util.List;

public class InventoryContextMenu extends Table {
    private InventoryItem item;
    private boolean isInside = false;
    private Vector2 currentCords;
    private Vector2 offset;

    public InventoryContextMenu(InventoryItem item, float x, float y){
        this.item = item;
        this.currentCords = new Vector2(x,y);
        this.offset = new Vector2(0,0);
        this.setBackground(Assets.skin().getDrawable("button-up"));
        TextButton use = new TextButton("Use", Assets.skin());
        this.localToStageCoordinates(currentCords);
        this.setPosition(currentCords.x, currentCords.y);
        use.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("using " + item.getDisplayName());
                InventoryContextMenu.this.setVisible(true);
                displayConsumableTargetSelect();
                InventoryUi.getInstance().setVisible(false);
                super.clicked(event, x, y);
            }



            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                System.out.println("leaving context menu.");
                InventoryContextMenu.this.remove();
                super.exit(event, x, y, pointer, toActor);

            }
        });
        this.add(use);
    }

    private void displayConsumableTargetSelect() {
        List<CharacterPanel> viableTargets = ConsumableManager.getInstance().viableTargets(item.getConsumableDetails());
        final int[] amount = {400};
        viableTargets.forEach(c -> {
            c.displaySelectable(amount[0]);
            amount[0] += 400;
        }
        );

    }
}
