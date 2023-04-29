package com.mygdx.game.state;

import com.mygdx.game.encounters.EnemyCharacter;

public record EnemySlots (
        EnemyCharacter firstCharacter,
        EnemyCharacter secondCharacter,
        EnemyCharacter thirdCharacter,
        EnemyCharacter fourthCharacter
){
    public enum Slot{
        FIRST,
        SECOND,
        THIRD,
        FOURTH
    }
    public EnemyCharacter getCharacterAt(Slot slot){
        switch(slot){
            case FIRST -> { return firstCharacter; }
            case SECOND -> { return secondCharacter; }
            case THIRD -> { return thirdCharacter; }
            case FOURTH -> { return fourthCharacter; }
            default -> throw new IllegalStateException("Unexpected value: " + slot);
        }
    }
}

