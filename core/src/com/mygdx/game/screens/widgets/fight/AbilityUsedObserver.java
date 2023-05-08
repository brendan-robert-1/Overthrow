package com.mygdx.game.screens.widgets.fight;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.state.Character;

public interface AbilityUsedObserver {
    void onNotify(Character targetCharacter, Ability ability, Character sourceCharacter);
}

