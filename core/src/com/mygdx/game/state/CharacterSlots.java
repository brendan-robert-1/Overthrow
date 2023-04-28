package com.mygdx.game.state;

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
}
