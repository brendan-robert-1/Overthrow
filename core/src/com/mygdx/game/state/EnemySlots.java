package com.mygdx.game.state;


import java.util.Arrays;
import java.util.List;

public class EnemySlots {
   private Character firstCharacter;
    private Character secondCharacter;
    private Character thirdCharacter;
    private Character fourthCharacter;


    public EnemySlots() {
    }



    public EnemySlots(Character firstCharacter, Character secondCharacter, Character thirdCharacter, Character fourthCharacter) {
        this.firstCharacter = firstCharacter;
        this.secondCharacter = secondCharacter;
        this.thirdCharacter = thirdCharacter;
        this.fourthCharacter = fourthCharacter;
    }



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

    public void setFirstCharacter(Character firstCharacter) {
        this.firstCharacter = firstCharacter;
    }



    public void setSecondCharacter(Character secondCharacter) {
        this.secondCharacter = secondCharacter;
    }



    public void setThirdCharacter(Character thirdCharacter) {
        this.thirdCharacter = thirdCharacter;
    }



    public void setFourthCharacter(Character fourthCharacter) {
        this.fourthCharacter = fourthCharacter;

    }

    public Character getFirstCharacter() {
        return firstCharacter;
    }



    public Character getSecondCharacter() {
        return secondCharacter;
    }



    public Character getThirdCharacter() {
        return thirdCharacter;
    }



    public Character getFourthCharacter() {
        return fourthCharacter;
    }
}

