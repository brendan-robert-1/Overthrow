package com.mygdx.game.character.enemies.homesteaders;

import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.Assets;
import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.character.buff.PierceBuff;
import com.mygdx.game.screens.encounterscreens.combat.DamageCalculator;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;
import com.mygdx.game.screens.widgets.fight.FightNode;
import org.checkerframework.checker.units.qual.A;

import java.util.Random;

import static com.mygdx.game.Assets.MASTER_VOLUME;

public class PitchforkStab implements Ability {
    public static final int DAMAGE = 5;
    @Override
    public String name() {
        return "PitchforkStab";
    }



    @Override
    public String description() {
        return "Does moderate damage applies pierce";
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
        int actualDamage = DamageCalculator.calculateDamage(damageType(), DAMAGE, source, target);
        target.decreaseHpBy(actualDamage);
        Sound sound = Assets.getInstance().getSoundAsset("man-growl.mp3");
        sound.play(MASTER_VOLUME);
        target.getBuffsBar().addDebuff(new PierceBuff(4));
    }



    @Override
    public AbilityType abilityType() {
        return AbilityType.HOMESTEADER_PITCHFORK_STAB;
    }



    @Override
    public DamageType damageType() {
        return DamageType.PHYSICAL;
    }
}
