package com.mygdx.game.screens.widgets.fight;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.widgets.AbilitySprite;
import com.mygdx.game.state.Character;

public class AbilitySelectPanel extends Table {
    public AbilitySelectPanel(){
        this.setVisible(false);
    }



    public void populateAbillites(Character activeCharacter) {
        this.getChildren().forEach(Actor::remove);

        Image ability1Image = new AbilitySprite(activeCharacter.getFirstBasicAbility().abilityType());
        ability1Image.setScaling(Scaling.fit);
        ability1Image.setSize(40,40);

        Image ability2Image = new AbilitySprite(activeCharacter.getSecondBasicAbility().abilityType());
        ability2Image.setScaling(Scaling.fit);
        ability2Image.setSize(40,40);
        ability2Image.setSize(40,40);

        Image ultImage = new AbilitySprite(activeCharacter.getUltimateAbility().abilityType());
        ultImage.setScaling(Scaling.fit);
        ultImage.setSize(40,40);
        ultImage.setSize(40,40);


        Image background = new Image(Assets.skin().getPatch("inventory-background"));
        background.setSize(52,52);
        Stack ability1 = new Stack();
        ability1.add(background);
        ability1.add(ability1Image);

        background = new Image(Assets.skin().getPatch("inventory-background"));
        background.setSize(52,52);
        Stack ability2 = new Stack();
        ability2.add(background);
        ability2.add(ability2Image);

        background = new Image(Assets.skin().getPatch("inventory-background"));
        background.setSize(52,52);
        Stack ult = new Stack();
        ult.add(background);
        ult.add(ultImage);

        this.add(ability1);
        this.add(ability2);
        this.add(ult);
        this.pack();
    }
}
