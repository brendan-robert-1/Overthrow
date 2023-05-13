package com.mygdx.game.character.enemies.homesteaders;

import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.Assets;
import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.character.buff.ArmorUpBuff;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;
import com.mygdx.game.screens.widgets.fight.FightNode;

import static com.mygdx.game.Assets.MASTER_VOLUME;

public class DogGrowl implements Ability {
    @Override
    public String name() {
        return "Dog Growl";
    }



    @Override
    public String description() {
        return "Increases armor for entire team by 1 for 3 turns";
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
//        Sound sound = Assets.getInstance().getSoundAsset("man-growl.mp3");
//        sound.play(MASTER_VOLUME);
        target.getBuffsBar().addBuff(new ArmorUpBuff(3));
    }



    @Override
    public AbilityType abilityType() {
        return AbilityType.HOMESTEADER_DOG_GROWL;
    }



    @Override
    public DamageType damageType() {
        return DamageType.NONE;
    }
}
