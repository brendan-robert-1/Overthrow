package com.mygdx.game.character.buff;

import com.mygdx.game.state.Character;

import java.io.UTFDataFormatException;

public class PoisonDebuff extends Buff {
    private int damagePerTurn;
    public PoisonDebuff(int turnsRemaining, int damagePerTurn) {
        super(turnsRemaining, BuffType.POISON);
        this.damagePerTurn = damagePerTurn;
    }



    @Override
    public void executeEndOfTurn(Character character) {
        character.decreaseHpBy(damagePerTurn);
        this.damagePerTurn --;
        if(damagePerTurn <= 0){
            this.turnsRemaining = 0;
        }
    }



    @Override
    public String getDisplayText() {
        return damagePerTurn + " damage per turn. Reduced by 1 each turn.";
    }
}
