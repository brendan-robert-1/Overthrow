package com.mygdx.game.character.buff;

import com.mygdx.game.state.Character;

public class Bleed extends Buff{
    public int damagePerTurn;



    public Bleed(int turnsRemaining, int damagePerTurn) {
        super(turnsRemaining, BuffType.BLEED);
        this.damagePerTurn = damagePerTurn;
    }



    @Override
    public void executeEndOfTurn(Character character) {
        character.decreaseHpBy(damagePerTurn);
    }



    @Override
    public String getDisplayText() {
        return "Target takes " + damagePerTurn + " damage per turn.";
    }
}
