package com.mygdx.game.encounters;

import com.google.common.collect.ImmutableList;
import java.util.Arrays;


import static com.mygdx.game.encounters.OverthrowActs.ActType.*;

public class OverthrowActs {
    public enum ActType { FARMS, BATTLEFIELD, SWAMP, MOUNTAIN, PALACE }
    private ImmutableList<ActType> acts;

    private OverthrowActs(){}
    private OverthrowActs(ImmutableList<ActType> acts){
        this.acts = acts;
    }
    public static OverthrowActs generateNormalActs(){
        return new OverthrowActs(ImmutableList.copyOf(Arrays.asList(
                FARMS, BATTLEFIELD, SWAMP, MOUNTAIN, PALACE
        )));
    }

    public ImmutableList<ActType> getActs() {
        return acts;
    }

}
