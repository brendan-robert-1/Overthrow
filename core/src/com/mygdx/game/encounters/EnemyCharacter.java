package com.mygdx.game.encounters;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.character.buff.Buff;
import com.mygdx.game.character.gear.EquippedGear;
import com.mygdx.game.character.plaguedoctor.PlagueDoctorGenerator;
import com.mygdx.game.state.FightUnit;

import java.util.List;

public record EnemyCharacter (
        EnemyType characterType,
        int hp,
        EquippedGear equippedGear,
        Ability firstBasicAbility,
        Ability secondBasicAbility,
        Ability ultimateAbility,
        List<Buff> buffs
) {
    public enum EnemyType {
        HOMESTEADER_DAD,
        HOMESTEADER_MOM,
        HOMESTEADER_DOG
    }
}

