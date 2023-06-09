package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Assets;

public class ArmorMerchantScreen extends ScreenAdapter {

    @Override
    public void show() {
        Table market = new Table().right().padRight(200);
        populateMarket(market);
    }

    private void populateMarket(Table table){
        Label label = new Label("Market", Assets.skin());
        table.add(label);
        table.row();
        TextButton healthPot = new TextButton("Bronze Helmet", Assets.skin());
        table.add(healthPot).fillX();
        table.row();
        TextButton hideShield = new TextButton("Cloth Shirt", Assets.skin());
        table.add(hideShield).fillX();;
        table.row();
        TextButton bluntDagger = new TextButton("Studded Leather Boots", Assets.skin());
        table.add(bluntDagger).fillX();;
        table.row();
        TextButton leatherPants = new TextButton("Leather Pants", Assets.skin());
        table.add(leatherPants).fillX();;
        table.row();
        TextButton proceed = new TextButton("Next Encounter ->", Assets.skin());
        proceed.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Proceeding to next encounter...");
            }
        });
        table.add(proceed).padTop(25);
        table.row();
    }
}
