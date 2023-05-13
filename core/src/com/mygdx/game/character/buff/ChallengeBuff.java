package com.mygdx.game.character.buff;

import com.mygdx.game.screens.widgets.fight.CharacterPanel;

public class ChallengeBuff extends Buff {
    public ChallengeBuff(int turnsRemaining) {
        super(turnsRemaining, BuffType.CHALLENGE);
    }



    @Override
    public void executeEndOfTurn(CharacterPanel characterPanel) {

    }



    @Override
    public String getDisplayText() {
        return "Increases damage taken from target by 50%";
    }
}
