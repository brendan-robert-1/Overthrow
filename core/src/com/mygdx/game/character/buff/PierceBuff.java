package com.mygdx.game.character.buff;

import com.mygdx.game.state.Character;

import static com.mygdx.game.character.buff.Buff.BuffType.PIERCE;

public class PierceBuff extends Buff {

    public PierceBuff(int turnsRemaining) {
        super(turnsRemaining, PIERCE);
    }



    @Override
    public void executeEndOfTurn(Character character) {

    }



    @Override
    public String getDisplayText() {
        return "Target takes 15% increased damage";
    }
}
