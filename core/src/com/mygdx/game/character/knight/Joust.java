package com.mygdx.game.character.knight;

import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.Assets;
import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.character.buff.Buff;
import com.mygdx.game.character.buff.PierceBuff;
import com.mygdx.game.screens.encounterscreens.combat.DamageCalculator;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;
import com.mygdx.game.screens.widgets.fight.FightNode;
import com.mygdx.game.state.Character;

import static com.mygdx.game.Assets.MASTER_VOLUME;

public class Joust implements Ability {

    public static final int DAMAGE = 7;
    @Override
    public String name() {
        return "Joust";
    }



    @Override
    public String description() {
        return "Lunge at targeted enemy dealing moderate damage and applying 2 turns of Pierce";
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
        Buff pierce = new PierceBuff(5);
        Sound sound = Assets.getInstance().getSoundAsset("joust.mp3");
        sound.play(MASTER_VOLUME);
        target.getBuffsBar().addDebuff(pierce);
    }



    @Override
    public AbilityType abilityType() {
        return AbilityType.JOUST;
    }



    @Override
    public DamageType damageType() {
        return DamageType.PHYSICAL;
    }
}
