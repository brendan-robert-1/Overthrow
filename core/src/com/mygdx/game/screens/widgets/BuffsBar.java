package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.character.buff.ArmorUpBuff;
import com.mygdx.game.character.buff.Buff;

import java.util.ArrayList;
import java.util.List;

public class BuffsBar extends Table/* implements BuffAnimatorSubject*/{
    private List<Buff> buffs = new ArrayList<>();
    private List<Buff> debuffs = new ArrayList<>();
    private HudTooltip hudTooltip = HudTooltip.getInstance();

    public void update(){
        this.clearChildren();
        build();
    }

    public BuffsBar(){
        build();
    }

    public void build(){
        for(Buff buff : buffs) {
            BuffSprite buffSprite = new BuffSprite(buff);
            buffSprite.addListener(new HudTooltipListener());
            this.add(buffSprite).expandX().left().padLeft(10);
        }
        for(Buff debuff : debuffs){
            BuffSprite buffSprite = new BuffSprite(debuff);
            buffSprite.addListener(new HudTooltipListener());
            this.add(buffSprite).expandX().left().padLeft(10);
        }
        this.pack();
    }



    public void animateDebuff(Buff toAnimate) {

       // notify(BuffAnimatorSubject.BuffAnimatorEvent.FINISHED_ANIMATION);
    }


    public List<Buff> getBuffs() {
        return buffs;
    }



    public List<Buff> getDebuffs() {
        return debuffs;
    }



    public void addBuff(Buff buff) {
        boolean buffFound = false;
        for(Buff existingBuff : buffs){
            if(existingBuff.buffType == buff.getBuffType()){
                existingBuff.increaseTurnsRemaining(buff.turnsRemaining);
                existingBuff.setPotency(existingBuff.getPotency() + buff.getPotency());
                buffFound = true;
            }
        }
        if(!buffFound){
            buffs.add(buff);
        }
        build();
    }

    public void addDebuff(Buff debuff){
        boolean buffFound = false;
        for(Buff existingDebuff : debuffs){
            if(existingDebuff.buffType == debuff.getBuffType()){
                existingDebuff.increaseTurnsRemaining(debuff.turnsRemaining);
                existingDebuff.setPotency(existingDebuff.getPotency() + debuff.getPotency());
                buffFound = true;
            }
        }
        if(!buffFound){
            debuffs.add(debuff);
        }
        build();
    }



    public void removeDebuff(Buff debuff) {
        debuffs.remove(debuff);
    }

    public void removeBuff(Buff buff){
        buffs.remove(buff);
    }



    public void removeFinishedBuffs() {
        List<Buff> newBuffs =  new ArrayList<>();
        List<Buff> newDebuffs = new ArrayList<>();
        for(Buff buff : buffs){
            if(buff.turnsRemaining >0 )newBuffs.add(buff);
        }
        for (Buff buff : debuffs){
            if(buff.turnsRemaining >0 )newDebuffs.add(buff);
        }
        this.buffs = newBuffs;
        this.debuffs =newDebuffs;
    }
}
