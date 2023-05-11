package com.mygdx.game.screens.widgets.fight;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ActionState;
import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.screens.encounterscreens.MainGameScreen;

public class AbilityDragTarget extends Target implements AbilityUsedSubject {
    Actor targetTable;
    private CharacterPanel targetCharacter;

    public AbilityDragTarget(Actor target, CharacterPanel targetCharacter){
        super(target);
        this.targetTable = target;
        this.targetCharacter = targetCharacter;
    }



    @Override
    public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
        return true;
    }



    @Override
    public void drop(Source source, Payload payload, float x, float y, int pointer) {
        AbilityDragSource abilityDragSource = (AbilityDragSource) source;
        if(abilityDragSource == null){
            return;
        }
        if(ActionState.playerStateValue == ActionState.StateValue.ALLOWED_TO_ACT){
            notify(targetCharacter, abilityDragSource.getAbility(), abilityDragSource.getSourceCharacter());
        } else {
            System.out.println("Not enough time elapsed ignoring action");
            return;
        }
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
    public void notify(CharacterPanel targetCharacter, Ability ability, CharacterPanel sourceCharacter) {
            MainGameScreen.getInstance().onNotify(targetCharacter, ability, sourceCharacter);
    }
}
