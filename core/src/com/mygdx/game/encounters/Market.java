package com.mygdx.game.encounters;

import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.state.GameNode;
import com.mygdx.game.state.shops.ShopOffering;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Market extends GameNode {
    private Map<InventoryItem, Integer> waresToPrice;
    public Market(Map<InventoryItem, Integer> waresToPrice){
        super(NodeType.MARKET, "Market");
        this.waresToPrice = waresToPrice;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Market");
        return sb.toString();
    }



    public Map<InventoryItem, Integer> getWaresToPrice() {
        return waresToPrice;
    }

    public List<ShopOffering> getShopOfferings(){
        List<ShopOffering> offerings = new ArrayList<>();
        for(InventoryItem item : waresToPrice.keySet()){
            int price = waresToPrice.get(item);
            ShopOffering offering = new ShopOffering();
            offering.setInventoryItem(item);
            offering.setPrice(price);
            offerings.add(offering);
        }
        return offerings;
    }
}
