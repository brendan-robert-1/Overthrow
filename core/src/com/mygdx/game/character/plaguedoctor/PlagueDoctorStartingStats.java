package com.mygdx.game.character.plaguedoctor;

import com.mygdx.game.character.stats.StartingStats;

public class PlagueDoctorStartingStats implements StartingStats {
    @Override
    public int armor() {
        return 30;
    }

    @Override
    public int magicResistance() {
        return 30;
    }

    @Override
    public int physicalDamage() {
        return 30;
    }

    @Override
    public int magicalDamage() {
        return 30;
    }

    @Override
    public int hp() {
        return 50;
    }

    @Override
    public int speed() {
        return 30;
    }
}
