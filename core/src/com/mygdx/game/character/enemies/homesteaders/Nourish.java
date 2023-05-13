package com.mygdx.game.character.enemies.homesteaders;

import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.Assets;
import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;
import com.mygdx.game.screens.widgets.fight.FightNode;

import static com.mygdx.game.Assets.MASTER_VOLUME;

public class Nourish implements Ability {

    private static final int HEAL = 5;
    @Override
    public String name() {
        return "Nourish";
    }



    @Override
    public String description() {
        return "Heals target";
    }



    @Override
    public boolean offensiveTargetable() {
        return false;
    }



    @Override
    public boolean friendlyTargetable() {
        return true;
    }



    @Override
    public boolean selfTargetable() {
        return true;
    }



    @Override
    public void execute(CharacterPanel target, CharacterPanel source, FightNode fight) {
        Sound sound = Assets.getInstance().getSoundAsset("bottle.mp3");
        sound.play(MASTER_VOLUME);
        target.increaseHpBy(5);
    }



    @Override
    public AbilityType abilityType() {
        return AbilityType.HOMESTEADER_NOURISH;
    }



    @Override
    public DamageType damageType() {
        return DamageType.HEAL;
    }
}
