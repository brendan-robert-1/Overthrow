package com.mygdx.game.character.buff;

import com.mygdx.game.state.Character;

public abstract class Buff {
    public int turnsRemaining;
    public BuffType buffType;



    public abstract void executeEndOfTurn(Character character);



    public static enum BuffType{
        POISON,
        BLEED,
        CHALLENGE,
        ARMOR_UP,
        PIERCE
    }

    public String displayName(){
       switch(buffType){
           case POISON -> {return "Poison";}
           case BLEED -> {return "Bleed";}
           case CHALLENGE -> {return "Challenged";}
           case ARMOR_UP -> {return "Armor Up";}
           case PIERCE -> {return "Pierced";}

           default -> throw new IllegalStateException("Unexpected value: " + buffType);
       }
    }



    public Buff(int turnsRemaining, BuffType buffType) {
        this.turnsRemaining = turnsRemaining;
        this.buffType = buffType;
    }



    public void reduceTurnsRemaining(int reduceBy){
        turnsRemaining -= reduceBy;
        if(turnsRemaining< 0){
            turnsRemaining = 0;
        }
    }

    public void increaseTurnsRemaining(int increaseBy){
        turnsRemaining++;
    }

    public BuffType getBuffType(){
        return buffType;
    }

    public abstract String getDisplayText();
}
