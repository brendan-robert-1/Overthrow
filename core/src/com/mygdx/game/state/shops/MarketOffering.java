package com.mygdx.game.state.shops;

import java.util.List;

public class MarketOffering {
    private List<Offering> potentialArmor;



    public List<Offering> getPotentialWeapons() {
        return potentialWeapons;
    }



    public MarketOffering setPotentialWeapons(List<Offering> potentialWeapons) {
        this.potentialWeapons = potentialWeapons;
        return this;
    }



    public List<Offering> getPotentialConsumables() {
        return potentialConsumables;
    }



    public MarketOffering setPotentialConsumables(List<Offering> potentialConsumables) {
        this.potentialConsumables = potentialConsumables;
        return this;
    }



    public List<Offering> getPotentialGems() {
        return potentialGems;
    }



    public MarketOffering setPotentialGems(List<Offering> potentialGems) {
        this.potentialGems = potentialGems;
        return this;
    }



    private List<Offering> potentialWeapons;
    private List<Offering> potentialConsumables;
    private List<Offering> potentialGems;



    public List<Offering> getPotentialArmor() {
        return potentialArmor;
    }



    public MarketOffering setPotentialArmor(List<Offering> potentialArmor) {
        this.potentialArmor = potentialArmor;
        return this;
    }
}
