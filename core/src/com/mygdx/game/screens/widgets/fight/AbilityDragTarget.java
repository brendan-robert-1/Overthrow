package com.mygdx.game.screens.widgets.fight;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.screens.encounterscreens.MainGameScreen;
import com.mygdx.game.state.Character;

public class AbilityDragTarget extends Target implements AbilityUsedSubject {
    Actor targetTable;
    Array<AbilityUsedObserver> observers = new Array<>();
    private Character targetCharacter;

    public AbilityDragTarget(Actor target, Character targetCharacter){
        super(target);
        this.targetTable = target;
        this.targetCharacter = targetCharacter;
        observers.add(MainGameScreen.getInstance());
    }



    @Override
    public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
        return true;
    }



    @Override
    public void drop(Source source, Payload payload, float x, float y, int pointer) {
        AbilityDragSource abilityDragSource = (AbilityDragSource) source;
        notify(targetCharacter, abilityDragSource.getAbility(), abilityDragSource.getSourceCharacter());
    }



    @Override
    public void addObserver(AbilityUsedObserver observer) {

    }



    @Override
    public void removeObserver(AbilityUsedObserver observer) {

    }



    @Override
    public void removeAllObservers() {

    }



    @Override
    public void notify(Character targetCharacter, Ability ability, Character sourceCharacter) {
        observers.forEach(observer -> {
            observer.onNotify(targetCharacter, ability, sourceCharacter);
        });
    }
}
