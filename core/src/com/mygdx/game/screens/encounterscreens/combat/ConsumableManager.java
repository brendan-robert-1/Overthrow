package com.mygdx.game.screens.encounterscreens.combat;

import com.mygdx.game.encounters.GameNode;
import com.mygdx.game.screens.widgets.Team;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;
import com.mygdx.game.screens.widgets.fight.FightNode;
import com.mygdx.game.screens.widgets.inventory.ConsumableDetails;
import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.state.GameState;

import java.util.ArrayList;
import java.util.List;

public class ConsumableManager {
    private boolean canUseConsumableThisTurn = true; //TODO allow items to mutate this. boss relic give 2 per turn for example
    private static ConsumableManager instance;
    private ConsumableManager(){}
    public static ConsumableManager getInstance(){
        if(instance == null){
            instance = new ConsumableManager();
        }
        return instance;
    }

    public List<CharacterPanel> viableTargets(ConsumableDetails consumableDetails){
        if(!canUseConsumableThisTurn){ return new ArrayList<>();}
        List<CharacterPanel> viableTargets = new ArrayList<>();
        if(consumableDetails.isFriendlyTargetable()){
            addFriendlyTargets(viableTargets, consumableDetails);
        }
        if(consumableDetails.isFoeTargetable()){
            addFoeTargets(viableTargets, consumableDetails);
        }
        return viableTargets;
    }



    private void addFoeTargets(List<CharacterPanel> viableTargets, ConsumableDetails consumableDetails) {
        GameNode currentNode = GameState.getInstance().getCurrentNode();
        if(currentNode instanceof FightNode){
            FightNode fightNode = (FightNode) currentNode;
            fightNode.getEnemyTeam().streamNonNull().forEach(viableTargets::add);
        }
    }



    private void addFriendlyTargets(List<CharacterPanel> viableTargets, ConsumableDetails consumableDetails) {
        Team.getInstance().streamNonNull().forEach(viableTargets::add);
    }



    public void consumableUsed(){ //TODO convert to observer
        this.canUseConsumableThisTurn = false; //this class can keep track of private int when i allow more than one per turn
    }
}
