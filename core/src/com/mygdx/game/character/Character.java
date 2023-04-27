package com.mygdx.game.character;

import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.character.abilities.Passive;
import com.mygdx.game.character.buff.Buff;
import com.mygdx.game.character.gear.EquippedGear;
import com.mygdx.game.character.stats.StartingStats;

import java.util.List;

public interface Character {
    public Passive passive();
    public List<Ability> startingBasicAbilities();
    public Ability startingUltimate();
    public List<Ability> advancedBasicAbilities();
    public List<Ability> advancedUltimates();
    public StartingStats startingStats();
}
