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

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class Team extends Table{
    private HudTooltip hudTooltip = HudTooltip.getInstance();
    private CharacterPanel characterPanel1;
    private CharacterPanel characterPanel2;
    private CharacterPanel characterPanel3;
    private CharacterPanel characterPanel4;

    private Team(){
        populate();
    }
    private static Team instance;
    public static Team getInstance(){
        if(instance == null){
            instance = new Team();
        }
        return instance;
    }



    public boolean contains(Character character) {
        if(characterPanel1 != null && characterPanel1.getCharacter() != null && characterPanel1.getCharacter().equals(character)){
            return true;
        }
        if(characterPanel2 != null && characterPanel2.getCharacter() != null && characterPanel2.getCharacter().equals(character)){
            return true;
        }
        if(characterPanel3 != null && characterPanel3.getCharacter() != null && characterPanel3.getCharacter().equals(character)){
            return true;
        }
        if(characterPanel4 != null && characterPanel4.getCharacter() != null && characterPanel4.getCharacter().equals(character)){
            return true;
        }
        return false;
    }



    public void update() {
        populate();
    }


    private void populate(){
        this.clearChildren();
        if(characterPanel1 != null && characterPanel1.getCharacter().getHp() > 0){
            characterPanel1 = new CharacterPanel(characterPanel1.getCharacter());
            this.add(characterPanel1).expand().fill();
        }
        if(characterPanel2 != null && characterPanel2.getCharacter().getHp() > 0){
            characterPanel2 = new CharacterPanel(characterPanel2.getCharacter());
            this.add(characterPanel2).expand().fill();
        }
        if(characterPanel3 != null && characterPanel3.getCharacter().getHp() > 0){
            characterPanel3 = new CharacterPanel(characterPanel3.getCharacter());
            this.add(characterPanel3).expand().fill();
        }
        if(characterPanel4 != null && characterPanel4.getCharacter().getHp() > 0){
            characterPanel4 = new CharacterPanel(characterPanel4.getCharacter());
            this.add(characterPanel4).expand().fill();
        }
    }

    public Stream<CharacterPanel> streamNonNull(){
        return Arrays.asList(
                characterPanel1,
                characterPanel2,
                characterPanel3,
                characterPanel4
        ).stream().filter(Objects::nonNull);
    }

    public int activeCharacters(){
        int i = 0;
        if(characterPanel1 != null) i++;
        if(characterPanel2 != null) i++;
        if(characterPanel3 != null) i++;
        if(characterPanel4 != null) i++;
        return i;
    }


    public CharacterPanel getCharacterPanel1() {
        return characterPanel1;
    }



    public Team setCharacterPanel1(CharacterPanel characterPanel1) {
        this.characterPanel1 = characterPanel1;
        populate();
        return this;
    }



    public CharacterPanel getCharacterPanel2() {
        return characterPanel2;
    }



    public Team setCharacterPanel2(CharacterPanel characterPanel2) {
        this.characterPanel2 = characterPanel2;
        populate();
        return this;
    }



    public CharacterPanel getCharacterPanel3() {
        return characterPanel3;
    }



    public Team setCharacterPanel3(CharacterPanel characterPanel3) {
        this.characterPanel3 = characterPanel3;
        populate();
        return this;
    }



    public CharacterPanel getCharacterPanel4() {
        return characterPanel4;
    }



    public Team setCharacterPanel4(CharacterPanel characterPanel4) {
        this.characterPanel4 = characterPanel4;
        populate();
        return this;
    }



    public void resolveDeaths() {
        streamNonNull().forEach(c -> {
            if(c.getHp() <= 0){
                removeDead(c);
            }
        });
    }


    private void removeDead(CharacterPanel c){
        if(characterPanel1.equals( c)){
            characterPanel1 = null;
        }
        if(characterPanel2.equals( c)){
            characterPanel1 = null;
        }
        if(characterPanel3.equals( c)){
            characterPanel3 = null;
        }
        if(characterPanel4.equals( c)){
            characterPanel4 = null;
        }
    }



}
