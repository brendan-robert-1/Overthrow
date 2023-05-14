package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;
import com.mygdx.game.state.Character;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class Team extends Table{
    private HudTooltip hudTooltip = HudTooltip.getInstance();
    private CharacterPanel firstCharacterPanel;
    private CharacterPanel secondCharacterPanel;
    private CharacterPanel thirdCharacterPanel;
    private CharacterPanel fourthCharacterPanel;

    private Team(){
        super(Assets.skin());

    }
    private static Team instance;
    public static Team getInstance(){
        if(instance == null){
            instance = new Team();
        }
        return instance;
    }

    public enum TeamSlotIndex {
        FIRST,
        SECOND,
        THIRD,
        FOURTH
    }

    public void init(CharacterPanel firstCharacterPanel, CharacterPanel secondCharacterPanel, CharacterPanel thirdCharacterPanel, CharacterPanel fourthCharacterPanel){

        if(firstCharacterPanel != null && firstCharacterPanel.getCharacter() != null){
            firstCharacterPanel.setTeamSlotIndex(TeamSlotIndex.FIRST);
            instance.firstCharacterPanel = firstCharacterPanel;
            instance.add(firstCharacterPanel).expand();
        }
        if(secondCharacterPanel != null && secondCharacterPanel.getCharacter() != null){
            secondCharacterPanel.setTeamSlotIndex(TeamSlotIndex.SECOND);
            instance.secondCharacterPanel = secondCharacterPanel;
            instance.add(secondCharacterPanel).expand();
        }
        if(thirdCharacterPanel != null && thirdCharacterPanel.getCharacter() != null){
            thirdCharacterPanel.setTeamSlotIndex(TeamSlotIndex.THIRD);
            instance.thirdCharacterPanel = thirdCharacterPanel;
            instance.add(thirdCharacterPanel).expand();
        }
        if(fourthCharacterPanel != null && fourthCharacterPanel.getCharacter() != null){
            fourthCharacterPanel.setTeamSlotIndex(TeamSlotIndex.FOURTH);
            instance.fourthCharacterPanel = fourthCharacterPanel;
            instance.add(fourthCharacterPanel).expand();
        }
        instance.pack();
    }


    public boolean contains(Character character) {
        if(firstCharacterPanel != null && firstCharacterPanel.getCharacter() != null && firstCharacterPanel.getCharacter().equals(character)){
            return true;
        }
        if(secondCharacterPanel != null && secondCharacterPanel.getCharacter() != null && secondCharacterPanel.getCharacter().equals(character)){
            return true;
        }
        if(thirdCharacterPanel != null && thirdCharacterPanel.getCharacter() != null && thirdCharacterPanel.getCharacter().equals(character)){
            return true;
        }
        if(fourthCharacterPanel != null && fourthCharacterPanel.getCharacter() != null && fourthCharacterPanel.getCharacter().equals(character)){
            return true;
        }
        return false;
    }



    public void update() {
        if(firstCharacterPanel != null) firstCharacterPanel.update();
        if(secondCharacterPanel != null) secondCharacterPanel.update();
        if(thirdCharacterPanel != null) thirdCharacterPanel.update();
        if(fourthCharacterPanel != null) fourthCharacterPanel.update();
        this.pack();
    }

    public Stream<CharacterPanel> streamNonNull(){
        return Stream.of(firstCharacterPanel, secondCharacterPanel, thirdCharacterPanel, fourthCharacterPanel
        ).filter(Objects::nonNull);
    }

    public int activeCharacters(){
        int i = 0;
        if(firstCharacterPanel != null) i++;
        if(secondCharacterPanel != null) i++;
        if(thirdCharacterPanel != null) i++;
        if(fourthCharacterPanel != null) i++;
        return i;
    }


    public CharacterPanel getFirstCharacterPanel() {
        return firstCharacterPanel;
    }



    public Team setFirstCharacterPanel(CharacterPanel firstCharacterPanel) {
        this.firstCharacterPanel = firstCharacterPanel;
        return this;
    }



    public CharacterPanel getSecondCharacterPanel() {
        return secondCharacterPanel;
    }



    public Team setSecondCharacterPanel(CharacterPanel secondCharacterPanel) {
        this.secondCharacterPanel = secondCharacterPanel;

        return this;
    }



    public CharacterPanel getThirdCharacterPanel() {
        return thirdCharacterPanel;
    }



    public Team setThirdCharacterPanel(CharacterPanel thirdCharacterPanel) {
        this.thirdCharacterPanel = thirdCharacterPanel;

        return this;
    }



    public CharacterPanel getFourthCharacterPanel() {
        return fourthCharacterPanel;
    }



    public Team setFourthCharacterPanel(CharacterPanel fourthCharacterPanel) {
        this.fourthCharacterPanel = fourthCharacterPanel;

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
        if(firstCharacterPanel!= null && firstCharacterPanel.equals( c)){
            firstCharacterPanel = null;
        }
        if(secondCharacterPanel!= null && secondCharacterPanel.equals( c)){
            firstCharacterPanel = null;
        }
        if(thirdCharacterPanel!= null && thirdCharacterPanel.equals( c)){
            thirdCharacterPanel = null;
        }
        if(fourthCharacterPanel!= null && fourthCharacterPanel.equals( c)){
            fourthCharacterPanel = null;
        }
    }



}
