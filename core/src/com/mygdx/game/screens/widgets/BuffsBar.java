package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.character.buff.Buff;

import java.util.ArrayList;
import java.util.List;

public class BuffsBar extends Table {

    private List<Buff> buffs;
    private List<Buff> debuffs;
    private HudTooltip hudTooltip = HudTooltip.getInstance();

    public void update(){
        this.clearChildren();
        build();
    }

    public BuffsBar(List<Buff> buffs, List<Buff> debuffs){
        if(buffs == null){
            this.buffs =new ArrayList<>();
        } else {
            this.buffs = buffs;
        }
        if(debuffs == null){
            this.debuffs = new ArrayList<>();
        } else {
            this.debuffs = debuffs;
        }
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

}
