package com.mygdx.game.character.abilities;

import com.mygdx.game.screens.widgets.inventory.InventoryItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeaponAbilityMapper {

    private static WeaponAbilityMapper instance;





    private Map<InventoryItem.ItemTypeId, List<Ability>> itemToAbilities = new HashMap<>();
    private WeaponAbilityMapper(){
        itemToAbilities = buildMap();
    }
    public static WeaponAbilityMapper getInstance(){
        if(instance == null){
            instance = new WeaponAbilityMapper();
        }
        return instance;
    }

    private Map<InventoryItem.ItemTypeId, List<Ability>> buildMap(){
        Map<InventoryItem.ItemTypeId, List<Ability>> map = new HashMap<>();
        List<Ability> abilities = new ArrayList<>();
        abilities.add(new RustyDaggerStab());
        map.put(InventoryItem.ItemTypeId.RUSTY_DAGGER, abilities);
        return map;
    }


    public Map<InventoryItem.ItemTypeId, List<Ability>> getItemToAbilities() {
        return itemToAbilities;
    }

}
