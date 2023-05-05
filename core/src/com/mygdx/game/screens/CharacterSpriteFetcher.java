package com.mygdx.game.screens;

import com.mygdx.game.state.Character;

public class CharacterSpriteFetcher {
    public static String mediumSpriteFrom(Character.CharacterType characterType){
        switch(characterType){
            case PLAGUE_DOCTOR -> { return "plauge_doctor";}
            case LEPER -> { return "plauge_doctor";}
            case INVENTOR -> { return "plauge_doctor";}
            case KNIGHT -> { return "knight2-large";}
            default -> throw new IllegalStateException("Unexpected value: " + characterType);
        }
    }

    public static String smallSpriteFrom(Character.CharacterType characterType){
        switch(characterType){
            case PLAGUE_DOCTOR -> { return "plauge_doctor";}
            case LEPER -> { return "plauge_doctor";}
            case INVENTOR -> { return "plauge_doctor";}
            case KNIGHT -> { return "KNIGHT2";}
            default -> throw new IllegalStateException("Unexpected value: " + characterType);
        }
    }


}
