package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.Assets;
import com.mygdx.game.state.Character;
import com.mygdx.game.state.GameState;

public class Team extends Table{

    public Team(){
        this.add(characterPanel(GameState.getInstance().getCharacterSlots().firstCharacter())).expand().fill();
        this.add(characterPanel(GameState.getInstance().getCharacterSlots().secondCharacter())).expand().fill();
        this.add(characterPanel(GameState.getInstance().getCharacterSlots().thirdCharacter())).expand().fill();
        this.add(characterPanel(GameState.getInstance().getCharacterSlots().fourthCharacter())).expand().fill();
        this.pack();
    }



    private Table characterPanel(Character character) {
        if(character == null){
            return emptyCharacterPanel();
        }
        Table characterPanel = new Table(Assets.skin());
        characterPanel.add(new Label(character.getName() + "        hp: " + character.getHp(), Assets.skin(), "title")).expandX();
        characterPanel.row();
        Image characterSprite = new CharacterSprite(character.getCharacterType());
        characterSprite.setScaling(Scaling.fit);
        characterPanel.add(characterSprite).width(300).height(300);
        InspectBox characterInspectBox = new InspectBox(character.getName(),
                "hp: " + character.getHp() + "\n" +
                        "armor: " + character.getHp() + "\n"+
                        "magic resistance: " + character.getHp() + "\n"+
                        "physical damage: " + character.getHp() + "\n"+
                        "magic damage: " + character.getHp() + "\n"+
                        "speed: " + character.getHp() + "\n"
        );
        //characterPanel.addListener(new RightClickInspectListener(stage, characterInspectBox));
        characterPanel.row();
        characterPanel.defaults().expandX();
        return characterPanel;
    }

    private Table emptyCharacterPanel(){
        Table empty = new Table(Assets.skin());
        empty.defaults().expand().fill();
        empty.add(new Label("                   ", Assets.skin()));
        return empty;
    }

}
