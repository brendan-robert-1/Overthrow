package com.mygdx.game.character.buff;

public abstract class Buff {
    public int turnsRemaining;

    public void reduceTurnsRemaining(int reduceBy){
        turnsRemaining -= reduceBy;
        if(turnsRemaining< 0){
            turnsRemaining = 0;
        }
    }

    public void increaseTurnsRemaining(int increaseBy){
        turnsRemaining++;
    }
}
