package com.mygdx.game.character.enemies.homesteaders;

import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.Assets;
import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.character.buff.Buff;
import com.mygdx.game.character.buff.PoisonDebuff;
import com.mygdx.game.screens.encounterscreens.combat.DamageCalculator;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;
import com.mygdx.game.screens.widgets.fight.FightNode;

import java.util.Random;

import static com.mygdx.game.Assets.MASTER_VOLUME;

public class DogBite implements Ability {
    private static final int DAMAGE = 4;
    @Override
    public String name() {
        return "Bite";
    }



    @Override
    public String description() {
        return "A nasty bite.\nDeals " + DAMAGE +" base damage.";
    }



    @Override
    public boolean offensiveTargetable() {
        return true;
    }



    @Override
    public boolean friendlyTargetable() {
        return false;
    }



    @Override
    public boolean selfTargetable() {
        return false;
    }



    @Override
    public void execute(CharacterPanel target, CharacterPanel source, FightNode fight) {
        Sound sound = Assets.getInstance().getSoundAsset("dog-growl.mp3");
        sound.play(MASTER_VOLUME);

        int actualDamage = DamageCalculator.calculateDamage(damageType(), DAMAGE, source, target);
        target.decreaseHpBy(actualDamage);
    }



    @Override
    public AbilityType abilityType() {
        return AbilityType.HOMESTEADER_DOG_BITE;
    }



    @Override
    public DamageType damageType() {
        return DamageType.PHYSICAL;
    }
}
