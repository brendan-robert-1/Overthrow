package com.mygdx.game.character.plaguedoctor;

import com.badlogic.gdx.audio.Sound;
import com.mygdx.game.Assets;
import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.character.buff.Buff;
import com.mygdx.game.character.buff.PoisonDebuff;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;
import com.mygdx.game.screens.widgets.fight.FightNode;
import com.mygdx.game.state.Character;

import static com.mygdx.game.Assets.MASTER_VOLUME;

public class TossConcoction implements Ability {

    @Override
    public String name() {
        return "Toss Concoction";
    }



    @Override
    public String description() {
        return "AOE debuffing attack";
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
        target.decreaseHpBy(1);
        Buff poison = new PoisonDebuff(3, 3);
        Sound sound = Assets.getInstance().getSoundAsset("axe.mp3");
        sound.play(MASTER_VOLUME);
        target.getBuffsBar().addDebuff(poison);
    }



    @Override
    public AbilityType abilityType() {
        return AbilityType.TOSS_CONCOCTION;
    }
}
