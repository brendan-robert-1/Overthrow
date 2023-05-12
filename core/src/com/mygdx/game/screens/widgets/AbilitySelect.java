package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.mygdx.game.Assets;
import com.mygdx.game.character.abilities.Ability;

public class AbilitySelect extends Stack {

    private Image abilityDecal;
    private Image background = new Image(Assets.skin().getPatch("inventory-background"));

    private Ability ability;

    public AbilitySelect( Image abilityDecal, Ability ability){
        this.abilityDecal = abilityDecal;
        this.ability = ability;
        background.setSize(52,52);
        this.add(background);
        this.add(abilityDecal);
    }



    public Ability getAbility() {
        return ability;
    }
}
