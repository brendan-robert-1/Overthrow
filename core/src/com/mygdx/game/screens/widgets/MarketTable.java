package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Assets;
import com.mygdx.game.encounters.Market;

public class MarketTable extends Table {

    private Market market;
    private Skin skin;
    public MarketTable(Market market){
        skin = Assets.skin();


        Table table = new Table();
        table.addListener(keyboardCloseMarketListener());
        table.setName("entireScreen");
        table.setFillParent(true);


        Table table1 = new Table();
        table1.setName("inventory");
        table1.setBackground(skin.getDrawable("button-over"));
        table1.pad(25.0f);
        PixelProTextButton closeMarket = new PixelProTextButton("Exit");
        closeMarket.addListener(closeMarketListener());
        table1.add(closeMarket);

        Table table2 = new Table();
        table2.setName("inventoryPanel");
        table2.setBackground(Assets.skin().getDrawable("button-down"));
        table2.add(new PixelProTextButton("view wares"));
        table1.add(table2).padBottom(25.0f).grow().align(Align.top);

        table1.row();
        table2 = new Table();
        table2.pad(0.0f);

        Table table3 = new Table();
        table3.setBackground(skin.getDrawable("button-down"));
        table2.add(table3).spaceRight(25.0f).grow();

        table3 = new Table();
        table3.setBackground(skin.getDrawable("button-down"));
        table2.add(table3).grow();
        table1.add(table2).grow();

        table1.row();
        table2 = new Table();
        table2.pad(0.0f);

        table3 = new Table();
        table3.setBackground(skin.getDrawable("button-down"));
        table2.add(table3).spaceRight(25.0f).grow();

        table3 = new Table();
        table3.setBackground(skin.getDrawable("button-down"));
        table2.add(table3).grow();
        table1.add(table2).spaceTop(25.0f).grow();
        table.add(table1).pad(100.0f).grow();
        this.add(table).grow();
    }



    private EventListener closeMarketListener() {
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MarketTable.super.remove();
            }
        };
    }

    private InputListener keyboardCloseMarketListener(){
        return new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode == Input.Keys.ESCAPE){
                    MarketTable.super.remove();
                }
                return super.keyDown(event, Input.Keys.ESCAPE);
            }
        };
    }


}
