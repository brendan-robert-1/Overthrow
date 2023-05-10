package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;
import com.mygdx.game.state.Character;
import com.mygdx.game.state.GameState;

public class Team extends Table{
    private HudTooltip hudTooltip = HudTooltip.getInstance();

    public void update() {
        this.clearChildren();
        this.populate();
    }

    public Team(){

        populate();
    }

    private void populate(){
        Character firstCharacter = GameState.getInstance().getCharacterSlots().firstCharacter();
        Character secondCharacter = GameState.getInstance().getCharacterSlots().secondCharacter();
        Character thirdCharacter = GameState.getInstance().getCharacterSlots().thirdCharacter();
        Character fourthCharacter = GameState.getInstance().getCharacterSlots().fourthCharacter();

        if(firstCharacter != null && firstCharacter.getHp() > 0){
            this.add(characterPanel(GameState.getInstance().getCharacterSlots().firstCharacter())).expand().fill();
        }
        if(secondCharacter != null&& secondCharacter.getHp() > 0){
            this.add(characterPanel(GameState.getInstance().getCharacterSlots().secondCharacter())).expand().fill();
        }
        if(thirdCharacter != null && thirdCharacter.getHp() > 0){
            this.add(characterPanel(GameState.getInstance().getCharacterSlots().thirdCharacter())).expand().fill();
        }
        if(fourthCharacter != null && fourthCharacter.getHp() > 0){
            this.add(characterPanel(GameState.getInstance().getCharacterSlots().fourthCharacter())).expand().fill();
        }
    }



    private CharacterPanel characterPanel(Character character) {
        CharacterPanel characterPanel = new CharacterPanel(character, Assets.skin());
        characterPanel.add(new Label("hp: " + character.getHp(), Assets.skin(), "title")).expandX();
        characterPanel.row();
        Image characterSprite = new CharacterSprite(character.getCharacterType());
        characterSprite.setScaling(Scaling.fit);
        characterPanel.add(characterSprite).width(200).height(250);
        characterPanel.row();
        characterPanel.add(new BuffsBar(character.getBuffs(), character.getDebuffs())).expandX().height(16).left();
        characterPanel.defaults().expandX();
        characterPanel.setName(character.getCharacterType().toString());

        return characterPanel;
    }

    private Table emptyCharacterPanel(){
        Table empty = new Table(Assets.skin());
        empty.defaults().expand().fill();
        empty.add(new Label("                   ", Assets.skin()));
        return empty;
    }



}
