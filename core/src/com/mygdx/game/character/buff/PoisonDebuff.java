package com.mygdx.game.character.buff;

import com.mygdx.game.state.Character;

import java.io.UTFDataFormatException;

public class PoisonDebuff extends Buff {

    public PoisonDebuff(int turnsRemaining, int damagePerTurn) {
        super(turnsRemaining, BuffType.POISON);
        this.setPotency(damagePerTurn);
    }



    @Override
    public void executeEndOfTurn(Character character) {
        character.decreaseHpBy(getPotency());
        this.setPotency(this.getPotency() -1);
        if(getPotency() <= 0){
            this.turnsRemaining = 0;
        }
    }



    @Override
    public String getDisplayText() {
        return getPotency() + " damage per turn. Reduced by 1 each turn.";
    }
}
