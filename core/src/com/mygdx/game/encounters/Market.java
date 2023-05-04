package com.mygdx.game.encounters;

import com.mygdx.game.screens.state.items.ItemSlot;
import com.mygdx.game.screens.state.shops.ShopOffering;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Market extends Encounter{
    private Map<ItemSlot, Integer> waresToPrice;
    public Market(Map<ItemSlot, Integer> waresToPrice){
        super(NodeType.MARKET, "Market");
        this.waresToPrice = waresToPrice;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Market");
        return sb.toString();
    }



    public Map<ItemSlot, Integer> getWaresToPrice() {
        return waresToPrice;
    }

    public List<ShopOffering> getShopOfferings(){
        List<ShopOffering> offerings = new ArrayList<>();
        for(ItemSlot item : waresToPrice.keySet()){
            int price = waresToPrice.get(item);
            ShopOffering offering = new ShopOffering();
            offering.setItemSlot(item);
            offering.setPrice(price);
            offerings.add(offering);
        }
        return offerings;
    }
}
