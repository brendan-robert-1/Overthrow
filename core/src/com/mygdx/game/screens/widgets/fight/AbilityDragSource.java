package com.mygdx.game.screens.widgets.fight;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.character.abilities.Ability.AbilityType;
import com.mygdx.game.state.Character;

public class AbilityDragSource extends Source {

    private DragAndDrop dragAndDrop;
    private Ability ability;
    private Stack source;
    private CharacterPanel sourceCharacter;

    public AbilityDragSource(Stack source, Ability ability, DragAndDrop dragAndDrop, CharacterPanel sourceCharacter){
        super(source.getChildren().peek());
        this.source = source;
        this.dragAndDrop = dragAndDrop;
        this.ability = ability;
        this.sourceCharacter = sourceCharacter;
    }



    @Override
    public Payload dragStart(InputEvent event, float x, float y, int pointer) {
        Payload payload = new Payload();
        Actor actor = getActor();
        if( actor == null ){
            return null;
        }
        payload.setDragActor(getActor());
        event.getStage().addActor(getActor());
        actor.toFront();
        return payload;
    }
    @Override
    public void dragStop (InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
        source.add(payload.getDragActor());
    }



    public Ability getAbility() {
        return ability;
    }



    public CharacterPanel getSourceCharacter() {
        return sourceCharacter;
    }
}
