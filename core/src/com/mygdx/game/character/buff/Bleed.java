package com.mygdx.game.character.buff;

import com.mygdx.game.state.Character;

public class Bleed extends Buff{

    public Bleed(int turnsRemaining, int damagePerTurn) {
        super(turnsRemaining, BuffType.BLEED);
        this.setPotency(damagePerTurn);
    }



    @Override
    public void executeEndOfTurn(Character character) {
        character.decreaseHpBy(this.getPotency());
    }



    @Override
    public String getDisplayText() {
        return "Target takes damage per turn.";
    }
}
