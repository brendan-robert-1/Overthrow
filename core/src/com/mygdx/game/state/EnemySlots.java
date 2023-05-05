package com.mygdx.game.state;


import java.util.Arrays;
import java.util.List;

public record EnemySlots (
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

    public boolean contains(Character character) {
        return firstCharacter != null && firstCharacter.equals(character) ||
                secondCharacter != null &&secondCharacter.equals(character) ||
                thirdCharacter != null &&thirdCharacter.equals(character) ||
                fourthCharacter != null &&fourthCharacter.equals(character);
    }
}

