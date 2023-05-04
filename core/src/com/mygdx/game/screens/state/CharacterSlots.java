package com.mygdx.game.screens.state;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

public record CharacterSlots (
    Character firstCharacter,
    Character secondCharacter,
    Character thirdCharacter,
    Character fourthCharacter
){
    public enum Slot{
        FIRST,
        SECOND,
        THIRD,
        FOURTH
    }
    public Character getCharacterAt(Slot slot){
        switch(slot){
            case FIRST -> { return firstCharacter; }
            case SECOND -> { return secondCharacter; }
            case THIRD -> { return thirdCharacter; }
            case FOURTH -> { return fourthCharacter; }
            default -> throw new IllegalStateException("Unexpected value: " + slot);
        }
    }
    public List<Character> asList(){
        return Arrays.asList(firstCharacter, secondCharacter, thirdCharacter, fourthCharacter);
    }
}