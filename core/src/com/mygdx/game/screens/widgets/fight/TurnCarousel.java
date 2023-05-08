package com.mygdx.game.screens.widgets.fight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.Assets;
import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.screens.widgets.AbilitySprite;
import com.mygdx.game.screens.widgets.CharacterSprite;
import com.mygdx.game.state.Character;

import java.util.ArrayList;
import java.util.List;

public class TurnCarousel extends Table{

    List<Character> futureTurns = new ArrayList<>();


    public TurnCarousel(){
        this.setPosition(Gdx.graphics.getWidth()/2 - this.getWidth()/2, Gdx.graphics.getHeight()-100);
        this.setVisible(true);
    }


//    @Override
//    public void onNotify(String text, java.util.List<Character> futureTurns, TurnEvent event) {
//        if(event.equals(TurnEvent.TURN_STARTED)){
//           populateCarousel(futureTurns);
//        }
//    }


    public void populateCarousel(List<Character> futureTurns) {
        this.futureTurns = futureTurns;
        for(Character turn : futureTurns){
            Stack images = new Stack();
            TextureAtlas atlas = Assets.getAssetManager().get("overthrow.atlas", TextureAtlas.class);
            Image background = new Image(atlas.findRegion("turn-carousel-background"));
            Image sprite;
            if(Character.isFriendly(turn.getCharacterType())){
               sprite = new CharacterSprite(turn.getCharacterType(), true);
            } else {
                sprite = new CharacterSprite(turn.getCharacterType());
            }
            images.add(background);
            images.add(sprite);
            this.add(images);
        }

        this.pack();
        this.setPosition(Gdx.graphics.getWidth()/2 - this.getWidth()/2, Gdx.graphics.getHeight()-100);
    }



    public void update(List<Character> futureTurns) {
        //this.add(new AbilitySprite(Ability.AbilityType.MIASMA));
       populateCarousel(futureTurns);
    }
}
