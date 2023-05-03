package com.mygdx.game.encounters.state.shops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.encounters.OverthrowActs.ActType;

public class MarketOfferingFactory {

    private static final String PATH = "gamedata/markets/";
    public static MarketOffering getMarketOfferingFor(ActType actType) {
        switch(actType){
            case FARMS -> { return loadMarketOffering("farm_markets.json");}
            case BATTLEFIELD -> {return loadMarketOffering("battlefield_markets.json");}
            case SWAMP -> {return loadMarketOffering("swamp_markets.json");}
            case MOUNTAIN -> {return loadMarketOffering("mountain_markets.json");}
            case PALACE -> {return loadMarketOffering("palace_markets.json");}
            default -> throw new IllegalStateException("Unexpected value: " + actType);
        }
    }



    private static MarketOffering loadMarketOffering(String jsonFile) {
        Json json = new Json();
        String jsonStr = Gdx.files.internal(PATH + jsonFile).readString();
        return json.fromJson(MarketOffering.class, jsonStr);
    }
}
