package com.mygdx.game.screens.widgets.fight;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.state.Character;

public interface AbilityUsedSubject {
    public void addObserver(AbilityUsedObserver observer);
    public void removeObserver(AbilityUsedObserver observer);
    public void removeAllObservers();
    public void notify(Character targetCharacter, Ability ability, Character sourceCharacter);
}
