package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Assets;
import com.mygdx.game.encounters.WishingWell;
import com.mygdx.game.state.GameState;

public class WishingWellScreen extends InGameEncounterScreen {
    private GameState gameState = GameState.getInstance();
    private WishingWell wishingWell;
    @Override
    public void show() {
        wishingWell = (WishingWell) gameState.getCurrentNode();
        Table market = new Table();
        market.right().padRight(200);
        populateWishEncounter(market);
    }

    private void populateWishEncounter(Table table){
        Label label = new Label("Wishing Well", Assets.skin(), "title");
        label.setAlignment(Align.center);
        table.add(label).colspan(2).spaceBottom(10);
        table.row();
        int percentChance = 50;
        Label chance = new Label(percentChance + "% chance the well is in your favor", Assets.skin(), "title");
        chance.setAlignment(Align.center);
        table.add(chance).colspan(2);
        table.row();
        TextButton throwCoins = new TextButton("Throw 10 coins", Assets.skin());
        throwCoins.addListener(throwCoinsListener(chance));
        table.add(throwCoins).pad(10).fillX();
        TextButton wish = new TextButton("Wish", Assets.skin());
        wish.addListener(wishListener());
        table.add(wish).pad(10).fillX();
        table.row();
        populateEncounter(table);
    }



    private EventListener throwCoinsListener(Label label) {
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Threw 10 coins to increase luck!");
                if(gameState.getCoin()>= 10 && wishingWell.getPercentChance() < 100){
                    wishingWell.throwCoins();
                    gameState.decreaseCoinBy(10);
                    updateCoins();
                    displayChance(wishingWell.getPercentChance(), label);
                }
            }
        };
    }
    private void displayChance(int chance, Label label){
        label.setText(chance + "% chance the well is in your favor");
    }



    private ClickListener wishListener(){
        return new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
               if(wishingWell.wishSuccessful()){
                   System.out.println("Wish Successful!");
               } else {
                   System.out.println("Wish unsuccessful.");
               }
               //displayWishOutcome();
                redirectNextNode();
            }
        };
    }
}
