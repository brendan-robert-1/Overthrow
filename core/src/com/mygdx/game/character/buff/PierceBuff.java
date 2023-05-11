package com.mygdx.game.character.buff;

import com.mygdx.game.screens.widgets.fight.CharacterPanel;
import com.mygdx.game.state.Character;

import static com.mygdx.game.character.buff.Buff.BuffType.PIERCE;

public class PierceBuff extends Buff {

    public PierceBuff(int turnsRemaining) {
        super(turnsRemaining, PIERCE);
    }



    @Override
    public void executeEndOfTurn(CharacterPanel characterPanel) {

    }



    @Override
    public String getDisplayText() {
        return "Target takes 15% increased damage";
    }
}
