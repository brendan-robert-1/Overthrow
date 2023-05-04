package com.mygdx.game.screens.state.shops;

import java.util.List;

public class MarketOffering {
    private List<PotentialOffering> potentialArmor;



    public List<PotentialOffering> getPotentialWeapons() {
        return potentialWeapons;
    }



    public MarketOffering setPotentialWeapons(List<PotentialOffering> potentialWeapons) {
        this.potentialWeapons = potentialWeapons;
        return this;
    }



    public List<PotentialOffering> getPotentialConsumables() {
        return potentialConsumables;
    }



    public MarketOffering setPotentialConsumables(List<PotentialOffering> potentialConsumables) {
        this.potentialConsumables = potentialConsumables;
        return this;
    }



    public List<PotentialOffering> getPotentialGems() {
        return potentialGems;
    }



    public MarketOffering setPotentialGems(List<PotentialOffering> potentialGems) {
        this.potentialGems = potentialGems;
        return this;
    }



    private List<PotentialOffering> potentialWeapons;
    private List<PotentialOffering> potentialConsumables;
    private List<PotentialOffering> potentialGems;



    public List<PotentialOffering> getPotentialArmor() {
        return potentialArmor;
    }



    public MarketOffering setPotentialArmor(List<PotentialOffering> potentialArmor) {
        this.potentialArmor = potentialArmor;
        return this;
    }
}
