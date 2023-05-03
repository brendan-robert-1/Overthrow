package com.mygdx.game.encounters;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.character.buff.Buff;
import com.mygdx.game.encounters.state.gear.EquippedGear;

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
        HOMESTEADER_DOG,
        TAX_MAN,
        TAX_COLLECTOR,
        MUTATED_CHICKEN,
        MUTATED_COW,
        MUTATED_PIG,
        FRED
    }
}

