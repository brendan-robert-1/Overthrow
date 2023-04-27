package com.mygdx.game.character.plaguedoctor;

import com.mygdx.game.character.Character;
import com.mygdx.game.character.abilities.Ability;
import com.mygdx.game.character.abilities.Passive;
import com.mygdx.game.character.stats.StartingStats;

import java.util.ArrayList;
import java.util.List;

public class PlagueDoctor implements Character {
    @Override
    public Passive passive() {
        return new PlagueDoctorPassive();
    }

    @Override
    public List<Ability> startingBasicAbilities() {
      return new ArrayList<>(List.of(
              new TossConcoction(),
              new Bloodlet())
      );
    }

    @Override
    public Ability startingUltimate() {
        return new Miasma();
    }

    @Override
    public List<Ability> advancedBasicAbilities() {
        return new ArrayList<>();
    }

    @Override
    public List<Ability> advancedUltimates() {
        return new ArrayList<>();
    }

    @Override
    public StartingStats startingStats() {
        return new PlagueDoctorStartingStats();
    }
}
