package com.mygdx.game.encounters.state;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.character.buff.Buff;
import com.mygdx.game.character.knight.KnightGenerator;
import com.mygdx.game.character.plaguedoctor.PlagueDoctorGenerator;
import com.mygdx.game.encounters.state.gear.EquippedGear;

import java.util.List;

public record Character(
    String name,
    CharacterType characterType,
    int hp,
    EquippedGear equippedGear,
    Ability firstBasicAbility,
    Ability secondBasicAbility,
    Ability ultimateAbility,
    List<Buff> buffs
) {
    public enum CharacterType {
        PLAGUE_DOCTOR, LEPER, INVENTOR, KNIGHT;
//        public static String toDisplayName(CharacterType characterType){
//            switch(characterType){
//                case PLAGUE_DOCTOR -> {return "Plague Doctor";}
//                case LEPER -> { return "Leper";}
//                case INVENTOR -> {return "Inventor";}
//                case KNIGHT -> {return "Knight";}
//                default -> throw new IllegalStateException("Unexpected value: " + characterType);
//            }
//        }
       
    }





    public static Character generateNewCharacter(Character.CharacterType characterType){
        switch(characterType){
            case PLAGUE_DOCTOR -> { return PlagueDoctorGenerator.generateStartingPlagueDoctor(); }
            case LEPER -> {return PlagueDoctorGenerator.generateStartingPlagueDoctor();}
            case INVENTOR -> {return PlagueDoctorGenerator.generateStartingPlagueDoctor();}
            case KNIGHT -> {return KnightGenerator.generateStartingPlagueDoctor();}
            default -> throw new IllegalStateException("Unexpected value: " + characterType);
        }
    }
}
