package com.mygdx.game.screens.widgets.fight;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.state.Character;

public class CharacterPanel extends Table {

    private Character character;

    public CharacterPanel(Character character, Skin skin){
        super(skin);
        this.character = character;
    }



    public Character getCharacter() {
        return character;
    }
}
