package com.mygdx.game.state;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.character.buff.Buff;
import com.mygdx.game.character.gear.EquippedGear;
import com.mygdx.game.character.plaguedoctor.PlagueDoctorGenerator;

import java.util.List;

public record Character(
    CharacterType characterType,
    int hp,
    EquippedGear equippedGear,
    Ability firstBasicAbility,
    Ability secondBasicAbility,
    Ability ultimateAbility,
    List<Buff> buffs
) {
    public enum CharacterType {
        PLAGUE_DOCTOR, LEPER, INVENTOR, KNIGHT
    }

    public static Character generateNewCharacter(Character.CharacterType characterType){
        switch(characterType){
            case PLAGUE_DOCTOR -> { return PlagueDoctorGenerator.generateStartingPlagueDoctor(); }
            case LEPER -> {return PlagueDoctorGenerator.generateStartingPlagueDoctor();}
            case INVENTOR -> {return PlagueDoctorGenerator.generateStartingPlagueDoctor();}
            case KNIGHT -> {return PlagueDoctorGenerator.generateStartingPlagueDoctor();}
            default -> throw new IllegalStateException("Unexpected value: " + characterType);
        }
    }
}
