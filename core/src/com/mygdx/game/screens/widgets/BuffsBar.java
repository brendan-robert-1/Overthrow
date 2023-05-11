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
        this.clearChildren();
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
        for(Buff buff : buffs){
            if(buff.equals(toAnimate)){

            }
        }
        for(Buff debuff : debuffs){
            if(debuff.equals(toAnimate)){

            }
        }
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
    }
}
