package com.mygdx.game.character.buff;

import com.mygdx.game.screens.widgets.fight.CharacterPanel;
import com.mygdx.game.state.Character;

public class ArmorUpBuff extends Buff {
    public ArmorUpBuff(int turnsRemaining) {
        super(turnsRemaining, BuffType.ARMOR_UP);
    }



    @Override
    public void executeEndOfTurn(CharacterPanel characterPanel) {

    }



    @Override
    public String getDisplayText() {
        return "Increases Armor by 3";
    }
}
